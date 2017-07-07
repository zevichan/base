package com.czw.toolkit.netty.websocket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.ssl.SslContext;

/**
 * @author ZeviChen , 2017/7/7 15:58
 */
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    private static final String WEBSOCKET_PATH = "/websocket";

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());//(1)
        pipeline.addLast(new HttpObjectAggregator(65536));//(2)
        pipeline.addLast(new WebSocketServerCompressionHandler());//(3)
//        pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH));//无效
        pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
//        pipeline.addLast(new WebSocketIndexPageHandler(WEBSOCKET_PATH));
        pipeline.addLast(new WebSocketFrameHandler());
    }
}
