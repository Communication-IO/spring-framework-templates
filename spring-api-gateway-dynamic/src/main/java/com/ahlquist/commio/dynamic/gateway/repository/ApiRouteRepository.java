package com.ahlquist.commio.dynamic.gateway.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.ahlquist.commio.dynamic.gateway.model.database.ApiRoute;

public interface ApiRouteRepository extends ReactiveCrudRepository<ApiRoute, Long> {

}