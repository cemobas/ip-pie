package com.ceon.ippie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class IpPieApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(IpPieApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8888"));
		app.run(args);

	}
}
