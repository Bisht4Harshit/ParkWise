package com.implementation.ParkWise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ParkWiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkWiseApplication.class, args);
	}

	
}
