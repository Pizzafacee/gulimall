package com.atguigu.com.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.com.gulimall.product.entity.CategoryEntity;
import com.atguigu.com.gulimall.product.service.CategoryService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;



/**
 * 商品三级分类
 *
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-06 19:25:42
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list/tree")
   // //@RequirePermissions("product:category:list")
    public R list(){
        List<CategoryEntity> list = categoryService.listWithTree();
        return R.ok().put("data", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
   // //@RequirePermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 更新三级分类
     */
    @RequestMapping("/update")
    //  //@RequirePermissions("product:category:save")
    public R update(@RequestBody CategoryEntity category){
        categoryService.saveCascade(category);

        return R.ok();
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
  //  //@RequirePermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update/sort")
  //  //@RequirePermissions("product:category:update")
    public R update(@RequestBody CategoryEntity[] categoryEntities){
		categoryService.updateBatchById(Arrays.asList(categoryEntities));

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // //@RequirePermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds){
		//categoryService.removeByIds(Arrays.asList(catIds));
        categoryService.removeMenuByIds(Arrays.asList(catIds));
        return R.ok();
    }

}
