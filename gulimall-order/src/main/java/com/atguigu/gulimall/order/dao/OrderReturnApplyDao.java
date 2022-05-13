package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderReturnApplyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单退货申请
 * 
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-08 14:02:06
 */
@Mapper
public interface OrderReturnApplyDao extends BaseMapper<OrderReturnApplyEntity> {
	
}
