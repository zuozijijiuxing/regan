package com.regan.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class RHttpReqHandler extends ChannelInboundHandlerAdapter {
	
	private static InternalLogger mLogger = InternalLoggerFactory.getInstance(RHttpReqHandler.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		super.channelRead(ctx, msg);
		
		// request header
		if( msg instanceof HttpRequest ) {
			HttpRequest req = (HttpRequest)msg;
			mLogger.info(req.toString());
		}
		
		// request content body
		if( msg instanceof HttpContent ) {
			
			// you can do some decode...
			
		}
		
	}
	
}
