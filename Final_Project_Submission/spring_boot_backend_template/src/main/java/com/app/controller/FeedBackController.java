package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.Feedback;
import com.app.service.FeedbackService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/feedback")
public class FeedBackController {
	
	@Autowired
	private FeedbackService feedbackService;

	@PostMapping
	public ResponseEntity<Object> addFeedback(@RequestBody Feedback feedback){
		try {
			Feedback newfeedback=feedbackService.saveFeedback(feedback);
			return ResponseEntity.status(HttpStatus.CREATED).body("feedback submitted successfully : "+ newfeedback);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("feedbak submission failed");
		}
		
	}
}
