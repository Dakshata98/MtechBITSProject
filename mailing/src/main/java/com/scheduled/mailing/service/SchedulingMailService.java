package com.scheduled.mailing.service;

import org.springframework.web.multipart.MultipartFile;

import com.scheduled.mailing.dto.Employee;
import com.scheduled.mailing.dto.MailingDTO;
import com.scheduled.mailing.dto.ScheduledMailOutput;

public interface SchedulingMailService {
	
	public boolean create(Employee employee);
	
	public boolean saveMailEntry(MailingDTO mailingDto);

	public boolean saveMailingEntry(MultipartFile file, String mailingDto);

	public boolean deleteRecord(Integer id);

	public ScheduledMailOutput fetchMailData(Integer id);
}
