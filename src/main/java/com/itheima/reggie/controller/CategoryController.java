package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author aql
 * @Date 2025/2/5 12:00
 * @Version 1.0
 **/

//分类管理
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    //新增分类
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("category:{}", category);
        if (categoryService.count(new LambdaQueryWrapper<Category>().eq(Category::getName, category.getName())) > 0) {
            return R.error("当前已存在相同的菜名");
        }
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    //分页查询
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        //分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //条件构造器对象
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Category::getSort);

        //进行分页查询
        categoryService.page(pageInfo, wrapper);
        return R.success(pageInfo);
    }

    //删除分类
    @DeleteMapping
    @Transactional(rollbackFor = Exception.class)
    public R<String> delete(@RequestParam Long id) {
        log.info("删除分类，id为:{}", id);
if (categoryService.count(new LambdaQueryWrapper<Category>().eq(Category::getId, id)) > 0) {
    categoryService.remove(id);
}else {
    throw new RuntimeException("分类不存在");
}
        return R.success("分类信息删除成功");
    }

    /**@Description: TODO methods 根据id修改分类信息
     * @param
     * @return 
     */
    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public R<String> update(@RequestBody Category category) {
        log.info("修改分类信息:{}", category);
        categoryService.updateById(category);
        return R.success("修改分类信息成功");
    }

}
