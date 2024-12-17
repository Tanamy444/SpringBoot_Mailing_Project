package com.nt.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class PurchaseServiceImpl implements IPurchaseMgmtService {

	@Autowired
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String fromEmailId;
	
	@Override
	public String shopping(String[] items, Double[] prices, String[] toEmailIds) throws Exception {
		//calculate bill amt
		double amt = 0.0;
		for(double p : prices)
		{
			amt = amt + p;
		}
		
		String msg1 = Arrays.toString(items)+"/n Are Purchased having Prices:: "+Arrays.toString(prices)+" /n With the bill Amt:: "+amt;
		
		//trigger the email msg
		String msg2 = sendMail(msg1, toEmailIds, fromEmailId);
		
		return msg1+"...."+msg2;
	}
	
	private String sendMail(String msg, String []toEmailIds, String fromEmailIds) throws Exception{
		
		MimeMessage message = sender.createMimeMessage();//represents email msg
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		//setting the header value
		helper.setSentDate(new Date());
		
		helper.setCc(toEmailIds);
		helper.setSubject("=== DO YOU KNOW ME ===");
		helper.setText(msg);
		helper.addAttachment("TAN.jpg", new ClassPathResource("TAN.jpg") );
		
		//send message
		sender.send(message);
		return "EMail Message sent successfully>>!!";
	}

}
