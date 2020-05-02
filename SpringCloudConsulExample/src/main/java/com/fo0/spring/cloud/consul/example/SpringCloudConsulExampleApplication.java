package com.fo0.spring.cloud.consul.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringCloudConsulExampleApplication {

	public static void main(String[] args) {
		// @formatter:off
		new SpringApplicationBuilder(SpringCloudConsulExampleApplication.class)
				.properties("spring.application.name=example-service")
				.properties("spring.cloud.consul.host=127.0.0.1")
				.properties("spring.cloud.consul.port=8500")
				.properties("spring.cloud.consul.discovery.instanceId=example-service:${random.value}")
			.run(args);
		// @formatter:on
	}

	@RequestMapping("/")
	public String home() {
		return "Hello world";
	}

}
