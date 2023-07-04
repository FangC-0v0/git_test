package com.cf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*分页查询结果的封装类*/
public class PageBean {
    private Long total;
    private List<Emp> rows;
}
