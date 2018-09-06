package com.example.springbootdemo.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * ***GOOD LUCK****
 *
 * @Author : Wukn
 * @Date : 2018/6/
 */
public class TestDemo {



    @Autowired
    private ApplicationContext context;




    @Test
    public void test1() {
        System.out.println("s");
    }
}
