package com.example.springbootdemo.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ***GOOD LUCK****
 *
 * @Author : Wukn
 * @Date : 2018/6/
 */

public class HelloWord {


    Logger logger = LoggerFactory.getLogger( HelloWord.class );





    public void test0() {
        System.out.println("hellp word!!");
        /**
         * 日志级别   由低到高  54321
         * 可以调整日志的级别
         *
         * springboot默认给我们使用的是infor级别，就是智慧输出infor
         */
        logger.trace( "跟踪轨迹5" );
        logger.debug( "debug信息4");
        logger.info( "infor3" );
        logger.warn( "警告2" );
        logger.error( "error1" );


    }














}
