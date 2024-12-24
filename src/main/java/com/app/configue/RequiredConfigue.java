package com.app.configue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RequiredConfigue {

	@Bean
	public RestTemplate template() {
		
		return new RestTemplate();
	}
}
