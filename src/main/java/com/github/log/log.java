package com.github.log;
//
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.joran.JoranConfigurator;
//import ch.qos.logback.core.joran.spi.JoranException;
//import ch.qos.logback.core.util.StatusPrinter;
//import org.apache.log4j.PropertyConfigurator;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * copy: https://my.oschina.net/yzChen/blog/758984
 */
public class log {

    private static LogEnum logEnum;

    public static void init(LogEnum logEnumInput) {
        logEnum = logEnumInput;
        if (logEnum == LogEnum.logback) {
            File file = new File("../etc/logback.xml");
            ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
//            if (iLoggerFactory instanceof LoggerContext) {
//                LoggerContext lc = (LoggerContext) iLoggerFactory;
//                JoranConfigurator configurator = new JoranConfigurator();
//                configurator.setContext(lc);
//                lc.reset();
//                try {
//                    configurator.doConfigure(file);
//                } catch (JoranException e) {
//                    e.printStackTrace();
//                }
//                StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
//            }
        }
        if (logEnum == LogEnum.log4j) {
            //System.setProperty("log4j.configuration", "D:\\BaiduNetdiskDownload\\2020-10-29\\java\\m_lodash\\etc\\log4j.properties");
            //.configure("D:\\BaiduNetdiskDownload\\2020-10-29\\java\\m_lodash\\etc\\log4j.properties");
        }
    }

    private static StackTraceElement findCaller() {
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        if (null == callStack) return null;
        StackTraceElement caller = null;
        String logClassName = log.class.getName();
        boolean isEachLogClass = false;
        for (StackTraceElement s : callStack) {
            if (logClassName.equals(s.getClassName())) {
                isEachLogClass = true;
            }
            if (isEachLogClass) {
                if (!logClassName.equals(s.getClassName())) {
                    isEachLogClass = false;
                    caller = s;
                    break;
                }
            }
        }
        return caller;
    }

    private static Logger logger() {
        StackTraceElement caller = findCaller();
        if (null == caller) return LoggerFactory.getLogger(log.class);
        // log4j2
        Logger logger;

//        if (logEnum == LogEnum.logback) {
//            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//            logger = lc.getLogger(caller.getClassName() + "." + caller.getMethodName() + "() Line: " + caller.getLineNumber());
//            return logger;
//        }

        /*
        https://blog.csdn.net/zouxucong/article/details/56013053
        if (logEnum == LogEnum.log4j) {
            logger = (Logger) org.apache.log4j.Logger.getLogger(caller.getClassName() + "." + caller.getMethodName() + "() Line: " + caller.getLineNumber());
            return logger;
        }

        if (logEnum == LogEnum.log4j2) {
            logger = (Logger) LogManager.getLogger(caller.getClassName() + "." + caller.getMethodName() + "() Line: " + caller.getLineNumber());
            return logger;
        }

         */
        // log4j和log4j2只允许出现一个！ 我们这里用整合slf4j的门面的形式
        return LoggerFactory.getLogger(caller.getClassName() + "." + caller.getMethodName() + "() Line: " + caller.getLineNumber());
    }

    public static void info(String msg) {
        logger().info(msg);
    }

    public static void info(String format, Object arg) {
        logger().info(format, arg);
    }

    public static void info(String format, Object... arguments) {
        logger().info(format, arguments);
    }

}
