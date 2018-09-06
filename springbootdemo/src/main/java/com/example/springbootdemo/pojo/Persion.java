package com.example.springbootdemo.pojo;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * ***GOOD LUCK****
 *
 * @Author : Wukn
 * @Date : 2018/6/
 *
 * 将配置文件中的的每一个属性的值，映射到这个组建中
 *@ConfigurationProperties
 * prefix = "persion"   指定在配置文件中需要将persion的配置属性映射到这个实体类中
 */

/**
 * 获取指定配置文件
 * @PropertySource( value = {"classpath:coms.properties"})
 */

@Component
/**
 * @ConfigurationProperties(prefix = "persion"),默认获取根目录下的值
 */
@ConfigurationProperties(prefix = "persion")
public class Persion {


    private String name;
    private Integer id;
    private Boolean bool;


    public Persion() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    @Override
    public String toString() {
        return JSON.toJSONString( this );
    }
}
