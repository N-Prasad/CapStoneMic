package com.prodata.discovery_server_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerAppApplication.class, args);
	}

}
