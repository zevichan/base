package com.czw.toolkit.netty.example1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by zevi on 2017/7/4.
 */
public class Server {


    public final static void main(String[] args){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(boss,work).channel(ServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024))
                                .addLast(new StringDecoder());
                                //.addLast(new CustomHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG,1024);

        try {
            ChannelFuture f = b.bind(1000).sync();
            f.addListener(ChannelFutureListener.CLOSE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }


    }

}
