package com.atguigu.gulimall.order.service;

import com.atguigu.gulimall.order.entity.OrderSettingEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-08 14:02:06
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

