package com.yinjing.service;

import com.yinjing.entity.PageResult;
import com.yinjing.entity.QueryPageBean;
import com.yinjing.entity.Result;
import com.yinjing.pojo.TravelGroup;
import com.yinjing.pojo.TravelItem;

import java.util.List;

public interface TraveGroupService {
    void add(TravelGroup travelGroup, Integer[] travelItemIds);

    PageResult findPage(QueryPageBean queryPageBean);

    TravelGroup findById(Integer id);

    void update(TravelGroup travelGroup) throws Exception;

    Integer[] findTravelItemsByGroupId(Integer groupId);

    List<TravelGroup> findAll() throws Exception;
}
