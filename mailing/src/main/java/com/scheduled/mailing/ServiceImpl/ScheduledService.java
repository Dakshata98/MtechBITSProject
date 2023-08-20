package com.scheduled.mailing.ServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.scheduled.mailing.dao.ScheduledMailDaoImpl;
import com.scheduled.mailing.dto.Body;
import com.scheduled.mailing.dto.ScheduledMail;

@Component
public class ScheduledService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ScheduledMailDaoImpl em;

	private static Unmarshaller unmarshaller;

	static {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Body.class);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Scheduled(cron = "0 0 12 * * *")
	public void scheduledService() {
		System.out.println("time : " + LocalDateTime.now());
		this.createScheduledMailBody();
	}

	public void sendEmail(ScheduledMail schMail, Body body, String path) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setFrom(schMail.getSender());
			mimeMessageHelper.setTo(schMail.getRecipient());
			StringBuffer mailBody=new StringBuffer();
			mailBody.append(body.getSalutation()+ "\n\n");
			for(StringBuffer stanza : body.getStanzas()) {
				mailBody.append(stanza + "\n");
			}
			mailBody.append("\n" +body.getComplimentaryClose() + "\n" + body.getSignature());
			mimeMessageHelper.setText(mailBody.toString());
			mimeMessageHelper.setSubject(body.getSubject());
			if (path != null && !path.isEmpty()) {
				FileSystemResource fileSystemResource = new FileSystemResource(new File(path));
				mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
			}
			mailSender.send(mimeMessage);
			System.out.println("mail sent");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		System.out.println("mail sent at : " + LocalDateTime.now());
	}

	public void createScheduledMailBody() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		List<ScheduledMail> list = em.getAllScheduledMails(formattedDate);

		for (ScheduledMail schMail : list) {
			try {
				em.updateStatus(schMail.getId(), "In progress");
				StringReader reader = new StringReader(schMail.getMailBody());
				Body body = (Body) unmarshaller.unmarshal(reader);
				if (schMail.getAttachment() != null) {
					FileOutputStream fos;
					try {
						String path = "D:\\collection-app" + "\\" + schMail.getId() + ".zip";
						fos = new FileOutputStream(path);
						fos.write(schMail.getAttachment());
						fos.flush();
						fos.close();
						this.sendEmail(schMail, body, path);
						em.updateStatus(schMail.getId(), "Sent");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					this.sendEmail(schMail, body, null);
					em.updateStatus(schMail.getId(), "Sent");
				}
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}

}
