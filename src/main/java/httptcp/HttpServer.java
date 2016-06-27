package httptcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cuihc on 2016/6/27.
 */
public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private ServerSocket serverSocket;

    private static final ExecutorService pool = Executors.newCachedThreadPool();

    public HttpServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.error("server listen failed");
            System.exit(0);
        }
    }

    public ServerSocket getServerSocket() {
        try {
            serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.serverSocket;
    }
}
