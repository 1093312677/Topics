package com.common;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.TimerService;

public class TeacherTimer {
	@Autowired
	private TimerService timerService;
	@Test
	public void selectTopic() {
		
		System.out.println("run");
//		SendEmail send = new SendEmail();
//		send.sendMail();
		
		timerService.test();
		
	}
}
