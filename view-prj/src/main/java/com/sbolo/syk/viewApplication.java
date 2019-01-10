package com.sbolo.syk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
//强制开启cglib代理，并暴露代理类可从AopContext中获取
@EnableAspectJAutoProxy(proxyTargetClass=true, exposeProxy=true)
@EnableSwagger2
@MapperScan(basePackages = "com.sbolo.syk.*.mapper")
public class viewApplication extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(viewApplication.class);
    }
	
	public static void main(String[] args) {
        SpringApplication.run(viewApplication.class,args);
    }
}
