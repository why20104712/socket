import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同时处理多个线程请求服务器端，给予TCP的socket
 * Created by Administrator on 2016/12/25.
 */
public class MultiServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();

                InetAddress inetAddress = socket.getInetAddress();
                System.out.println("主机地址：" + inetAddress.getHostAddress());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
