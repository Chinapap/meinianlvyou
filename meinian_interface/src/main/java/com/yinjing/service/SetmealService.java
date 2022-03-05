package com.yinjing.service;

import com.yinjing.entity.PageResult;
import com.yinjing.entity.QueryPageBean;
import com.yinjing.pojo.Setmeal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SetmealService {
    void add(Setmeal setmeal, Integer[] travelgroupIds) throws  Exception;


    PageResult findPage(QueryPageBean queryPageBean);
}
