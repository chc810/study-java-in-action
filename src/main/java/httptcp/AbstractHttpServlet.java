package httptcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * Created by cuihc on 2016/7/1.
 */
abstract class AbstractHttpServlet {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final static int crlf13 = 13; // '\r'
    protected final static int crlf10 = 10; // '\n'

    abstract  public  void service(Socket socket, InputStream is, OutputStream os, String name) throws Exception;
}
