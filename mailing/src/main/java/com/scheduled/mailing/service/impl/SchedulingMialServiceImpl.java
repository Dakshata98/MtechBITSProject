package com.scheduled.mailing.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduled.mailing.dao.ScheduledMailDaoImpl;
import com.scheduled.mailing.dto.Body;
import com.scheduled.mailing.dto.Employee;
import com.scheduled.mailing.dto.MailBody;
import com.scheduled.mailing.dto.MailOutput;
import com.scheduled.mailing.dto.MailOutputData;
import com.scheduled.mailing.dto.MailingDTO;
import com.scheduled.mailing.dto.ScheduledMail;
import com.scheduled.mailing.dto.ScheduledMailOutput;
import com.scheduled.mailing.service.SchedulingMailService;

@Service
public class SchedulingMialServiceImpl implements SchedulingMailService {

	@Autowired
	private ScheduledMailDaoImpl em;

	@Autowired
	private ObjectMapper mapper;

	private static Marshaller marshaller;

	private static Unmarshaller unmarshaller;

	static {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Body.class);
			marshaller = context.createMarshaller();
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean create(Employee employee) {
//		employeeRepository.save(employee);
		em.createEmployee(employee);
		return true;
	}

	public String marshallBody(MailBody mailBody) {
		try {
			StringWriter writer = new StringWriter();
			Body body = new Body();
			body.setSubject(mailBody.getSubject());
			body.setSalutation(mailBody.getSalutation());
			body.setStanzas(mailBody.getStanzas());
			body.setComplimentaryClose(mailBody.getComplimentaryClose());
			body.setSignature(mailBody.getSignature());
			marshaller.marshal(body, writer);
			String xml = writer.toString();
			return xml;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveMailEntry(MailingDTO mailingDto) {
		ScheduledMail scheduledMail = new ScheduledMail();
		scheduledMail.setId(new Integer(em.nextID().toString()));
		scheduledMail.setSender(mailingDto.getSender());
		scheduledMail.setRecipient(mailingDto.getRecipient());
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		scheduledMail.setCreateDateTime(date);
		scheduledMail.setLastUpdateDateTime(date);
		try {
			scheduledMail.setScheduledDateTime(dateFormat.parse(mailingDto.getScheduledDateTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String xml = this.marshallBody(mailingDto.getBody());
		scheduledMail.setMailBody(xml);
		scheduledMail.setAttachment(null);
		scheduledMail.setStatus("Scheduled");
		em.saveMailData(scheduledMail);
		return true;
	}

	@Override
	public boolean saveMailingEntry(MultipartFile file, String mailingDto) {
		try {
			MailingDTO mailingData = mapper.readValue(mailingDto, MailingDTO.class);
			ScheduledMail scheduledMail = new ScheduledMail();
			if (mailingData.getId() == null) {
				scheduledMail.setId(new Integer(em.nextID().toString()));
			} else {
				scheduledMail.setId(mailingData.getId());
			}
			scheduledMail.setSender(mailingData.getSender());
			scheduledMail.setRecipient(mailingData.getRecipient());
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formattedDate = dateFormat.format(date);
			scheduledMail.setCreateDateTime(date);
			scheduledMail.setLastUpdateDateTime(date);
			scheduledMail.setScheduledDateTime(dateFormat.parse(mailingData.getScheduledDateTime()));
			String xml = this.marshallBody(mailingData.getBody());
			scheduledMail.setMailBody(xml);
			if (file != null) {
				InputStream is = file.getInputStream();
				byte[] data = new byte[is.available()];
				is.read(data);
				FileOutputStream fos = new FileOutputStream("D:\\collection-app" + "\\" + file.getOriginalFilename());
				fos.write(data);
				fos.flush();
				fos.close();
				is.close();
				File f = new File("D:\\collection-app" + "\\" + file.getOriginalFilename());
				FileInputStream fis = new FileInputStream(f);
				byte[] byteArray = new byte[(int) f.length()];
				fis.read(byteArray);
				fis.close();
				scheduledMail.setAttachment(byteArray);
			} else {
				scheduledMail.setAttachment(null);
			}
			scheduledMail.setStatus("Scheduled");
			em.saveMailData(scheduledMail);
			return true;
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteRecord(Integer id) {
		return em.deleteMailData(id);
	}

	@Override
	public ScheduledMailOutput fetchMailData(Integer id) {
		try {
			ScheduledMail mailData = em.getRecord(id);
			StringReader reader = new StringReader(mailData.getMailBody());
			Body body = (Body) unmarshaller.unmarshal(reader);
			ScheduledMailOutput output = new ScheduledMailOutput();
			output.setId(mailData.getId());
			output.setSender(mailData.getSender());
			output.setRecipient(mailData.getRecipient());
			output.setCreateDateTime(mailData.getCreateDateTime());
			output.setLastUpdateDateTime(mailData.getLastUpdateDateTime());
			output.setScheduledDateTime(mailData.getScheduledDateTime());
			output.setMailBody(body);
			output.setAttachment(mailData.getAttachment());
			output.setStatus(mailData.getStatus());
			return output;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MailOutput fetchUserMailData(String mailId) {
		// TODO Auto-generated method stub
		List<ScheduledMail> mails = em.getMails(mailId);
		List<MailOutputData> mailsData = new ArrayList<>();
		for(ScheduledMail mail : mails) {
			MailOutputData mailOutput = new MailOutputData();
			mailOutput.setId(mail.getId());
			mailOutput.setRecipient(mail.getRecipient());
			mailOutput.setScheduledDateTime(mail.getScheduledDateTime());
			mailOutput.setStatus(mail.getStatus());
			String xml=mail.getMailBody();
			String startWord = "<subject>";
	        String endWord = "</subject>";
	        int startIndex = xml.indexOf(startWord) + startWord.length();
	        int endIndex = xml.indexOf(endWord);
	        mailOutput.setSubject(xml.substring(startIndex, endIndex).trim());
			mailsData.add(mailOutput);
		}
		MailOutput mailResponse = new MailOutput();
		mailResponse.setMailId(mailId);
		mailResponse.setMails(mailsData);
		return mailResponse;
	}

}
