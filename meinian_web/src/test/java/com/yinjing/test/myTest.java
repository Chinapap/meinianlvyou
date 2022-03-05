package com.yinjing.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @Author尹晶
 * @Date2022/3/4 19:26
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-redis.xml","classpath:springmvc.xml"})
public class myTest {
    @Autowired
    JedisPool jedisPool;

    @Test
    public void testJedisPoolConn(){
        Jedis resource = jedisPool.getResource();
        System.out.println(resource.get("k1"));
    }

}
