import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2016/12/25.
 */
public class ServerThread extends Thread {


    //每个线程维护一个socket实例
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }


    public void run() {

        //进行通信
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    

}
