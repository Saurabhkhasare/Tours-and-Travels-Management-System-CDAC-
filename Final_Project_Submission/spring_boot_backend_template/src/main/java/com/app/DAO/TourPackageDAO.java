package com.app.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojo.TourPackage;
import com.app.pojo.User;

@Repository
public interface TourPackageDAO extends JpaRepository<TourPackage, Integer>{
	
	List<TourPackage> findByFromSourceAndToDestinationAndDepartureDate(String fromSource, String toDestination, String date);
	
}