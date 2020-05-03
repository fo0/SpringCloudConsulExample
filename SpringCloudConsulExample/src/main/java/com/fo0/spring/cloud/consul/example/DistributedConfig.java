package com.fo0.spring.cloud.consul.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistributedConfig {

	@Value("${servicename}")
	private String value;

	@Autowired
	private MyProperties properties;

	@GetMapping("/getConfigFromValue")
	public String getConfigFromValue() {
		return value;
	}

	@GetMapping("/getConfigFromProperty")
	public String getConfigFromProperty() {
		return properties.getServicename();
	}
}
