package com.yinjing.dao;

import com.github.pagehelper.Page;
import com.yinjing.pojo.TravelGroup;
import com.yinjing.pojo.TravelItem;

import java.util.List;
import java.util.Map;

public interface TravelGroupDao {
    Integer add(TravelGroup travelGroup);

    void bathAddGroupAndItem(Map map);

    Page<TravelGroup> findPage();

    TravelGroup findById(Integer id);

    void update(TravelGroup travelGroup);

    Integer[] findTravelItemsByGroupId(Integer groupId);

    List<TravelGroup> findAll();
}
