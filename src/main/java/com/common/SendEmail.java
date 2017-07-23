package com.common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {
	private String host = "smtp.163.com";//smtp
	private String user = "kone_net@163.com";//
	private String pwd = "";
	private String from = "kone_net@163.com";//
	private String to = "1093312677@qq.com";//
	private String subject = "Weily";//
	private String text = "你好，你的信息系统已经登记，（此消息自动发送）";
	
	public SendEmail() {
		super();
	}

	public SendEmail(String to, String subject, String text) {
		super();
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public void sendMail(){
		 Properties props = new Properties();  
	       props.put("mail.smtp.host", host); //smtp
	  
	       props.put("mail.smtp.auth", true);  //
	       
	       Session session = Session.getInstance(props);
	       session.setDebug(true);//
	       MimeMessage  message = new MimeMessage(session); //
	       try  
	       {  
	    	   InternetAddress fromI = new InternetAddress(from);
		       message.setFrom(fromI); //nternetAddress[]  
		  
		       InternetAddress toI=new InternetAddress(to);
		       message.setRecipient(Message.RecipientType.TO,toI);//TO
		       
		       message.setText(text); // 
		  
		       message.setSubject(subject); //
		  
		       message.saveChanges();//save
		       
		       Transport transport = session.getTransport("smtp");
		       transport.connect(host,user,pwd);
		       transport.sendMessage(message,message.getAllRecipients());//
			   transport.close();
		    
	       }catch(Exception e){  }
	  
	
	}
}
