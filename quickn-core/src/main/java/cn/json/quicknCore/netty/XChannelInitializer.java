package cn.json.quicknCore.netty;
 
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import java.util.concurrent.TimeUnit;


public class XChannelInitializer extends ChannelInitializer<SocketChannel> {

	private final DefaultEventExecutorGroup executorGroup;
	
    public XChannelInitializer(final DefaultEventExecutorGroup executorGroup) {
    	this.executorGroup = executorGroup;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new ReadTimeoutHandler(60,TimeUnit.SECONDS));
		pipeline.addLast(new WriteTimeoutHandler(60,TimeUnit.SECONDS));
		pipeline.addLast(new HttpRequestDecoder());
		pipeline.addLast(new HttpResponseEncoder());
		pipeline.addLast(new HttpObjectAggregator(1048576));
		pipeline.addLast(new ChunkedWriteHandler());
		pipeline.addLast(this.executorGroup, "", new XChannelHandler());
    }
}
