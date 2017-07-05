package com.czw.toolkit.netty.http1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author ZeviChen , 2017/7/5 14:18
 */
public class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        System.out.println("client running...");
    }
}
