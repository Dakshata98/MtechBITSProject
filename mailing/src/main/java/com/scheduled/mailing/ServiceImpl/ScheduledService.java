package com.scheduled.mailing.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
		try {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setFrom("dakshatasmetkari@gmail.com");
		mimeMessageHelper.setTo("newtimicindia@gmail.com");
		mimeMessageHelper.setText("Hi");
		mimeMessageHelper.setSubject("Hi");
		
		FileSystemResource fileSystemResource = new FileSystemResource(
				new File("D:\\documents\\113802054_ExamForm.pdf"));
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		mailSender.send(mimeMessage);
		System.out.println("mail sent");
		
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		System.out.println("mail sent at : "+LocalDateTime.now());
	}
	
}
