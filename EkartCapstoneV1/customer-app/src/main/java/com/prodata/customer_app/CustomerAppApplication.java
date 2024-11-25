package com.prodata.customer_app;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAppApplication.class, args);
	}
	
//	@Bean
//	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
//	    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//	    jsonConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
//	    return jsonConverter;
//	}

}
