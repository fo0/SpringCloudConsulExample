package com.fo0.spring.cloud.consul.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableDiscoveryClient
//@RestController
@Log4j2
public class SpringCloudConsulExampleApplication {

	public static final String NAME = "example-service";

	public static void main(String[] args) {
		// @formatter:off
		new SpringApplicationBuilder(SpringCloudConsulExampleApplication.class)
				.properties("spring.application.name=" + NAME)
				.properties("spring.cloud.consul.host=127.0.0.1")
				.properties("spring.cloud.consul.port=8500")
				.properties("spring.cloud.consul.discovery.instanceId=" + NAME + ":${random.value}")
			.run(args);
		// @formatter:on
	}

//	@RequestMapping("/")
//	public String home() {
//		return "Hello world";
//	}

}
