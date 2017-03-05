package cn.json.quicknCore.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders.Names;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class XProcessor implements Runnable {
	public static final Logger logger = LoggerFactory.getLogger(XProcessor.class);
	private ChannelHandlerContext context;
	private String requestIP = null;
	private String requestPath = null;
	private Map<String, List<String>> requestData = null;
	private boolean isContinued = false;
	private String allowOrigin = null;

	public XProcessor(ChannelHandlerContext ctx, String ip, String path, Map<String, List<String>> data, boolean isContinued, String origin) {
		this.context = ctx;
		this.requestIP = ip;
		this.requestPath = path;
		this.requestData = data;
		this.isContinued = isContinued;
		this.allowOrigin = origin;
	}

	@Override
	public void run() {
		String resultStr = String.valueOf(XRouter.doLogic(requestIP, requestPath, requestData));
		logger.info("XProcessor.result="+resultStr);
		if (isContinued) {
			context.write(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.CONTINUE));
		}
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,Unpooled.wrappedBuffer(resultStr.getBytes()));
		response.headers().set(Names.CONTENT_TYPE,"text/plain; charset=" + CharsetUtil.UTF_8.toString());
		response.headers().set(Names.CONTENT_LENGTH,response.content().readableBytes());
		if (allowOrigin != null) {
			response.headers().set(Names.ACCESS_CONTROL_ALLOW_ORIGIN,allowOrigin);
		}
		context.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
}
