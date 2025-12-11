package com.setec.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.setec.entities.Booked;
import com.setec.repos.BookedRepos;
import com.setec.services.MyTelegramBot;

@Controller
public class MyController {

	// http://localhost:8080/
	
	@GetMapping({"/" , "/home"})
	public String home(Model mod) {
		
		Booked booked = new Booked(
				1,"Kea Sreylis",
				"012345678",
				"Lis@gmail.com",
				"10/27/2025",
				"1:20 PM",
				10
				
				);
		
		mod.addAttribute("booked",booked);
		
		return "index";
	}
	
	@GetMapping({"/about"})
	public String about() {
		return "about";
	}
	
	@GetMapping({"/contact"})
	public String contact() {
		return "contact";
	}
	
	@GetMapping({"/menu"})
	public String menu() {
		return "menu";
	}
	
	@GetMapping({"/reservation"})
	public String reservation(Model mod) {
		
		Booked booked = new Booked(
				1,"Kea Sreylis",
				"012345678",
				"Lis@gmail.com",
				"10/27/2025",
				"1:20 PM",
				10
				
				);
		
		mod.addAttribute("booked",booked);
		return "reservation";
	}
	
	@GetMapping({"/service"})
	public String service() {
		return "service";
	}
	
	@GetMapping({"/testimonial"})
	public String testimonial() {
		return "testimonial";
	}
	
	@Autowired
	private BookedRepos bookedRepos;
	
	@Autowired
	private MyTelegramBot bot;
	
	@PostMapping({"/success"})
	public String success(@ModelAttribute Booked booked) {
		bookedRepos.save(booked);
		
	    // Format message for Telegram
	    String msg = ""
	        + "📌 New Booking Received! \n\n"
	        + "👤 Name:  " + booked.getName() + "\n"
	        + "📞 Phone:  " + booked.getPhoneNumber() + "\n"
	        + "✉️ Email:  " + booked.getEmail() + "\n"
	        + "📅 Date:  " + booked.getDate() + "\n"
	        + "⏰ Time:  " + booked.getTime() + "\n"
	        + "👥 Persons:  " + booked.getPerson();

	    bot.sendMessage(msg);

		return "success";
	}
	
	
	
	
}
