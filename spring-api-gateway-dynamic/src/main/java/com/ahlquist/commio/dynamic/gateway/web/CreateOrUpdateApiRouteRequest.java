package com.ahlquist.commio.dynamic.gateway.web;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrUpdateApiRouteRequest {
	@NotBlank
	private String path;
	private String method;
	@NotBlank
	private String uri;
}
