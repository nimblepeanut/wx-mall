package com.qxf.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// mybatis的扫描
@MapperScan(basePackages="com.qxf.mall.mapper")
public class WxMallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxMallAdminApplication.class, args);
    }

}
