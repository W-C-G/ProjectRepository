package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/index")
	public String Index() {
		System.out.println("컨트롤러 실행 디버그");
		return "Index";
	}
}
