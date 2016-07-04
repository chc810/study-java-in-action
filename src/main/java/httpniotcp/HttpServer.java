package httpniotcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(httptcp.HttpServer.class);

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    public HttpServer(int port) {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            //设置通道为非阻塞
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            logger.error("服务启动错误，" + e.getMessage(), e);
            System.exit(-1);
        }


    }

    public void start() {
        new WorkThread().start();
    }

    private final class WorkThread extends Thread {

        public void run() {
            while (true) {
                try {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    logger.info("SelectionKey size : {}", keys.size());
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while(iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        logger.info("the selectionKey : {}",key);
                        if (key.isAcceptable()) {
                            logger.info("Server: SelectionKey is acceptable");
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(key.selector(), SelectionKey.OP_READ);
                        } else if (key.isReadable()) {
                            logger.info("Server : SelectionKey is readable");
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(5*1024);
                            int readBytes = socketChannel.read(byteBuffer);
                            logger.info("Server: receiver bytes numbers:  " + readBytes);
                            while(readBytes != -1 && readBytes != 0) {
                                logger.info("Server: readBytes = " + readBytes);
                                logger.info("Server: data = " + new String(byteBuffer.array(), 0, readBytes));
                                byteBuffer.clear();
                                readBytes = socketChannel.read(byteBuffer);
                            }
                            StringBuffer sb = new StringBuffer();
                            sb.append("HTTP/1.1 200 OK\r\n");
                            sb.append("Server: Apache/1.2.6\r\n");
                            sb.append("Date: Tue, 14 Sep 1999 02:19:57 GMT\r\n");
                            sb.append("Content-Type: text/html\r\n");
                            sb.append("\r\n");
                            sb.append("<html><head><title>test</title></head><body>test</body></html>\r\n");
                            byteBuffer.flip();
                            byteBuffer.put(sb.toString().getBytes());
                            socketChannel.write(byteBuffer);
                            //注意需要关闭
                            socketChannel.close();
                            key.cancel();
                        }
                        iterator.remove();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
