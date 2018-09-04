package com.example.springbootdemo.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ***GOOD LUCK****
 *
 * 私人代码贡献
 *
 * @Author : Wukn
 * @Date : 2018/7/17
 *
 * 缓存操作工具类
 */
@Service
public class RedisDao {
    @Autowired
    private RedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger( RedisDao.class );


    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        logger.info( "key+value{}"+"--->"+key+"::"+value );
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.info( e.getMessage() );
        }
        return result;
    }


    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    public boolean setAndTime(final String key, Object value, Long expireTime) {
        logger.info( "key+value+expireTime{}"+"--->"+key+"::"+value + "__"+expireTime );
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            if(null != expireTime) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            logger.info( e.getMessage() );
        }
        return result;
    }


    /**
     * 批量删除对应的value
     * @param keys
     */
    public void removeAllValue(final String... keys) {
        logger.info( "keys is length{}--->"+keys.length);
        if(keys.length>0 && !StringUtils.isEmpty( keys )) {
            for (String key : keys) {
                remove(key);
            }
        }
    }


    /**
     * 批量删除key
     * @param pattern
     */
    public void removeKeys(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0){
            redisTemplate.delete(keys);
        }

    }


    /**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key) {
        logger.info( "key{}--->"+key );
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }


    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        logger.info( "key{}"+key );
        return redisTemplate.hasKey(key);
    }


    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) throws Exception{
        logger.info( "key{}--->"+key );
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        if(StringUtils.isEmpty( result )) {
            throw new Exception( "缓存读取失败。。。" );
        }
        return result;
    }









    /********************************    哈希    **************************************/
    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value){
        logger.info( "{}"+key+hashKey+value );
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }


    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }










    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k,Object v){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }


    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List <Object> lRange(String k, long l, long l1){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }



    /**
     * redis的集合，是一种无序的集合，集合中的元素没有先后顺序。
     *
     *
     * 集合添加
     * @param key
     * @param value
     */
    public void add(String key,Object value){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }


    /**
     * 集合获取
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }



    public void zAdd(String key,Object value,double scoure){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
    }


    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }


}
