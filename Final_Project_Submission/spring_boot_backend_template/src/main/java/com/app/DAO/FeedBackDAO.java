package com.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojo.Feedback;

@Repository
public interface FeedBackDAO extends JpaRepository<Feedback, Integer> {

}
