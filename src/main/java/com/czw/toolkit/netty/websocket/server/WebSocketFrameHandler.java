package com.czw.toolkit.netty.websocket.server;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ZeviChen , 2017/7/7 15:58
 */
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        super.channelActive(ctx);
        if (Group.recipients.add(ctx.channel()))
            Group.recipients.writeAndFlush(ctx.name() + " 加入了群聊");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        if (Group.recipients.remove(ctx.channel()))
            Group.recipients.writeAndFlush(ctx.name() + " 加入了群聊");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {

        if (frame instanceof TextWebSocketFrame) {
            String request = ((TextWebSocketFrame) frame).text();

            String dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss - "));
            System.out.println(dt + "" + ctx.channel());
            System.out.println(dt + "Service received: " + request);

            ctx.channel().writeAndFlush(new TextWebSocketFrame(request.toUpperCase(Locale.US)));
        } else {
            String message = "unsupported frame type: " + frame.getClass().getName();
            throw new UnsupportedOperationException(message);
        }
    }
}
