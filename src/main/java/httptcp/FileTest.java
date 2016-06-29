package httptcp;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cuihc on 2016/6/28.
 */
public class FileTest {

    @Test
    public void testRead() throws IOException {
        InputStream is = FileTest.class.getResourceAsStream("/test.txt");
        int c = 0;
        while ((c = is.read()) != -1) {
            System.out.println(String.valueOf(c));
        }
    }
}
