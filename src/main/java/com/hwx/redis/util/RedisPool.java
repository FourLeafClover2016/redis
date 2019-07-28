package com.hwx.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 连接池
 *
 * @author: Huawei Xie
 * @date: 2019/6/11
 */
public class RedisPool {
    /**
     * redis 连接池
     */
    private static JedisPool pool;

    /**
     * 最大连接数
     */
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperties("redis.pool.max-totla"));
    /**
     * 最大空闲实例个数
     */
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperties("redis.pool.max-idle"));
    /**
     * 最小空闲实例个数
     */
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperties("redis.pool.min-idle"));
    /**
     * 在borrow一个jedis实例的时候，是否进行验证操作，如果赋值为true，
     * 得到的jedis实例肯定是可以用的
     */
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperties("redis.testOnBorrow"));

    /**
     * 在return一个jedis实例时，是否要进行验证操作，如果赋值为true
     * 则放回jedispool实例肯定是可以用的
     */
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperties("redis.testOnReturn"));

    /**
     * redis 主机地址
     */
    private static String redisIP = PropertiesUtil.getProperties("redis.host");

    /**
     * redis 端口
     */
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperties("redis.port"));

    /**
     * 超时时间
     */
    private static int timeout = Integer.parseInt(PropertiesUtil.getProperties("redis.timeout"));

    static {
        initPool();
    }

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        // 连接耗尽时，是否阻塞，false会抛出异常，true阻塞到超时，默认为true
        config.setBlockWhenExhausted(true);
        pool = new JedisPool(config, redisIP, redisPort, timeout);

    }


    /**
     * 获取redis连接
     *
     * @return
     */
    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis) {
        pool.returnBrokenResource(jedis);
    }

}
