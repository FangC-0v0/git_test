package com.cf.service.impl;

import com.cf.mapper.EmpMapper;
import com.cf.pojo.Emp;
import com.cf.pojo.PageBean;
import com.cf.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize,
                         String name, Short gender, LocalDate begin, LocalDate end) {
/*        *//*获取总记录数*//*
        Long count = empMapper.count();
        *//*获取分页所有记录*//*
        Integer start = (page - 1)*pageSize;
        List<Emp> emps = empMapper.page(start,pageSize);*/

        PageHelper.startPage(page,pageSize);

        List<Emp>emps  = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>)emps;
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
