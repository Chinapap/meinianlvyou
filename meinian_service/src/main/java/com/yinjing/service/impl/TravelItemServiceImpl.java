package com.yinjing.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinjing.constant.MessageConstant;
import com.yinjing.dao.TravelItemDao;
import com.yinjing.entity.PageResult;
import com.yinjing.entity.Result;
import com.yinjing.pojo.TravelItem;
import com.yinjing.service.TravelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author尹晶
 * @Date2022/3/2 17:07
 * @Version 1.0
 */
//发布服务 注册到zk
@Service(interfaceClass = TravelItemService.class)
@Transactional //声明式事务
public class TravelItemServiceImpl implements TravelItemService{

    @Autowired
    private TravelItemDao travelItemDao;

    @Override
    public void add(TravelItem travelItem) throws Exception{
        travelItemDao.add(travelItem);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<TravelItem> page = travelItemDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void remove(Integer id) throws  Exception{
        int row = travelItemDao.remove(id);

    }

    @Override
    public void update(TravelItem travelItem) throws Exception{
        int update = travelItemDao.update(travelItem);
    }

    @Override
    public TravelItem findById(Integer id) throws SQLException {
        return travelItemDao.findById(id);
    }

    @Override
    public List<TravelItem> findAll() throws SQLException{

        return travelItemDao.findAll();
    }


}
