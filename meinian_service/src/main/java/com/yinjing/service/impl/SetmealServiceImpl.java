package com.yinjing.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinjing.dao.SetmealDao;
import com.yinjing.entity.PageResult;
import com.yinjing.entity.QueryPageBean;
import com.yinjing.pojo.Setmeal;
import com.yinjing.service.SetmealService;
import com.yinjing.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author尹晶
 * @Date2022/3/4 15:02
 * @Version 1.0
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;


    @Override
    public void add(Setmeal setmeal, Integer[] travelgroupIds) throws Exception {
        //添加套餐游 返回自增主键
        setmealDao.add(setmeal);
        setSetmealAndTravelGroup(setmeal.getId(), travelgroupIds);
        int indexOf = setmeal.getImg().lastIndexOf("/");
        String fileName = setmeal.getImg().substring(indexOf + 1);
        jedisPool.getResource().sadd("usepic",fileName);

    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page setmealList = setmealDao.findAll(queryPageBean.getQueryString());
        return new PageResult(setmealList.getTotal(),setmealList.getResult());
    }


    public void setSetmealAndTravelGroup(Integer setmealId, Integer[] travelgroupIds) throws SQLException {
        if (travelgroupIds != null && travelgroupIds.length > 0) {
            Map map = new HashMap();

            //循环插入中间表
            for (Integer travelgroupId :
                    travelgroupIds) {
                map.put("setmealId", setmealId);
                map.put("travelgroupId", travelgroupId);
                setmealDao.setSetmealAndTravelGroup(map);

            }
        }
    }
}
