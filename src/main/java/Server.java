import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 * Created by Administrator on 2016/12/25.
 */
public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);

            Socket socket = serverSocket.accept();

            System.out.println("服务器端以启动，等待客户端连接");

            //获取客户端发送的信息
            InputStream inputStream = socket.getInputStream();
            //对字符流进行包装成字节流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println("客户端说：" + info);
            }

            socket.isInputShutdown();

            //给客户端发消息
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("服务器说：欢迎你");
            printWriter.flush();
            socket.shutdownOutput();

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();

            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
