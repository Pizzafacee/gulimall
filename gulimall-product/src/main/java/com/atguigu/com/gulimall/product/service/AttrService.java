package com.atguigu.com.gulimall.product.service;

import com.atguigu.com.gulimall.product.vo.AttrRespVo;
import com.atguigu.com.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.com.gulimall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-06 19:25:42
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryAttrPage(Map<String, Object> params, Long catalogId);

    AttrRespVo getAttrInfo(Long attrId);

    Integer updateAttr(AttrVo attrVo);

    PageUtils querySalePage(Long catelogId, Map<String, Object> params);

}

