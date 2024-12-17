package com.nt.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.service.IPurchaseMgmtService;

@Component
public class SpringBootMailTest implements CommandLineRunner {

	@Autowired
	private IPurchaseMgmtService service;
	
	@Override
	public void run(String... args) throws Exception {
		try {
			
			String resultMsg = service.shopping(new String[] {"Coat" ,"Kurti"}, 
					                            new Double[] {5000.0, 1000.0}, 
					                            new String[] {"aachaltiwari9323@gmail.com","tanmaym0444@gmail.com"});
			
			System.out.println(resultMsg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
