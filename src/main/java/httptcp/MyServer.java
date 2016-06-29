package httptcp;

/**
 * Created by cuihc on 2016/6/28.
 */
public class MyServer {

    public static void main(String[] args) {
        HttpServlet servlet = new HttpServlet();
        HttpServer server = new HttpServer(servlet,30000);
        server.start();
    }
}
