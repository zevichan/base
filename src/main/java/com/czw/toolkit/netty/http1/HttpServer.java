package com.czw.toolkit.netty.http1;

import com.czw.toolkit.netty.http1.route.Router;
import com.czw.toolkit.netty.http1.web.IndexController;
import com.czw.toolkit.netty.http1.web.InfoController;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author ZeviChen , 2017/7/5 11:21
 */
public class HttpServer {

    public static void main(String[] args) {

        Router.register("/", new IndexController());
        Router.register("/info",new InfoController());


        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(boss, work).channel(NioServerSocketChannel.class)
                .childHandler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
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

                        //添加权限处理器UserAuthHandler

                        ch.pipeline().addLast("fileServerHandler",
                                new HttpServerHandler());
                    }
                });
        try {
            ChannelFuture f = b.bind("localhost", 80).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }


    }


}
