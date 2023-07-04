package com.cf.service;

import com.cf.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    /*查询部门所有数据*/
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);


    Dept findById(Integer id);

    void update(Dept dept);
}
