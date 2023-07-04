package com.cf.controller;

import com.cf.pojo.Dept;
import com.cf.pojo.Result;
import com.cf.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService  deptService;

    //@RequestMapping(value = "/depts",method =  RequestMethod.GET)
    @GetMapping
    public Result list(){
        List<Dept> deptList = deptService.list();
        log.info("查询数据");
        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    public  Result delete(@PathVariable("id") Integer id){
        log.info("删除部门:{}",id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门:{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Integer id){
        Dept dept = deptService.findById(id);
        log.info("搜索到部门信息：{}",dept);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门");
        deptService.update(dept);
        return Result.success();
    }
}
