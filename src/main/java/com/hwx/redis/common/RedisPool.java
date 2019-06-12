package com.hwx.redis.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: Huawei Xie
 * @date: 2019/6/11
 */
@Component
public class RedisPool implements ApplicationRunner {
    /**
     * redis 连接池
     */
    private static JedisPool pool;

    /**
     * 最大连接数
     */
    @Value("${redis.pool.max-totla}")
    private Integer maxTotal;
    /**
     * 最大空闲实例个数
     */
    @Value("${redis.pool.max-idle}")
    private Integer maxIdle;
    /**
     * 最小空闲实例个数
     */
    @Value("${redis.pool.min-idle}")
    private Integer minIdle;
    /**
     * 在borrow一个jedis实例的时候，是否进行验证操作，如果赋值为true，
     * 得到的jedis实例肯定是可以用的
     */
    @Value("${redis.testOnBorrow}")
    private Boolean testOnBorrow;

    /**
     * 在return一个jedis实例时，是否要进行验证操作，如果赋值为true
     * 则放回jedispool实例肯定是可以用的
     */
    @Value("${redis.testOnReturn}")
    private Boolean testOnReturn;

    /**
     * redis 主机地址
     */
    @Value("${redis.host}")
    private String redisIP;

    /**
     * redis 端口
     */
    @Value("${redis.port}")
    private Integer redisPort;

    /**
     * 超时时间
     */
    @Value("${redis.timeout}")
    private int timeout;

    private void initPool() {
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
     * @return
     */
    public Jedis getJedis() {
        return pool.getResource();
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        initPool();
    }
}
