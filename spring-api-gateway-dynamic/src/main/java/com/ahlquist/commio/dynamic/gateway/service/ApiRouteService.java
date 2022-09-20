package com.ahlquist.commio.dynamic.gateway.service;

import com.ahlquist.commio.dynamic.gateway.model.database.ApiRoute;
import com.ahlquist.commio.dynamic.gateway.web.CreateOrUpdateApiRouteRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApiRouteService {

	Flux<ApiRoute> findApiRoutes();

	Mono<ApiRoute> findApiRoute(Long id);

	Mono<Void> createApiRoute(CreateOrUpdateApiRouteRequest createOrUpdateApiRouteRequest);

	Mono<Void> updateApiRoute(Long id, CreateOrUpdateApiRouteRequest createOrUpdateApiRouteRequest);

	Mono<Void> deleteApiRoute(Long id);
}