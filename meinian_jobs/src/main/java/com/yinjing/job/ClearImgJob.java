package com.yinjing.job;

import com.yinjing.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @Author尹晶
 * @Date2022/3/4 20:48
 * @Version 1.0
 */
public class ClearImgJob {
    @Autowired
    JedisPool jedisPool;
    public void ClearImg(){
        Set<String> pics = jedisPool.getResource().sdiff("loadpic", "usepic");

        for (String pic :
                pics) {
            QiniuUtils.deleteFileFromQiniu(pic);
            System.out.println("删除垃圾图片：" + pic);
            jedisPool.getResource().srem("loadpic",pic);
        }
    }

}
