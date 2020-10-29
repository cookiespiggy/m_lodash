package com.github;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

public class App3 {
    public static void main(String[] args) {

        ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();

        System.out.println(iLoggerFactory);


    }
}
