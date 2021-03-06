package com.peraglobal.datacrawlerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * <code>Application.java</code>
 * <p>
 * 功能:启动类
 * 
 * <p>
 * Copyright 安世亚太 2016 All right reserved.
 * 
 * @author yongqian.liu
 * @version 1.0 2016-12-2 </br>
 * 			最后修改人 无
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties({ WebServiceProperties.class })
public class Application {

	public static void main(String[] args) {
		// 设置启动类
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 设置 RestTemplate 功能，在项目中调用 RESTFul API
	 * 
	 * @param builder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
