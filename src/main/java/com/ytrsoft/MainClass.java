package com.ytrsoft;

import com.ytrsoft.util.ProcessKit;

public class MainClass {
    public static void main(String[] args) {
        ProcessKit.forList().forEach(System.out::println);
    }
}
