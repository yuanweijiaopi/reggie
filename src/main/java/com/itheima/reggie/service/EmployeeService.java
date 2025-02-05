package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Employee;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author aql
 */
public interface EmployeeService extends IService<Employee> {
    void register(String username,String password);

    void saveOrUpdateEmployee(Employee employee);

    Employee getEmployee(Long id);
}
