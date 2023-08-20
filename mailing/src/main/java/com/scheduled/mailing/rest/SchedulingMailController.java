package com.scheduled.mailing.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scheduled.mailing.dto.Employee;
import com.scheduled.mailing.dto.MailingDTO;
import com.scheduled.mailing.dto.ScheduledMailOutput;
import com.scheduled.mailing.service.SchedulingMailService;

@RestController
@RequestMapping("/api/scheduling")
public class SchedulingMailController {

	@Autowired
	private SchedulingMailService SchedulingMailService;

	@PostMapping("/addEntry")
	public boolean createTask(@RequestBody MailingDTO mailingDTO) {
		return SchedulingMailService.saveMailEntry(mailingDTO);
	}
	
	@PostMapping("/addMailEntry")
	public boolean addMailData(@RequestParam("file") MultipartFile file, @RequestParam String mailingDTO) {
		return SchedulingMailService.saveMailingEntry(file, mailingDTO);
	}
	
	@PutMapping("/editMailEntry")
	public boolean updateMailData(@RequestParam("file") MultipartFile file, @RequestParam String mailingDTO) {
		return SchedulingMailService.saveMailingEntry(file, mailingDTO);
	}
	
	@DeleteMapping("/removeMailEntry")
	public boolean deleteMailRecord(@RequestParam Integer id) {
		return SchedulingMailService.deleteRecord(id);
	}
	
	@GetMapping("/fetchMailData")
	public ScheduledMailOutput fetchMailData(@RequestParam Integer id) {
		return SchedulingMailService.fetchMailData(id);
	}

	@PostMapping("/createEmployee")
	public boolean createEmployee(@RequestBody Employee employee) {
		return SchedulingMailService.create(employee);
	}
}
