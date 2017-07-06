package com.czw.toolkit.netty.websocket;

import com.czw.toolkit.netty.http1.DefaultPage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.util.CharsetUtil;

import java.util.Set;

/**
 * @author ZeviChen , 2017/7/6 15:44
 */
public class WebSocketIndexPage extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

        if (!request.decoderResult().isSuccess()) {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        if (request.method() != HttpMethod.GET) {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN));
            return;
        }

        QueryStringDecoder query = new QueryStringDecoder(request.uri());
        if (!"/".equalsIgnoreCase(query.path())) {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND));
            return;
        }

        sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                Unpooled.copiedBuffer(DefaultPage.WEBSOCKET.toString().getBytes())));

    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // Generate an error page if response getStatus code is not OK (200).
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }

//        setCookies(req, res);

        res.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
        // Send the response and close the connection if necessary.
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        f.addListener(ChannelFutureListener.CLOSE);
//        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
//            f.addListener(ChannelFutureListener.CLOSE);
//        }

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
}
