package com.sbolo.syk.view.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbolo.syk.view.service.TestService;

import oth.common.ui.RequestResult;

@Controller
public class TestController {
	
	private static final String test = "test.html";

	@Autowired
	private TestService testService;
	
	@GetMapping("test")
	@ResponseBody
	public RequestResult<Integer> test111(@NotNull(message="测试") String testa) {
		int i = testService.test();
		return new RequestResult<>(i);
	}
	
	@GetMapping("testSocket")
	public String json(){
		return test;
	}
}
