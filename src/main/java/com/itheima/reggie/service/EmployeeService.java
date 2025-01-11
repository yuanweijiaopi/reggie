package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Employee;

/**
 * @author aql
 */
public interface EmployeeService extends IService<Employee> {
    void register(Employee employee);
}
