package com.scheduled.mailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MailingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailingApplication.class, args);
	}

}
