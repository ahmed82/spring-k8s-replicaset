package com.atr.springk8s.web;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("home")
	public String goHome() {
		Random rdm = new Random();
		return rdm.nextBoolean() ? "It's your lucky day! :)" : "Oh, no !!!, keep trying hard, never give up :) ";
	}

}
