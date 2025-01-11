package com.itheima.reggie.Handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MyMetaObjectHandler
 * @Description TODO
 * @Author aql
 * @Date 2025/1/11 17:48
 * @Version 1.0
 **/
@Primary
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private HttpServletRequest request;

    @Override
    public void insertFill(MetaObject metaObject) {
         // 获取当前用户ID的自定义方法
        Long userId = getCurrentUserId();
        this.setFieldValByName("createUser", userId, metaObject);
        this.setFieldValByName("updateUser", userId, metaObject); // 添加填充updateUser字段

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 获取当前用户ID的自定义方法
        Long userId = getCurrentUserId();
        this.setFieldValByName("updateUser", userId, metaObject);
    }

    private Long getCurrentUserId() {
        // 实现获取当前用户ID的逻辑，比如从session或者上下文中获取
        // 示例，返回一个示例用户ID
        return 1L;
    }
}
