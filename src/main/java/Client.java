import java.io.*;
import java.net.Socket;

/**
 * 客户端
 * Created by Administrator on 2016/12/25.
 */
public class Client {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);

            OutputStream outputStream = socket.getOutputStream();

            PrintWriter printWriter = new PrintWriter(outputStream);

            printWriter.write("登录名是why");
            printWriter.flush();

            socket.shutdownOutput();

            //获取服务器发送的内容
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println("服务器说：" + info);
            }
            socket.shutdownInput();

            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
