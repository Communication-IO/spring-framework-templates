package com.ahlquist.commio.dynamic.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ahlquist.commio.dynamic.gateway.service.ApiPathRouteLocatorImpl;
import com.ahlquist.commio.dynamic.gateway.service.ApiRouteService;

@Configuration
public class GatewayConfiguration {

	@Bean
	public RouteLocator routeLocator(ApiRouteService apiRouteService, RouteLocatorBuilder routeLocatorBuilder) {
		return new ApiPathRouteLocatorImpl(apiRouteService, routeLocatorBuilder);
	}
}