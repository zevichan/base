package com.czw.toolkit.netty.http1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.net.Socket;

/**
 * @author ZeviChen , 2017/7/5 13:04
 */
public class HttpClient {

    public static void main(String[] args) {

        EventLoopGroup work = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(work).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("http-decoder",
                                new HttpRequestDecoder());//生成HttpRequest,HttpResponse,HttpContent,LastHttpContent
                        ch.pipeline().addLast("http-aggregator",
                                new HttpObjectAggregator(65536));//将生成的多个Http对象组合起来,FullHttpRequest,FullHttpResponse
                        ch.pipeline().addLast("http-encoder",
                                new HttpResponseEncoder());//响应解码器，用于给客户端返回response
                        ch.pipeline().addLast("http-chunked",
                                new ChunkedWriteHandler());//支持发送大的码流,如大的文件
                        ch.pipeline().addLast(new HttpClientHandler());
                    }
                })
                .option(ChannelOption.SO_KEEPALIVE, true);
        try {
            ChannelFuture ch = b.connect("localhost", 80).sync();
            ch.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            work.shutdownGracefully();
        }


    }


}
