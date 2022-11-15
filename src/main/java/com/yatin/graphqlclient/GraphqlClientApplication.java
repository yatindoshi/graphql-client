package com.yatin.graphqlclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GraphqlClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlClientApplication.class, args);
	}

}
