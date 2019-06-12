package com.hwx.redis.resis;

import com.hwx.redis.RedisApplication;
import com.hwx.redis.common.RedisPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.Jedis;

/**
 * @author: Huawei Xie
 * @date: 2019/6/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@WebAppConfiguration
public class RedisTest {
    @Autowired
    private RedisPool redisPool;

    @Test
    public void test() {
        Jedis jedis = redisPool.getJedis();
        jedis.set("hwx", "23");
        jedis.close();
    }
}
