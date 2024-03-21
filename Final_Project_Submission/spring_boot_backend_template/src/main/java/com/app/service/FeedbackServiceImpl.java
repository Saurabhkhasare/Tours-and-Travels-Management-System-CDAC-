package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.FeedBackDAO;
import com.app.pojo.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedBackDAO feedbackDAO;

	@Override
	public Feedback saveFeedback(Feedback feedback) {
		return feedbackDAO.save(feedback);
	}

}
