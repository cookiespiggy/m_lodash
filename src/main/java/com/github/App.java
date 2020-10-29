package com.github;

import com.github.log.LogEnum;
import com.github.log.log;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        // init
        log.init(LogEnum.log4j);

        log.info("info1");
        log.info("{}", "info2");

    }
}
