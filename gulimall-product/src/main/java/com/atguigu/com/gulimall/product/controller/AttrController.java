package com.atguigu.com.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;


import com.atguigu.com.gulimall.product.vo.AttrRespVo;
import com.atguigu.com.gulimall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atguigu.com.gulimall.product.entity.AttrEntity;
import com.atguigu.com.gulimall.product.service.AttrService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;



/**
 * 商品属性
 *
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-06 19:25:42
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // //@RequirePermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 查询基本属性的功能 分页
     * @param params
     * @param catalogId
     * @return
     */
//attr/base/list/0?t=1654929400416&page=1&limit=10&key=
    @GetMapping("base/list/{catalogId}")
    public R baseList(@RequestParam Map<String, Object> params,@PathVariable("catalogId") Long catalogId){
        PageUtils page = attrService.queryAttrPage(params,catalogId);
        return R.ok().put("page",page);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
   // //@RequirePermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
		//AttrEntity attr = attrService.getById(attrId);
        AttrRespVo attrRespVo = attrService.getAttrInfo(attrId);
        return R.ok().put("attr",attrRespVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  //@RequirePermissions("product:attr:save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  //@RequirePermissions("product:attr:update")
    public R update(@RequestBody AttrVo attrVo){
		//attrService.updateById(attrVo);
        Integer counts = attrService.updateAttr(attrVo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // //@RequirePermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

    /**
     * 销售属性
     *
     */
    @GetMapping("/sale/list/{catelogId}")
    public R saleList(@PathVariable("catelogId") Long catelogId,
                      @RequestParam Map<String, Object> params){
        PageUtils pageUtils = attrService.querySalePage(catelogId,params);
        return R.ok().put("page",pageUtils);
    }
}
