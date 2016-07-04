package httpniotcp;

/**
 * Created by cuihc on 2016/7/4.
 */
public class MyServer {

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(20000);
        httpServer.start();
    }
}
