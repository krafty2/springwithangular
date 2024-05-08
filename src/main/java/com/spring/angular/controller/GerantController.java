package com.spring.angular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.service.CanalService;

@RestController
@RequestMapping("/gerant/")
@CrossOrigin(origins = "*")
public class GerantController {

	@Autowired
	private CanalService canalService;
	
	@GetMapping("/ca")
	public double chiffreAffaire() {
		return canalService.chiffreAffaire();
	}
}
