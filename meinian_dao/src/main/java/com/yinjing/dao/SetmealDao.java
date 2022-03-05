package com.yinjing.dao;

import com.github.pagehelper.Page;
import com.yinjing.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SetmealDao {
    void add(Setmeal setmeal);

    void setSetmealAndTravelGroup(Map map);

    Page findAll(@Param("value") String value);
}
