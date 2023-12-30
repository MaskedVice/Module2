package com.scripfinder.module2;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module2Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Module2Application.class);
		app.setDefaultProperties(Collections
          .singletonMap("server.port", "8085")
		  );
        app.run(args);
	}

}
