package com.microservices.tablesservice.tablesservice;

import com.microservices.tablesservice.tablesservice.config.DatabaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TablesserviceApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfig.class);
		SpringApplication.run(TablesserviceApplication.class, args);
	}

}
