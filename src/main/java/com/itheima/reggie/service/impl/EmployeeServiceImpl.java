package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployeeService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void register(String username,String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        Employee employee = new Employee();
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(1L);
        employee.setUpdateUser(1L);
        //加密
        // 密码加密
        String md5String = DigestUtils.md5DigestAsHex(password.getBytes());
        // 设置用户名
        employee.setUsername(username);
        // 设置加密后的密码
        employee.setPassword(md5String);
           employeeMapper.register(employee);
    }
}
