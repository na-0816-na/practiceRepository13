package com.example.demo.controller;

import org.springframework.stereotype.Controller;

@Controller

//完了後のリダイレクト先
public class CommonController {

	private String complete() {
		return "complete";
	}
}
