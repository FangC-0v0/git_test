package com.cf.mapper;

import com.cf.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    /*查询全部部门数据*/
    @Select("select * from dept")
    List<Dept> list();
    /*根据ID删除部门*/
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    /*添加部门*/
    @Insert("insert into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /*通过id查找部门*/
    @Select("select * from dept where id = #{id}")
    Dept findById(Integer id);
    /*更新部门信息*/
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
