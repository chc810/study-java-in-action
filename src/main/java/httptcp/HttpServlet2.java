package httptcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by cuihc on 2016/6/28.
 */
public class HttpServlet2 extends AbstractHttpServlet{


    public void service(Socket socket, InputStream is, OutputStream os, String name) throws IOException {

        int c = 0;
        int crlfNum = 0;
        int l = is.available();
        logger.info("长度为：{}" , l);
        byte[] bytes = new byte[l];
        is.read(bytes);


        String s = new String(bytes);
        logger.info("信息为:{}" ,s);


//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
      /*  String line = "";
        int num = 0;
        while((line = br.readLine()) != null) {
            num ++;
            logger.info(line);
            if (num >= 9) {

            }

        }
        if (num >= 9) {
            StringBuffer sb = new StringBuffer();
            sb.append("HTTP/1.1 200 OK/r/n");
            sb.append("Server: Apache/1.2.6/r/n");
            sb.append("Date: Tue, 14 Sep 1999 02:19:57 GMT/r/n");
            sb.append("Content-Type: text/html/r/n");
            sb.append("/r/n");
            sb.append("<html><head><title>sws</title></head><body>test</body></html>/r/n");
            os.write(sb.toString().getBytes());
            logger.info(sb.toString());
            os.flush();
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("HTTP/1.1 200 OK/r/n");
            os.write(sb.toString().getBytes());
            logger.info(sb.toString());
            os.flush();
        }*/

        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Server: Apache/1.2.6\r\n");
        sb.append("Date: Tue, 14 Sep 1999 02:19:57 GMT\r\n");
        sb.append("Content-Type: text/html\r\n");
        sb.append("\r\n");
        sb.append("<html><head><title>你好</title></head><body>test</body></html>\r\n");

        os.write(sb.toString().getBytes());
        logger.info(sb.toString());
        os.flush();
    }


    private String readLine(InputStream is, int contentLength) throws IOException {
        if (contentLength <= 0) {
            StringBuffer sb = new StringBuffer();
            int r = is.read();
            while (r != crlf13) {
                sb.append((char)r);
                r = is.read();
            }
            is.read(); //多读一个
            return sb.toString();
        } else {
            byte[] bytes = new byte[contentLength];
            is.read(bytes);
            return new String(bytes);
        }

    }


}
