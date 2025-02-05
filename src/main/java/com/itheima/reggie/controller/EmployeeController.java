package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.util.JwtUtil;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletResponse response, @RequestBody Employee employee, HttpSession session) {
        //检查是否已经登录
        if (session.getAttribute("employee") != null) {
            //返回当前用户信息
            return R.success((Employee) session.getAttribute("employee"));
        }

        //1、将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if (emp == null) {
            return R.error("当前账户不存在");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)) {
            return R.error("密码错误");
        }

        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }

        session.setAttribute("employee", emp);
        session.setAttribute("employeeId", emp.getId());
        session.setAttribute("employeeName", emp.getUsername());
        //设置登录过期时间为1小时
        session.setMaxInactiveInterval(60*60);
        return R.success(emp);
    }

    /**
     * 员工退出
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    //注册
    @PostMapping("/register")
    public R register(String username, String password) {
        employeeService.register(username, password);
        log.info("注册成功");
        return R.success();
    }

    //删除
    @DeleteMapping
    public R<String> delete(@RequestParam("id") Integer id) {

        employeeService.removeById(id);
        return R.success("删除成功");
    }

    @PostMapping("addOrUpdate")
    @ApiModelProperty("添加和修改")
    public R<Employee> save(@RequestBody Employee employee) {
        log.info("新增员工，员工信息:{}", employee);
        employeeService.saveOrUpdateEmployee(employee);
        return R.success(employee);
    }

    @GetMapping("/test")
    public R<Page> test(Integer page, Integer size, String username) {
        log.info("page = {},size = {},username = {}", page, size, username);
        //构建分页构造器
        Page pageInfo = new Page(page, size);
        //构建条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(username), Employee::getUsername, username);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    //根据id查询员工信息
    @GetMapping("/{id}")
    public R<Employee> get(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        return R.success(employee);
    }


@GetMapping("/doGet")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = (Long) request.getSession().getAttribute("employeeId");

        //检查属性是否存在
        if (employeeId != null) {
            response.getWriter().println(employeeId);
        }else {
        response.getWriter().println("employeeId not found.");
        }
    }
}
