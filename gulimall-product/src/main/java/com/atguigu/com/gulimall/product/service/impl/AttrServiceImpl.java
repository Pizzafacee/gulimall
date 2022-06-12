package com.atguigu.com.gulimall.product.service.impl;

import com.atguigu.com.gulimall.product.constant.ProductConstant;
import com.atguigu.com.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.atguigu.com.gulimall.product.dao.AttrGroupDao;
import com.atguigu.com.gulimall.product.dao.CategoryDao;
import com.atguigu.com.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.com.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.com.gulimall.product.entity.CategoryEntity;
import com.atguigu.com.gulimall.product.service.CategoryService;
import com.atguigu.com.gulimall.product.vo.AttrRespVo;
import com.atguigu.com.gulimall.product.vo.AttrVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.com.gulimall.product.dao.AttrDao;
import com.atguigu.com.gulimall.product.entity.AttrEntity;
import com.atguigu.com.gulimall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
@Slf4j
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveAttr(AttrVo attr) {
        //保存基本信息
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.save(attrEntity);
        //保存关联关系表信息
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity entity = new AttrAttrgroupRelationEntity();
            entity.setAttrId(attrEntity.getAttrId());
            entity.setAttrGroupId(attr.getAttrGroupId());
            attrAttrgroupRelationDao.insert(entity);
        }
    }

    @Override
    public PageUtils queryAttrPage(Map<String, Object> params, Long catalogId) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        if (catalogId != 0) {
            queryWrapper.eq("catelog_id", catalogId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((wrapper) -> wrapper.eq("attr_id", key).or().like("attr_name", key));
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> attrRespVos = records.stream().map((attrEntity -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);
            //根据attrId查询catelogName
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if (categoryEntity != null) {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            //根据attrGroupId查询attrGroupName
            AttrAttrgroupRelationEntity relationEntity = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
            if (relationEntity != null) {
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(relationEntity.getAttrGroupId());
                attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
                log.info("attrRespVo{}", attrRespVo);
            }
            return attrRespVo;
        })).collect(Collectors.toList());
        pageUtils.setList(attrRespVos);
        return pageUtils;
    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        AttrRespVo attrRespVo = new AttrRespVo();
        //根据attrId查找attr属性
        AttrEntity attrEntity = attrDao.selectById(attrId);
        BeanUtils.copyProperties(attrEntity, attrRespVo);
        //查找出groupId和groupName
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity relationEntity = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
            Long attrGroupId = relationEntity.getAttrGroupId();
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupId);
            if (attrGroupEntity != null) {
                String attrGroupName = attrGroupEntity.getAttrGroupName();
                attrRespVo.setAttrGroupId(attrGroupId);
                attrRespVo.setGroupName(attrGroupName);
            }
        }
        //根据属性id查找出分类tree
        CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
        attrRespVo.setCatelogName(categoryEntity.getName());
        Long[] catePath = categoryService.findCatePath(attrEntity.getCatelogId());
        attrRespVo.setCatelogPath(catePath);
        return attrRespVo;
    }

    /**
     * 修改attr信息
     *
     * @param attrVo
     * @return
     */
    @Override
    @Transactional
    public Integer updateAttr(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo, attrEntity);
        int count = attrDao.updateById(attrEntity);
        //更新关联关系表
        if (attrVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            Long attrId = attrVo.getAttrId();
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrId(attrVo.getAttrId());
            relationEntity.setAttrGroupId(attrVo.getAttrGroupId());
            Long countNum = attrAttrgroupRelationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
            //如果未关联分组信息
            if (countNum < 1) {
                //新增
                int insert = attrAttrgroupRelationDao.insert(relationEntity);
            } else {
                //修改
                int update = attrAttrgroupRelationDao.update(relationEntity, new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
            }
        }
        return count;

    }

    @Override
    public PageUtils querySalePage(Long catelogId, Map<String, Object> params) {
        //查询销售属性
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_type", 0);
        if (catelogId != 0) {
            queryWrapper.eq("catelog_id", catelogId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((wrapper) -> wrapper.eq("attr_id", key).or().like("attr_name", key));
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> attrRespVos = records.stream().map((attrEntity -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            Long catId = categoryEntity.getCatId();
            String name = categoryEntity.getName();
            attrRespVo.setCatelogId(catId);
            attrRespVo.setCatelogName(name);
            return attrRespVo;
        })).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(attrRespVos);
        return pageUtils;
    }

}