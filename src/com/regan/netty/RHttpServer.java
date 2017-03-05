package com.regan.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * This is httpServer.
 * @author gaokang
 *
 */
public class RHttpServer {
	
	private static InternalLogger mLogger = InternalLoggerFactory.getInstance(RHttpServer.class);
	
	/**
	 * This is port for httpServer listening!
	 */
	private int mPort = 8080;
	
	private EventLoopGroup mBossGroup = new NioEventLoopGroup();
	private EventLoopGroup mWorkGroup = new NioEventLoopGroup();
	private ServerBootstrap mBoostrap = new ServerBootstrap();
	
	/**
	 * Constructor for {@link RHttpServer}
	 * @param port
	 */
	public RHttpServer(int port) {
		this.mPort = port;
	}
	
	/**
	 * Set listening port.
	 * @param port, integer 
	 * @return instance of {@link RHttpServer}
	 */
	public RHttpServer setPort(int port) {
		this.mPort = port;
		return this;
	}
	
	/**
	 * Get listening port.
	 * @return
	 */
	public int getPort() {
		return this.mPort;
	}
	
	/**
	 * Start httpServer.
	 * @return {@link ChannelFuture}
	 */
	public ChannelFuture listen() {
		try{
			mBoostrap.group(mBossGroup,mWorkGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
					
					    // encode {@link HttpRequest}
						ch.pipeline().addLast(new HttpResponseEncoder());
						
						// decode {@link HttpRequest}
						ch.pipeline().addLast(new HttpRequestDecoder());
						
						// handler {@link HttpRequest}
						ch.pipeline().addLast(new RHttpReqHandler());
					}
				}).option(ChannelOption.SO_BACKLOG, 100)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
			return mBoostrap.bind(mPort).sync();
		} catch (Exception e) {
			mLogger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Shut Down server.
	 */
	public void shutDown() {
		mBossGroup.shutdownGracefully();
		mWorkGroup.shutdownGracefully();
	}
	
}
