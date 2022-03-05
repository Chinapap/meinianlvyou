package com.yinjing.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yinjing.constant.MessageConstant;
import com.yinjing.entity.PageResult;
import com.yinjing.entity.QueryPageBean;
import com.yinjing.entity.Result;
import com.yinjing.pojo.Setmeal;
import com.yinjing.service.SetmealService;
import com.yinjing.utils.QiniuUtils;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

/**
 * @Author尹晶
 * @Date2022/3/4 14:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @Autowired
    JedisPool jedisPool;
    @PostMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        String fileName = null;
        try {
            //获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            // 找到.最后出现的位置
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf);
            //使用UUID随机产生文件名称，防止同名文件覆盖
            fileName = UUID.randomUUID().toString() + suffix;

            jedisPool.getResource().sadd("loadpic",fileName);
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, @RequestParam Integer[] travelgroupIds){
        try {
            setmealService.add(setmeal,travelgroupIds);
        }catch (Exception e){
            //新增套餐失败
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        //新增套餐成功
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.findPage(queryPageBean);
    }

    }

