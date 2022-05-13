package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-08 12:57:34
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
