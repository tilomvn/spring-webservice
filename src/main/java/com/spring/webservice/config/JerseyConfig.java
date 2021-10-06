package com.spring.webservice.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.spring.webservice.controller.TestService;

@Configuration
class JerseyConfig extends ResourceConfig {

	JerseyConfig() {
		register(TestService.class);
		register(ExceptionHandlerMapper.class);
	}
}
