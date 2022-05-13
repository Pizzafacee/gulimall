package com.atguigu.gulimall.order.service;

import com.atguigu.gulimall.order.entity.RefundInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;

import java.util.Map;

/**
 * 退款信息
 *
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-08 14:02:06
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

