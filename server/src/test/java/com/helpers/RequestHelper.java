package com.helpers;

import static com.helpers.CommonValues.BASE_URI;
import static io.restassured.RestAssured.with;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;

@Component
public class RequestHelper {

	public RequestSpecification createRequestSpecification(int port) {
		return with()
                .baseUri(BASE_URI)
                .port(port)
                .accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
                .filter(new RequestLoggingFilter());
	}
}
