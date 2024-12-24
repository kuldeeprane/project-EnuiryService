package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	@Bean
	public ModelMapper modelMapper()
	{
		ModelMapper m=new ModelMapper();
		m.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		return m;
		//return new ModelMapper();
		
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
