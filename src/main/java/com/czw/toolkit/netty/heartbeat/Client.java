package com.czw.toolkit.netty.heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * Created by zevi on 2017/7/3.
 */
public class Client {

    public final static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                            ch.pipeline().addLast(new LoginInfoHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);

            // 启动客户端
            ChannelFuture cf = b.connect("localhost", 1000).sync();

//            cf.addListener(future -> {
//                if (future.isSuccess()) {
//                    System.out.println("重新连接服务器成功");
//                } else {
//                    System.out.println("重新连接服务器失败");
//                    //doConnection();
//                }
//            });

//            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }


}
