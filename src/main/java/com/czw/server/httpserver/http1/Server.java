package com.czw.server.httpserver.http1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ZeviChen , 2016/10/22 16:07
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(80);
        Socket socket = server.accept();
        InputStream inputStream = socket.getInputStream();


    }

}
