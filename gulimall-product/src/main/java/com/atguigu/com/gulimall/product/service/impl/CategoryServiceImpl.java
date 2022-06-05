package com.atguigu.com.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.com.gulimall.product.dao.CategoryDao;
import com.atguigu.com.gulimall.product.entity.CategoryEntity;
import com.atguigu.com.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询各级别分类
     *
     * @return
     */
    @Override
    public List<CategoryEntity> listWithTree() {
        //1、查询所有分类
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        //2、将所有分类组装成父子树形接口
        //2.1）查询1级分类
        List<CategoryEntity> level1Category =
                categoryEntities.stream().filter((CategoryEntity) -> CategoryEntity.getCatLevel() == 1)
                        .peek(CategoryEntity -> CategoryEntity.setChildren(getChildrens(CategoryEntity, categoryEntities)))
                        .sorted((CategoryEntity1, CategoryEntity2) -> {
                            return CategoryEntity1.getSort() - CategoryEntity2.getSort();
                        }).collect(Collectors.toList());

        return level1Category;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 1、检查当前删除的菜单，是否被别的地方引用
        int i = baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatePath(Long catelogId) {
        //找出当前分类及其父类的总路径
        List<Long> pathList = new LinkedList<>();
        List<Long> path = findPath(catelogId, pathList);
        Collections.reverse(path);
        Long[] longs = path.toArray(new Long[0]);


//        Long parentCid = categoryEntity.getParentCid();
//        if (parentCid != 0) {
//            this.getById(parentCid);
//        }
        return longs;
    }

    private List<Long> findPath(Long catelogId, List<Long> pathList) {
        pathList.add(catelogId);
        CategoryEntity categoryEntity = this.getById(catelogId);
        Long parentCid = categoryEntity.getParentCid();
        if (parentCid != 0) {
            findPath(parentCid, pathList);
        }
        return pathList;
    }

    //递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> categoryEntities) {
        List<CategoryEntity> collect = categoryEntities.stream().filter((CategoryEntity) -> {
                    return CategoryEntity.getParentCid().equals(root.getCatId());
                })
                //找到子菜单
                .peek(CategoryEntity -> CategoryEntity.setChildren(getChildrens(CategoryEntity, categoryEntities)))
                //菜单的排序
                .sorted((CategoryEntity1, CategoryEntity2) -> {
                    return (CategoryEntity1.getSort() == null ? 0 : CategoryEntity1.getSort()) - (CategoryEntity2.getSort() == null ? 0 : CategoryEntity2.getSort());
                })
                .collect(Collectors.toList());

        return collect;
    }

}