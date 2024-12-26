package com.prateleira_inteligente.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrateleiraInteligenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrateleiraInteligenteApplication.class, args);
		TestDAO testDAO = new TestDAO();
		testDAO.testDAO();
	}

}
