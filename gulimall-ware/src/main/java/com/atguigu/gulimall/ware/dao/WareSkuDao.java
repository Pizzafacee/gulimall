package com.atguigu.gulimall.ware.dao;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-08 14:08:49
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
