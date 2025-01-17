package com.asalkar.oils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(scanBasePackages = {"com.asalkar.oils.controller","com.asalkar.oils.services","com.asalkar.oils.repository","com.asalkar.dto.filter","com.asalkar.dto","com.asalkar.oils.config","com.asalkar.oils.model"})
@CrossOrigin("http://localhost:3000")
public class AsalkarOilsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsalkarOilsApplication.class, args);
	}

}
