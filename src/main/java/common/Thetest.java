package common;

import org.junit.Test;

/**
 * Thetest
 *
 * @author cuihc
 * @date 2016/7/12
 */
public class Thetest {

    @Test
    public void test() {
        int i0 = 1 << 0;
        int i1 = 1<<1;
        int i2 = 1<<2;
        int i3 = 1<<3;
        int i4 = 1<<4;
        int i5 = 1 << -1;
        System.out.println(i0);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i5);

    }
}
