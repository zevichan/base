package com.czw.toolkit.netty.websocket;

import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;

import java.net.URI;


/**
 * @author ZeviChen , 2017/7/6 16:23
 */
@ChannelHandler.Sharable
public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {

    private final WebSocketClientHandshaker handshaker;
    private ChannelPromise handshakeFuture;

    public WebSocketClientHandler(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    public ChannelFuture handshakeFuture() {
        return handshakeFuture;
    }

    public WebSocketClientHandler() {
        handshaker = WebSocketClientHandshakerFactory.newHandshaker(URI.create("ws://localhost/websocket"),
                WebSocketVersion.V13, null, true, new DefaultHttpHeaders());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object frame) throws Exception {
        System.out.println("msg: " + frame);
        handshakeFuture = ctx.newProgressivePromise();

        if (!handshaker.isHandshakeComplete()) {
            handshaker.finishHandshake(ctx.channel(), (FullHttpResponse) frame);
            System.out.println("WebSocket Client connected!");
            handshakeFuture.setSuccess();
            return;
        }

        if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
            System.out.println("WebSocket Client received message: " + textFrame.text());
        } else if (frame instanceof PongWebSocketFrame) {
            System.out.println("WebSocket Client received pong");
        } else if (frame instanceof CloseWebSocketFrame) {
            System.out.println("WebSocket Client received closing");
            ctx.channel().closeFuture();
        }
    }
}
