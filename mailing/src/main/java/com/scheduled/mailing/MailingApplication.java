package com.scheduled.mailing;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MailingApplication {

//	@Autowired
//	private PersonRepository persons;
	
	public static void main(String[] args) {
		SpringApplication.run(MailingApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		List<Persons> p=persons.findAll();
//		p.forEach(System.out :: println);
//	}

}
