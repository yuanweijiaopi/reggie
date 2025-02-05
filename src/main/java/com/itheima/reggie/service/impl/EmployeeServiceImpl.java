package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void register(String username,String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        Employee employee = new Employee();
        //加密
        // 密码加密
        String md5String = DigestUtils.md5DigestAsHex(password.getBytes());
        // 设置用户名
        employee.setUsername(username);
        // 设置加密后的密码
        employee.setPassword(md5String);
           employeeMapper.register(employee);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateEmployee(@RequestBody Employee employee) {
        
        if (employee.getPassword() == null || employee.getPassword().isEmpty()) {
            employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        }
        if (employee.getUsername() == null || employee.getUsername().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        saveOrUpdate(employee);
    }


    public Employee getEmployee(@PathVariable Long id) {
        Employee employee = employeeMapper.selectById(id);
        if (employee == null) {
            throw new RuntimeException("当前对象不存在");
        }
        return employee;
    }
}

