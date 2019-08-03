package com.retailers.dht.com;

import org.junit.Test;

public class test {
    @Test
    public void test1() {
        String s = "D:\\Android\\sdk\\add-ons";

        while(s.lastIndexOf("\\") > 0) {
            s = s.substring(0, s.lastIndexOf("\\"));
            System.out.println(s);
        }

    }


    @Test
    public void test2() {
        String s = "abcdefgdadfa";

        String substring = s.substring(5, 6);
        System.out.println(substring);


    }

    @Test
    public void test3() {
        String s = "abc";

        System.out.println("/" +  s);

    }
}
