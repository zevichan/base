package com.czw.toolkit.netty.simple;

import com.czw.toolkit.netty.simple.stream.StreamTimeHandler;
import com.czw.toolkit.netty.simple.stream.TimeDecoder;
import com.czw.toolkit.netty.simple.stream.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author ZeviChen , 2017/4/25 10:02
 */
public class SimpleServer {
    private int port;

    public SimpleServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(1);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    //配置 Channel
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //时间handler
//                            ch.pipeline().addFirst(new TimeDecoder(),new StreamTimeHandler());
                            // 注册handler
                            ch.pipeline().addLast(new TimeDecoder(),new TimeServerHandler());

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();

            // 等待服务器 socket 关闭
            f.channel().closeFuture().sync();
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        new SimpleServer(8080).run();
    }


}
