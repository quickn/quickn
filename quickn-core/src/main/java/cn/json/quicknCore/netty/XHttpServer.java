package cn.json.quicknCore.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @copyright (c) xhigher 2015 
 * @author xhigher    2015-3-26 
 */
public class XHttpServer {

	private int defaultPort = 8686;
	private String defaultHost = "0.0.0.0";
	
	private static Logger logger = LoggerFactory.getLogger("syslog");
	
	protected EventLoopGroup bossGroup = null;
	protected EventLoopGroup workerGroup = null;
	protected ServerBootstrap bootstrap = null;
	private DefaultEventExecutorGroup executorGroup = null;
	
	private boolean isInit = false;
	
	public XHttpServer() {
		isInit = false;
	}

	public XHttpServer(final int port) {
		defaultPort = port;
		isInit = false;
	}

	public XHttpServer init() {
		isInit = true;
		return this;
	}

	public void start() {
		if(isInit){
			executorGroup = new DefaultEventExecutorGroup(4, new DefaultThreadFactory("bizEventExecutorGroup"));
			bossGroup = new NioEventLoopGroup(1);
	        workerGroup = new NioEventLoopGroup();
			try {
				bootstrap = new ServerBootstrap();
				bootstrap.group(bossGroup, workerGroup);
				bootstrap.channel(NioServerSocketChannel.class);
				bootstrap.option(ChannelOption.SO_BACKLOG, 100);
				bootstrap.handler(new LoggingHandler(LogLevel.INFO));
				bootstrap.childHandler(new XChannelInitializer(executorGroup));
				
				ChannelFuture future = bootstrap.bind(defaultHost,defaultPort).sync();
				future.channel().closeFuture().sync();
			}catch (Exception e){
				logger.error("XHttpServer.start.Exception",e);
			} finally {
				stop();
			}
		}else{
			logger.error("XHttpServer.init.failed!!!");
		}
	}

	public void stop() {
		if(bossGroup!=null){
			bossGroup.shutdownGracefully();
		}
		if(workerGroup != null){
			workerGroup.shutdownGracefully();
		}
		if(executorGroup != null){
			executorGroup.shutdownGracefully();
		}
	}

}
