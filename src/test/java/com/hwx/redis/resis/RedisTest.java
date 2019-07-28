package com.hwx.redis.resis;

import com.hwx.redis.RedisApplication;
import com.hwx.redis.util.RedisShardedPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.ShardedJedis;

/**
 * @author: Huawei Xie
 * @date: 2019/6/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@WebAppConfiguration
public class RedisTest {


    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            ShardedJedis jedis = RedisShardedPool.getJedis();
            System.out.println(jedis.get("key"+i));
            RedisShardedPool.returnResource(jedis);
        }
    }
}
