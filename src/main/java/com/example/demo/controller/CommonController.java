package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

//完了後のリダイレクト先
public class CommonController {

	@GetMapping("/complete")
	private String complete() {
		return "complete";
	}
}
