package com.hwx.redis.util;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * redis 连接池工具类
 * @author: Huawei Xie
 * @date: 2019/7/28
 */
@Slf4j
public class RedisPoolUtil {

    /**
     * key value存入redis
     *
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * key value存入redis时设置有效期 单位秒
     *
     * @param key
     * @param value
     * @param exTime
     * @return
     */
    public static String setEx(String key, String value, int exTime) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 从redis中获取key的value
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = RedisPool.getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            RedisPool.returnBrokenResource(jedis);
            return value;
        }
        RedisPool.returnResource(jedis);
        return value;
    }

    /**
     * 重置key的有效期
     *
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key, int exTime) {
        Jedis jedis = null;
        Long value = null;
        try {
            jedis = RedisPool.getJedis();
            value = jedis.expire(key, exTime);
        } catch (Exception e) {
            RedisPool.returnBrokenResource(jedis);
            return value;
        }
        RedisPool.returnResource(jedis);
        return value;
    }

    /**
     * 在redis中删除key
     *
     * @param key
     * @return
     */
    public static Long del(String key) {
        Jedis jedis = null;
        Long value = null;
        try {
            jedis = RedisPool.getJedis();
            value = jedis.del(key);
        } catch (Exception e) {
            RedisPool.returnBrokenResource(jedis);
            return value;
        }
        RedisPool.returnResource(jedis);
        return value;
    }
}
