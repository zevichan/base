package com.czw.toolkit.netty.http1;

import com.czw.toolkit.netty.http1.route.Controller;
import com.czw.toolkit.netty.http1.route.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.CharsetUtil;

/**
 * @author ZeviChen , 2017/7/5 15:41
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        System.out.println("server running...");
        String uri = msg.uri();
        QueryStringDecoder query = new QueryStringDecoder(uri, CharsetUtil.UTF_8, true);
        String path = query.path();

        if (path.equals("/favicon.ico"))
            return;

        Controller controller = Router.getControl(path);
        System.out.println("request path: " + path);
        if (controller == null) {
            System.out.println("page not found. uri:" + path);
            Controller.notFound(ctx, msg);
            return;
        }

        controller.get(ctx, msg);
    }


}