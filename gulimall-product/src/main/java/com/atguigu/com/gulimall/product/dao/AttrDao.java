package com.atguigu.com.gulimall.product.dao;

import com.atguigu.com.gulimall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性
 * 
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-06 19:25:42
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
	
}
