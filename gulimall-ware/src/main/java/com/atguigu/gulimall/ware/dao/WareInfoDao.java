package com.atguigu.gulimall.ware.dao;

import com.atguigu.gulimall.ware.entity.WareInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息
 * 
 * @author wangkang
 * @email wangkang@gmail.com
 * @date 2022-05-08 14:08:49
 */
@Mapper
public interface WareInfoDao extends BaseMapper<WareInfoEntity> {
	
}
