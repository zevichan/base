package com.czw.toolkit.netty.http1.route;

import com.czw.toolkit.netty.http1.DefaultPage;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ZeviChen , 2017/7/5 15:54
 */
public abstract class Controller {
    public static final String TYPE_PLAIN = "text/plain; charset=UTF-8";
    public static final String TYPE_JSON = "application/json; charset=UTF-8";
    public static final String TYPE_HTML = "text/html; charset=UTF-8";

    protected String method;
    protected String rstType;

    protected void readRequest(FullHttpRequest request) {
        String uri = request.uri();
        System.out.println("url: " + uri);
        QueryStringDecoder q = new QueryStringDecoder(uri);
        Map<String, List<String>> params = q.parameters();
        for (Map.Entry<String, List<String>> param : params.entrySet()) {
            System.out.print(param.getKey() + " : ");
            List<String> values = param.getValue();
            System.out.print("[");
            for (String value : values) {
                System.out.print(value + " ");
            }
            System.out.println("]");
        }
    }

    protected ChannelFuture writeResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
        return writeResponse(ctx, request, response, null);
    }

    protected ChannelFuture writeResponse(ChannelHandlerContext ctx, FullHttpRequest request) {
        return writeResponse(ctx, request, null, null);
    }

    protected ChannelFuture writeResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response, HttpResponseStatus status) {
        boolean keepAlive = HttpUtil.isKeepAlive(request);
        StringBuilder content;
        DecoderResult result = request.decoderResult();
        if (result.isFailure()) {
            status = HttpResponseStatus.INTERNAL_SERVER_ERROR;

            content = DefaultPage.EMPTY;
            content.append("WITH DECODER FAILURE: ");
            content.append(result.cause());
            content.append("\r\n");
        }

        if (status == null)
            status = HttpResponseStatus.OK;

        if (null == response) {
            content = DefaultPage.INDEX;
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, TYPE_HTML);
            response.content().writeBytes(content.toString().getBytes());
        }

        if (keepAlive) {
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        //set cookies
        setCookies(request, response);

        ChannelFuture future = ctx.writeAndFlush(response);
        if (response.status().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
        return future;
    }

    protected void setCookies(FullHttpRequest request, FullHttpResponse response) {
        String strCookie = request.headers().get(HttpHeaderNames.COOKIE);
        if (strCookie != null) {
            Set<io.netty.handler.codec.http.cookie.Cookie> cookies = ServerCookieDecoder.STRICT.decode(strCookie);
            if (!cookies.isEmpty()) {
                for (io.netty.handler.codec.http.cookie.Cookie cookie : cookies) {
                    response.headers().add(HttpHeaderNames.SET_COOKIE, io.netty.handler.codec.http.cookie.ServerCookieEncoder.STRICT.encode(cookie));
                }
            }
        } else {
            response.headers().add(HttpHeaderNames.SET_COOKIE, io.netty.handler.codec.http.cookie.ServerCookieEncoder.STRICT.encode("name", "zevi"));
            response.headers().add(HttpHeaderNames.SET_COOKIE, io.netty.handler.codec.http.cookie.ServerCookieEncoder.STRICT.encode("server", "netty4.1"));
        }
    }

    public static void notFound(ChannelHandlerContext ctx, FullHttpRequest request) {
        boolean keepAlive = HttpUtil.isKeepAlive(request);
        StringBuilder content = DefaultPage.NOT_FOUND;
        DecoderResult result = request.decoderResult();
        HttpResponseStatus status = HttpResponseStatus.NOT_FOUND;
        if (result.isFailure()) {
            status = HttpResponseStatus.INTERNAL_SERVER_ERROR;

            content = DefaultPage.EMPTY;
            content.append("WITH DECODER FAILURE: ");
            content.append(result.cause());
            content.append("\r\n");
        }


        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, TYPE_HTML);
        response.content().writeBytes(content.toString().getBytes());

        if (keepAlive) {
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        String strCookie = request.headers().get(HttpHeaderNames.COOKIE);
        if (strCookie != null) {
            Set<io.netty.handler.codec.http.cookie.Cookie> cookies = ServerCookieDecoder.STRICT.decode(strCookie);
            if (!cookies.isEmpty()) {
                for (io.netty.handler.codec.http.cookie.Cookie cookie : cookies) {
                    response.headers().add(HttpHeaderNames.SET_COOKIE, io.netty.handler.codec.http.cookie.ServerCookieEncoder.STRICT.encode(cookie));
                }
            }
        }

        ChannelFuture future = ctx.writeAndFlush(response);
        if (response.status().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }


    public abstract void get(ChannelHandlerContext ctx, FullHttpRequest request);

}
