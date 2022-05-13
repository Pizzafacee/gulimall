package com.atguigu.gulimall.order.service;

import com.atguigu.gulimall.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-08 14:02:06
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

