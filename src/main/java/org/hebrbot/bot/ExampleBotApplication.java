package org.hebrbot.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleBotApplication {

	public static void main(String[] args) {
//		ApiContextInitializer.init();
		SpringApplication.run(ExampleBotApplication.class, args);
	}

}
