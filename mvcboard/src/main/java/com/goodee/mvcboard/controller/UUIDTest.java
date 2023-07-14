package com.goodee.mvcboard.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UUIDTest {
	@GetMapping("uuidTest")
	public String uuidTest() {
		UUID newFilename = UUID.randomUUID();
		log.debug(newFilename.toString().replace("-", ""));
		return ""; 
	}
}
