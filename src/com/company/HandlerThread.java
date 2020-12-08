package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandlerThread implements Runnable{
    private Socket socket;
    private BufferedReader input;
    private PrintWriter out;

    public HandlerThread(Socket client){
        socket = client;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("服务器 打开输入输出流 异常: " + e.getMessage());
        }
        new Thread(this).start();
    }

    public void sendToClient(String msg){
        out.println(msg);
    }

    @Override
    public void run() {
        try {
            String clientInputStr;
            String result = null;
            while (true) {
                // 读取客户端数据
                clientInputStr = input.readLine();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
                // 处理客户端数据
                System.out.println("客户端发过来的内容:" + clientInputStr);

                String [] arr = clientInputStr.split("\\s+");
                switch (arr[0]) {
                    case "check":
                        break;
                    case "reg":
                        break;
                    case "login":
                        break;
                    case "selectH":
                        break;
                    case "DAKA":
                        break;
                    case "getDAKAList":
                        break;
                    default:
                        result = "Wrong input type";
                        break;
                }
                //返回消息
                sendToClient(result);
            }
        } catch (Exception e) {
            System.out.println("服务器 run 异常: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    input = null;
                    System.out.println("服务端 finally 异常:" + e.getMessage());
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    socket = null;
                    System.out.println("服务端 finally 异常:" + e.getMessage());
                }
            }

        }
    }
}
