package com.yinjing.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinjing.dao.TravelGroupDao;
import com.yinjing.entity.PageResult;
import com.yinjing.entity.QueryPageBean;
import com.yinjing.pojo.TravelGroup;
import com.yinjing.pojo.TravelItem;
import com.yinjing.service.TraveGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author尹晶
 * @Date2022/3/2 23:53
 * @Version 1.0
 */
@Service(interfaceClass = TraveGroupService.class)
@Transactional
public class TraveGroupServiceImpl implements TraveGroupService {
    @Autowired
    private TravelGroupDao travelGroupDao;


    @Override
    public void add(TravelGroup travelGroup, Integer[] travelItemIds) {
        //新增跟图游信息，返回新增id值
        travelGroupDao.add(travelGroup);
        //批量存入中间表
        setTraveGroupAndTravelItem(travelGroup.getId(), travelItemIds);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page page = travelGroupDao.findPage();
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public TravelGroup findById(Integer id) {
        return travelGroupDao.findById(id);

    }

    @Override
    public void update(TravelGroup travelGroup) throws Exception {
        travelGroupDao.update(travelGroup);
    }

    @Override
    public Integer[] findTravelItemsByGroupId(Integer groupId) {
        return travelGroupDao.findTravelItemsByGroupId(groupId);

    }

    @Override
    public List<TravelGroup> findAll() throws Exception {
        return travelGroupDao.findAll();

    }


    public void setTraveGroupAndTravelItem(Integer travelGroupId, Integer[] travelItemIds) {
        if (travelItemIds != null && travelItemIds.length > 0) {
            for (Integer travelItemId :
                    travelItemIds) {
                // 如果有多个参数使用map
                Map<String, Integer> map = new HashMap<>();
                map.put("travelGroup", travelGroupId);
                map.put("travelItem", travelItemId);
                travelGroupDao.bathAddGroupAndItem(map);

            }

        }

    }

}
