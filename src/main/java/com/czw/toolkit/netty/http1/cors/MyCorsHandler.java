package com.czw.toolkit.netty.http1.cors;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

/**
 * Created by zevi on 2017/7/9.
 */
public class MyCorsHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        System.out.println("MyCorsHandler.channelRead0: " + msg.content().toString());
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.content().writeBytes("{'rst':'ok'}".getBytes());
        response.headers().set("cutom-cors-header", "cors");
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"application/json");

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
