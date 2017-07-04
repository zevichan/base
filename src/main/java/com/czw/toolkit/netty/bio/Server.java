package com.czw.toolkit.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zevi on 2017/7/4.
 */
public class Server {


    public final static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(1000);
        while (true) {
            Socket socket = ss.accept();
            new Thread(new ServerHandler(socket)).start();
        }

    }
}

class ServerHandler implements Runnable {

    private Socket socket = null;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                System.out.println(line);
                pw.println("Get messages.");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (pw != null) pw.close();
            }

        }
    }
}
