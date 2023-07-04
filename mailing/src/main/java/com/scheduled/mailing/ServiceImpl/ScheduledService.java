package com.scheduled.mailing.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Scheduled(fixedRate = 100000)
	public void scheduledService() {
		System.out.println("time : "+LocalDateTime.now());
		this.sendEmail();
	}
	
	public void sendEmail() {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("dakshatasmetkari@gmail.com");
		message.setTo("newtimicindia@gmail.com");
		message.setText("Hi");
		message.setSubject("Hi");
		mailSender.send(message);
		System.out.println("mail sent at : "+LocalDateTime.now());
	}
	
}
