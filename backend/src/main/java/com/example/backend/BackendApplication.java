package com.example.backend;

import java.util.HashMap;
import java.util.Map;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		Map<String, Object> envProps = new HashMap<>();
		dotenv.entries().forEach(entry -> envProps.put(entry.getKey(), entry.getValue()));
		new SpringApplicationBuilder(BackendApplication.class).properties(envProps).run(args);
		// SpringApplication.run(BackendApplication.class, args);
	}
}
