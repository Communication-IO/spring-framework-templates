package com.ahlquist.commio.dynamic.gateway.model.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.ahlquist.commio.dynamic.gateway.constant.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(TableName.API_ROUTE) // To bind our model class with a database table with defined name
public class ApiRoute {

	// Indicating that this field is primary key in our database table
	@Id 
	private Long id;

	private String path;
	private String method;
	private String uri;
}