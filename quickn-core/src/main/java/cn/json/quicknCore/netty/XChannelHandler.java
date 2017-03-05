package cn.json.quicknCore.netty;

import cn.json.quicknCore.conf.APIConfig;
import cn.json.quicknCore.conf.PublicConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.HttpHeaders.Names;
import io.netty.handler.codec.http.multipart.*;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.EndOfDataDecoderException;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.ErrorDataDecoderException;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XChannelHandler extends ChannelInboundHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger("syslog");
	
	private HttpRequest request = null;
	private String requestIP = null;
	
	private String requestPath = null;
	private Map<String, List<String>> requestData = null;
	private String contentType = PublicConfig.CONTENT_TYPE_NORMAL;
	private StringBuffer bodyContentSB = null;
	private boolean is100ContinueExpected = false;
	private String allowOrigin = null;
	private boolean readingChunks = false;
	private static final HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);

    private HttpPostRequestDecoder postDecoder = null;
    
    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
    	context.flush();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext context) {
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext context) {
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
        if (msg instanceof HttpRequest) {
        	request = (HttpRequest) msg;
        	
        	if (!request.getDecoderResult().isSuccess()) {
        		logger.error("XChannelHandler.channelRead:HttpResponseStatus.BAD_REQUEST");
        		sendError(context, HttpResponseStatus.BAD_REQUEST);
                return;
            }
        	
        	HttpMethod method = request.getMethod();
        	if (method != HttpMethod.GET && method != HttpMethod.POST) {
        		logger.error("XChannelHandler.channelRead:HttpResponseStatus.METHOD_NOT_ALLOWED");
                sendError(context, HttpResponseStatus.METHOD_NOT_ALLOWED);
                return;
            }

        	QueryStringDecoder decoder = new QueryStringDecoder(request.getUri());
        	requestPath = decoder.path();
        	logger.info("requestPath="+requestPath);
            if (requestPath == null) {
            	logger.error("XChannelHandler.channelRead:HttpResponseStatus.FORBIDDEN");
                sendError(context, HttpResponseStatus.FORBIDDEN);
                return;
            }
            requestData = decoder.parameters();
            if(requestData.isEmpty()){
            	requestData = new HashMap<String, List<String>>();
            }
            
            
            
            HttpHeaders header = request.headers();
            requestIP = header.get("X-Real-IP");
            if (requestIP == null) {
            	InetSocketAddress insocket = (InetSocketAddress) context.channel().remoteAddress();
    			requestIP = insocket.getAddress().getHostAddress();
			}
            logger.info("X-Forwarded-For="+header.get("X-Forwarded-For"));
			List<String> ipList = new ArrayList<String>();
			ipList.add(requestIP);
			requestData.put(PublicConfig.KEY_CLIENT_IP, ipList);
			
			is100ContinueExpected = HttpHeaders.is100ContinueExpected(request);
			
            if(method == HttpMethod.POST){
            	String type = header.get("Content-Type").toLowerCase();
    			logger.info("content-type="+type);
    			if(type.equals("application/json")){
    				contentType = PublicConfig.CONTENT_TYPE_JSON;
    			}else if(type.equals("application/xml") || type.equals("text/xml")){
    				contentType = PublicConfig.CONTENT_TYPE_XML;
    			}
            	if(!PublicConfig.CONTENT_TYPE_NORMAL.equals(contentType)){
            		bodyContentSB = new StringBuffer();
            	}else{
                	String origin = header.get(Names.ORIGIN);
                	if(!checkAllowOrigin(origin)){
                		logger.error("XChannelHandler.channelRead.checkAllowOrigin:HttpResponseStatus.FORBIDDEN:"+origin);
                		sendError(context, HttpResponseStatus.FORBIDDEN);
                        return;
                	}
                    try {
                    	postDecoder = new HttpPostRequestDecoder(factory, request);
                    } catch (ErrorDataDecoderException e) {
        	            e.printStackTrace();
        	            logger.error("XChannelHandler.channelRead.HttpPostRequestDecoder.ErrorDataDecoderException=", e);
        	            sendError(context, HttpResponseStatus.BAD_REQUEST);
        	            return;
        	        }
                    readingChunks = HttpHeaders.isTransferEncodingChunked(request);
            	}
            }else if(method == HttpMethod.GET){
            	context.executor().submit(new XProcessor(context, requestIP, requestPath, requestData,is100ContinueExpected,allowOrigin));
            }else{
            	logger.error("XChannelHandler.channelRead:HttpResponseStatus.METHOD_NOT_ALLOWED");
            	sendError(context, HttpResponseStatus.METHOD_NOT_ALLOWED);
                return;
            }
        }
        
        if (msg instanceof HttpContent) {
        	if(PublicConfig.CONTENT_TYPE_NORMAL.equals(contentType)){
		        if (postDecoder != null) {
		        	HttpContent chunk = (HttpContent) msg;

		        	try {
		        		postDecoder.offer(chunk);
		        	} catch (ErrorDataDecoderException e1) {
		                logger.error("XChannelHandler.channelRead.ErrorDataDecoderException=", e1);
		                sendError(context, HttpResponseStatus.INTERNAL_SERVER_ERROR);
		                return;
		            }
		        	readHttpDataChunkByChunk();
		        	
		        	if (chunk instanceof LastHttpContent) {
		        		readingChunks = false;
		        		postDecoder.destroy();
		        		postDecoder = null;
		        		
		        		context.executor().submit(new XProcessor(context, requestIP, requestPath, requestData,is100ContinueExpected,allowOrigin));
		        	}
		        }
        	}else{
        		HttpContent chunk = (HttpContent) msg;
            	ByteBuf buf = chunk.content();
        		while(buf.isReadable()){
        			byte nextByte = buf.readByte();
        			bodyContentSB.append((char)nextByte);
        		}
            	if (chunk instanceof LastHttpContent) {
        			List<String> tempList = new ArrayList<String>();
        			tempList.add(contentType);
        			requestData.put(PublicConfig.KEY_CONTENT_TYPE, tempList);
        			
        			tempList = new ArrayList<String>();
        			tempList.add(bodyContentSB.toString());
        			requestData.put(PublicConfig.KEY_CONTENT_DATA, tempList);
            		context.executor().submit(new XProcessor(context, requestIP, requestPath, requestData,is100ContinueExpected,null));
            	}
        	}
        }
    }

    private void readHttpDataChunkByChunk() {
        try {
            while (postDecoder.hasNext()) {
            	InterfaceHttpData data = postDecoder.next();
                if (data != null) {
                    try {
                    	if (data.getHttpDataType() == HttpDataType.Attribute) {
                            Attribute attribute = (Attribute) data;
                            List<String> valueList = new ArrayList<String>();
                            valueList.add(attribute.getValue());
                            requestData.put(attribute.getName(), valueList);
                        }  
                    } catch(Exception e){
                    	logger.error("XChannelHandler.readHttpDataChunkByChunk.Exception:", e);
                    }finally {
                        data.release();
                    }
                }
            }
        } catch (EndOfDataDecoderException e1) {
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.error("XChannelHandler.exceptionCaught:", cause);
    	ctx.flush();
    	ctx.channel().close();
    }
    
    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
    	ByteBuf errorInfo = Unpooled.copiedBuffer("Failure: " + status + "\r\n", CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, errorInfo);
        errorInfo.release();
        response.headers().set(Names.CONTENT_TYPE, "text/plain; charset="+CharsetUtil.UTF_8.toString());
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

	private boolean checkAllowOrigin(String origin){
		if(origin != null){
			for(int i = 0, n = APIConfig.ACCESS_CONTROL_ALLOW_ORIGIN_LIST.length; i<n; i++){
				if(origin.startsWith(APIConfig.ACCESS_CONTROL_ALLOW_ORIGIN_LIST[i])){
					allowOrigin = APIConfig.ACCESS_CONTROL_ALLOW_ORIGIN_LIST[i];
					return true;
				}
			}
		}
		return false;
	}

}