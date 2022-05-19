package com.atguigu.gulimall.gulimallthirdparty.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.DELETE;

@Configuration
public class OSSConfig {
    @Deprecated
    public OSS OSSClient(){
        return new OSSClient("oss-cn-hangzhou.aliyuncs.com","LTAI5tAQveUkHZiBWshQLvcB","eTyHF62KstfYqiheJBVbkxtAoInoHE");
    }
}
