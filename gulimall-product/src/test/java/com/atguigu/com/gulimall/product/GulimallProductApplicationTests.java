package com.atguigu.com.gulimall.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.com.gulimall.product.entity.BrandEntity;
import com.atguigu.com.gulimall.product.service.BrandService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {
    @Autowired
    private BrandService brandService;

    @Resource
    OSSClient ossClient;
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

    @Test
    public void testUpload() throws FileNotFoundException {
//        String endPoint = "oss-cn-hangzhou.aliyuncs.com";
//        String accessKeyId = "LTAI5tAQveUkHZiBWshQLvcB";
//        String accessKeySecret = "eTyHF62KstfYqiheJBVbkxtAoInoHE";
//
//        OSS ossClient = new OSSClientBuilder().build(endPoint,accessKeyId,accessKeySecret);
        InputStream inputStream = new FileInputStream("F:\\谷粒商城\\Guli Mall(包含代码、课件、sql)\\Guli Mall\\分布式基础\\资源\\pics\\fe215589ed6500f4.jpg");
        ossClient.putObject("gulimall-pizzawang","fe215589ed6500f4.jpg",inputStream);
        ossClient.shutdown();
        System.out.println("上传完成");
    }

    @Test
    public void stringTest(){
        String s = "and";
        String t = new String("and");
        System.out.println(s.equals(t));
    }

}
