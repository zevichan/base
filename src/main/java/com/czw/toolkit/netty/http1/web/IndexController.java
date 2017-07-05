package com.czw.toolkit.netty.http1.web;

import com.czw.toolkit.netty.http1.DefaultPage;
import com.czw.toolkit.netty.http1.route.Controller;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

/**
 * @author ZeviChen , 2017/7/5 16:25
 */
public class IndexController extends Controller {


    @Override
    public void get(ChannelHandlerContext ctx, FullHttpRequest request) {
        System.out.println("IndexController ...");

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, TYPE_HTML);
        response.content().writeBytes(DefaultPage.INDEX.toString().getBytes());
        readRequest(request);
        writeResponse(ctx, request, response);


    }
}
