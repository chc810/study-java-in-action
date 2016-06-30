package httptcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by cuihc on 2016/6/28.
 */
public class HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static int crlf13 = 13; // '\r'
    private final static int crlf10 = 10; // '\n'

    public void service(InputStream is, OutputStream os, String name) throws IOException {
        logger.info("{} request is coming........................",name);
        //读取头部
        BufferedReader br  = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        logger.info("第一行为：{}",line);
        if (line == null) {
            return;
        }
        // < Method > < URL > < HTTP Version > <\r\n>  取的是URL部分
        String resource = line.substring(line.indexOf('/'), line
                .lastIndexOf('/') - 5);
        //获得请求的资源的地址
        resource = URLDecoder.decode(resource, "utf-8");//反编码 URL 地址
        String method = new StringTokenizer(line).nextElement()
                .toString();// 获取请求方法, GET 或者 POST
        logger.info("http访问的方法为：{}", method);
        if ("get".equals(method.toLowerCase())) {
            //get方式
            line = br.readLine();
            while (line != null && !"".equals(line)) {
                logger.info(line);
                line = br.readLine();
            }
            logger.info("头部访问结束.............");
        } else {
            //其他方式
        }

        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Server: Apache/1.2.6\r\n");
        sb.append("Date: Tue, 14 Sep 1999 02:19:57 GMT\r\n");
        sb.append("Content-Type: text/html\r\n");
        sb.append("\r\n");
        sb.append("<html><head><title>test</title></head><body>test</body></html>\r\n");
        os.write(sb.toString().getBytes());
        logger.info(sb.toString());
        os.flush();
        logger.info("{} request is over ..........................", name);
/*
        int c = 0;
        int crlfNum = 0;
        List<Byte> temps = new ArrayList<Byte>();
        while ((c = is.read()) != -1) {
            System.out.println((char) c + " " + c);
            if (c == crlf13 || c == crlf10) {
                crlfNum ++;
                if (crlfNum == 4) {
                    crlfNum = 0;

                } else {
                    if (temps.size() > 0) {
                        byte[] bytes = new byte[temps.size()];
                        for (int i=0;i<temps.size();i++) {
                            bytes[i] = temps.get(i);
                        }
                        logger.info(new String(bytes));
                        temps.clear();
                    }
                }
            } else {
                temps.add((byte)c);
            }
        }*/

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

       /* StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Server: Apache/1.2.6\r\n");
        sb.append("Date: Tue, 14 Sep 1999 02:19:57 GMT\r\n");
        sb.append("Content-Type: text/html\r\n");
        sb.append("\r\n");
        sb.append("<html><head><title>你好</title></head><body>test</body></html>\r\n");
        os.write(sb.toString().getBytes());
        logger.info(sb.toString());
        os.flush();*/
    }
}
