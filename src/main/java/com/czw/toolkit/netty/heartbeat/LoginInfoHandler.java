package com.czw.toolkit.netty.heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by zevi on 2017/7/3.
 */
public class LoginInfoHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器为我怎么了，我得告诉他...");
        System.out.println(new String(ByteBufUtil.getBytes((ByteBuf) msg)));
        ctx.writeAndFlush("是的，我还活着");
    }

}
