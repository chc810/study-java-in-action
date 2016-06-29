package httptcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cuihc on 2016/6/27.
 */
public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private ServerSocket serverSocket;

    private HttpServlet httpServlet;

    private static final ExecutorService pool = Executors.newCachedThreadPool();

    public HttpServer(HttpServlet httpServlet, int port) {
        try {
            this.httpServlet = httpServlet;
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.error("server listen failed");
            System.exit(0);
        }
    }

    public void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                pool.execute(new SocketHandler(socket));
            } catch (IOException e) {
                logger.error("server has exception " + e.getMessage(), e);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                }
            }
        }
    }

    final class SocketHandler implements Runnable {
        private Socket socket;

        public SocketHandler(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            InputStream is = null;
            OutputStream os = null;
            try {
                is = socket.getInputStream();
                os = socket.getOutputStream();
                httpServlet.service(is,os);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }
}
