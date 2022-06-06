package learn.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 吴飞群
 * @createTime 2022/06/05
 */
public class BioDemo {
    public void bio_server_1() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(100);//线程池
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("0.0.0.0", 8888));
        while(!Thread.currentThread().isInterrupted()){
            Socket socket = serverSocket.accept();
            executor.submit(new ConnectIOnHandler(socket));//为新的连接创建新的线程
        }
    }

    class ConnectIOnHandler extends Thread{
        private Socket socket;
        public ConnectIOnHandler(Socket socket){
            this.socket = socket;
        }

        public void run(){
            while(!Thread.currentThread().isInterrupted() && !socket.isClosed()){
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    InputStream inputStream = socket.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
