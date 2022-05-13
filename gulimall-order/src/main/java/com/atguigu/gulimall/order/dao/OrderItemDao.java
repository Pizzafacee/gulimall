package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-08 14:02:06
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
