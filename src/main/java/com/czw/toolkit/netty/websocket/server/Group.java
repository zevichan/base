package com.czw.toolkit.netty.websocket.server;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
 * @author ZeviChen , 2017/7/7 17:37
 */
public class Group {

    public static final ChannelGroup recipients =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
