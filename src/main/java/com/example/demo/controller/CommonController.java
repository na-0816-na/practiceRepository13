package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

//完了後のリダイレクト先
public class CommonController {

	@PostMapping("/complete")
	private String complete() {
		return "complete";
	}
}
