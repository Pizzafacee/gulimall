package com.atguigu.com.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.com.gulimall.product.dao.AttrGroupDao;
import com.atguigu.com.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.com.gulimall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catalogId) {
        IPage<AttrGroupEntity> page = null;
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(params.get("key"))) {
            queryWrapper.and((wrapper) ->
                wrapper.like("attr_group_id", params.get("key")).or().like("descript", params.get("key")));
            }
            if (catalogId == 0) {
                page = this.page(new Query<AttrGroupEntity>().getPage(params), queryWrapper);
            } else {
                //select * from attrGroupEntity where catalog_id = catalogId and (.. or ..)

                queryWrapper.eq("catelog_id", catalogId);

                page = this.page(new Query<AttrGroupEntity>().getPage(params), queryWrapper);
            }

            return new PageUtils(page);
        }


    }