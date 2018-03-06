package com.common;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.TimerService;
@Component
public class TeacherTimer {
	@Autowired
	private TimerService timerService;
	@Test
	public void selectTopic() {
		
		System.out.println("-----------------------run-----------------------");
//		SendEmail send = new SendEmail();
//		send.sendMail();
		
		timerService.start();
		
	}
}
