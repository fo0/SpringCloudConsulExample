package com.fo0.spring.cloud.consul.example;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableDiscoveryClient
//@RestController
@EnableScheduling
@Log4j2
public class Main {

	public static final String NAME = "example-service"; // + RandomStringUtils.randomAlphanumeric(10);

	@Autowired
	private DiscoveryClient dClient;

	@Autowired
	private ConsulDataAccess cClient;

	public static void main(String[] args) {
		// @formatter:off
		new SpringApplicationBuilder(Main.class)
				.properties("spring.application.name=" + NAME)
				.properties("server.port=" + (8080 + Integer.parseInt(RandomStringUtils.randomNumeric(3))))
				.properties("spring.cloud.consul.host=127.0.0.1")
				.properties("spring.cloud.consul.port=8500")
				.properties("spring.cloud.consul.discovery.instanceId=" + NAME + ":${random.value}")
				.properties("spring.cloud.consul.discovery.healthCheckPath=/my-health-check")
				.properties("spring.cloud.consul.discovery.healthCheckPathInterval=20s")
			.run(args);
		// @formatter:on
	}

	@Scheduled(fixedRate = 1000 * 10)
	public void changeValue() {
		String s = RandomStringUtils.randomAlphanumeric(10);
		log.info("changing value to: " + s);
		cClient.setMyChangeValue(s);
	}

	@Scheduled(fixedRate = 1000 * 5)
	public void cron() {
		try {
			log.info("-------------------------------------------------------------------------");
//			client.getInstances(Main.NAME).stream().forEach(log::info);
			ServiceInstance uri = dClient.getInstances(Main.NAME).stream().findAny().orElse(null);
			log.info("Any Service URI: " + uri);
			log.info("Changing Value: " + cClient.getMyChangeValue().getValue());
			log.info("-------------------------------------------------------------------------");
		} catch (Exception e) {
			log.error("error");
		}
	}

//	@RequestMapping("/")
//	public String home() {
//		return "Hello world";
//	}

}
