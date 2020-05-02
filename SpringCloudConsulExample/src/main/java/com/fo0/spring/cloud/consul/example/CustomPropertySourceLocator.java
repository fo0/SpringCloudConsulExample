package com.fo0.spring.cloud.consul.example;

import java.util.Collections;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

@Configuration
public class CustomPropertySourceLocator implements PropertySourceLocator {

	@Override
	public PropertySource<?> locate(Environment environment) {
		return new MapPropertySource("application-name", Collections.<String, Object>singletonMap("spring.applicaton.name", "example-service"));
	}

}