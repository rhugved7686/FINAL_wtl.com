package com.workshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop.Entity.RegisteredWebsite;
import com.workshop.Service.WebsiteRegisterService;

@Controller
public class WebsiteRegistrationController {

	@Autowired
	WebsiteRegisterService service;
	
	
	@GetMapping("/websiteRegister")
	public String website() {
		return "WebsiteRegistration";
	}
	
	
	
	@PostMapping("/registerWebsite")
	public String registerWeb(@RequestParam String name, @RequestParam String phone, @RequestParam String email) {
		String apiKey = service.generateApiKey();
		System.out.println(apiKey);
		
		RegisteredWebsite web = new RegisteredWebsite();
		web.setEmail(email);
		web.setName(name);
		web.setPhone(phone);
		web.setApiKey(apiKey);
		
		service.save(web);
		
		return "WebsiteRegistrationThankyou";
	}
	
	
	
	
	
	
	
}
