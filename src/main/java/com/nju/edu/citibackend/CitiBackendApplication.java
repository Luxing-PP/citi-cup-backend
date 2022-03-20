package com.nju.edu.citibackend;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class CitiBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(CitiBackendApplication.class, args);
	}
}
