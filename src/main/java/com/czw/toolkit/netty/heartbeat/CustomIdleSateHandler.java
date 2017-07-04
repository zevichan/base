package com.czw.toolkit.netty.heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by zevi on 2017/7/3.
 */
public class CustomIdleSateHandler extends IdleStateHandler {


    public CustomIdleSateHandler(long readerIdleTime, long writerIdleTime, long allIdleTime, TimeUnit unit) {
        super(readerIdleTime, writerIdleTime, allIdleTime, unit);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server - 正在等client回应");
        super.channelRead(ctx, msg);
        byte[] bytes = ByteBufUtil.getBytes((ByteBuf) msg);
        System.out.println(new String(bytes));

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);

        if (evt instanceof IdleStateEvent) {

            IdleStateEvent event = (IdleStateEvent) evt;

            if (event.state().equals(IdleState.READER_IDLE)) {
                System.out.println("server - 好久没有读取数据了");
                //未进行读操作
                System.out.println("READER_IDLE");
                // 超时关闭channel
                ctx.close();

            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                System.out.println("server - 好久没有写入数据了");

            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                System.out.println("server - 太长时间啥操作都没有了");
                //未进行读写
                System.out.println("ALL_IDLE");
                // 发送心跳消息
                final ChannelFuture f = ctx.write("你还活着吗");
//                f.addListener(ChannelFutureListener.CLOSE);

            }

        }

    }
}
