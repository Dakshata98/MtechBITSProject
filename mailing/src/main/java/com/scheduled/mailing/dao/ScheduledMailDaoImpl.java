package com.scheduled.mailing.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scheduled.mailing.dto.Employee;
import com.scheduled.mailing.dto.ScheduledMail;

@Repository
@Transactional
public class ScheduledMailDaoImpl {
	
	@Autowired
	private EntityManager entityManager;
	
	public boolean createEmployee(Employee employee) {
		entityManager.merge(employee);
		return true;
	}
	
	public BigInteger nextID() {
		return (BigInteger) entityManager.createNativeQuery("select NEXT VALUE FOR SCHMAILSEQUENCE").getSingleResult();
	}
	
	public boolean saveMailData(ScheduledMail scheduledMail) {
		entityManager.merge(scheduledMail);
		return true;
	}
	
	public ScheduledMail getRecord(Integer id) {
		return (ScheduledMail) entityManager.createNativeQuery("select * from SCHMAIL where id="+id, ScheduledMail.class).getSingleResult();
	}
	
	public List<ScheduledMail> getAllScheduledMails(String scheduledDate) {
		List<ScheduledMail> scheduledMails = (List<ScheduledMail>) entityManager
				.createNativeQuery("select * from schmail where sch_datetime <= '"+scheduledDate+"' and status='Scheduled'", ScheduledMail.class).getResultList();
		return scheduledMails;
	}
	
	public boolean updateStatus(Integer id, String status) {
		entityManager.createNativeQuery("UPDATE schmail SET status = '"
				+status+"' WHERE id="+id).executeUpdate();
		return true;
		
	}
	
	public boolean deleteMailData(Integer id) {
		ScheduledMail mailData = this.getRecord(id);
		entityManager.remove(mailData);
		return true;
	}
	

}
