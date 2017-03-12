package com.regan.main;

import com.regan.netty.RHttpServer;

import io.netty.channel.ChannelFuture;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
public class RMain {
	
	private static InternalLogger mLogger = InternalLoggerFactory.getInstance(RMain.class);
	
	/**
	 * This is entry point for application.
	 * @param args, arguments.
	 */
	public static void main(String[] args) {
		RHttpServer server = new RHttpServer(8080);
		try {
			ChannelFuture f = server.listen();
			if( null != f ) {
				f.channel().closeFuture().sync();
			}
		} catch (Exception e) {
			mLogger.error(e.getMessage());
		} finally {
			server.shutDown();
		}
	}

}
