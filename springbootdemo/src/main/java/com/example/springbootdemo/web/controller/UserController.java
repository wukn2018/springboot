package com.example.springbootdemo.web.controller;

import com.example.springbootdemo.commons.ClientResult;
import com.example.springbootdemo.commons.exception.CMSException;
import com.example.springbootdemo.entity.UserEntity;
import com.example.springbootdemo.pojo.HelloWord;
import com.example.springbootdemo.pojo.Persion;
import com.example.springbootdemo.pojo.User;
import com.example.springbootdemo.response.BaseResponse;
import com.example.springbootdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ***GOOD LUCK****
 *
 * @Author : Wukn
 * @Date : 2018/6/
 */
@RestController
@RequestMapping(value = "user")
public class UserController {


    @Value( "${swaggerShow.show}" )
    private boolean swaggerShow;


    @Value( "${str}" )
    private String s;

    @Value( "#{1+2}" )
    private Integer da;



    @Autowired
    private UserService userService;

    @Autowired
    private Persion persion;


    @Autowired
    private HelloWord helloWord;

    Logger logger = LoggerFactory.getLogger( HelloWord.class );


    /**
     * 查询
     * @param
     * @param
     * @return
     */
    @PostMapping(value = "/nameandsex")
    public ClientResult findUserByName(@Valid @RequestBody(required = false) User user, BindingResult bindingResult)
            throws Exception{
        try {
            System.out.println(s);
            System.out.println(persion);
            helloWord.test0();
            return new ClientResult( userService.finaUserByNAME( user.getName(),user.getSex() ) );
        } catch (CMSException e) {
            return new ClientResult( e.getCode(),e.getMsg());
        }

    }













}
