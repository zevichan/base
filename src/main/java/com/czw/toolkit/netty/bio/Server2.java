package com.czw.toolkit.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 伪异步IO
 *
 * 一个接口如果请求时间过长也是阻塞IO
 * Created by zevi on 2017/7/4.
 */
public class Server2 {

    public final static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1000);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        while (true) {
            Socket socket = ss.accept();
            executorService.execute(new ServerHandler(socket));
        }

    }


}
