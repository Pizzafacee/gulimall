package com.atguigu.com.gulimall.product;

import com.atguigu.com.gulimall.product.entity.BrandEntity;
import com.atguigu.com.gulimall.product.service.BrandService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {
    @Autowired
    private BrandService brandService;

    @Test
   public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setName("三星");
//        brandEntity.setName("华为");
//        brandService.save(brandEntity);
//        System.out.println("保存成功。。。。");
        boolean b = brandService.updateById(brandEntity);
        if(b){
            System.out.println("修改成功");
        }
    }
    @Test
    public void contextLoads2() {
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper();
        queryWrapper.equals(1L);
        List<BrandEntity> list = brandService.list(queryWrapper);
        System.out.println(list);
    }


}
