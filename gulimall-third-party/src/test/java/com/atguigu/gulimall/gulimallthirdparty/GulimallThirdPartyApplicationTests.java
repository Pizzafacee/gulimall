package com.atguigu.gulimall.gulimallthirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallThirdPartyApplicationTests {


    @Resource
    OSSClient ossClient;

    @Test
    public void testUpload() throws FileNotFoundException {
//        String endPoint = "oss-cn-hangzhou.aliyuncs.com";
//        String accessKeyId = "LTAI5tAQveUkHZiBWshQLvcB";
//        String accessKeySecret = "eTyHF62KstfYqiheJBVbkxtAoInoHE";
//
//        OSS ossClient = new OSSClientBuilder().build(endPoint,accessKeyId,accessKeySecret);
        InputStream inputStream = new FileInputStream("F:\\谷粒商城\\Guli Mall(包含代码、课件、sql)\\Guli Mall\\分布式基础\\资源\\pics\\fe215589ed6500f4.jpg");
        ossClient.putObject("gulimall-pizzawang","zzzzzz.jpg",inputStream);
        ossClient.shutdown();
        System.out.println("上传完成");
    }

}
