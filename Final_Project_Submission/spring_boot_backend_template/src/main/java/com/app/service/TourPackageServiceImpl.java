package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.DAO.TourPackageDAO;
import com.app.pojo.TourPackage;

@Service
public class TourPackageServiceImpl implements TourPackageService {
	
	
	@Autowired
	private TourPackageDAO tourPackageDAO;
	

	@Override
	public TourPackage addTourPackage(TourPackage tourPackage) {
		return tourPackageDAO.save(tourPackage);
	}


	@Override
	public List<TourPackage> getTourPackages() {
	
		return tourPackageDAO.findAll();
	}


	@Override
	public TourPackage updateTourPackage(TourPackage tourPackage,int id) {
		Optional<TourPackage> oldTourPackage= tourPackageDAO.findById(id);
	    if(oldTourPackage.isPresent()) {
	    	TourPackage updateTourPackage= oldTourPackage.get();
	       updateTourPackage.setBusName(tourPackage.getBusName());
	       updateTourPackage.setDepartureDate(tourPackage.getDepartureDate());
	       updateTourPackage.setDepartureTime(tourPackage.getDepartureTime());
	       updateTourPackage.setFromSource(tourPackage.getFromSource());
	       updateTourPackage.setToDestination(tourPackage.getToDestination());
	       updateTourPackage.setPrice(tourPackage.getPrice());
	       return tourPackageDAO.save(updateTourPackage);
	       }
	    else {
	    	return null;
	    }
	}


	@Override
	public void deleteTourPackage(int id) {
		 tourPackageDAO.deleteById(id);
	}


	@Override
	public List<TourPackage> getFilteredTourPackages(String fromSource, String toDestination, String departureDate) {
		
		return tourPackageDAO.findByFromSourceAndToDestinationAndDepartureDate(fromSource,toDestination,departureDate);
	}


	@Override
	public TourPackage getPerticularTourPackage(int id) {
		Optional<TourPackage> optionalTourPackage =  tourPackageDAO.findById(id);
		if(optionalTourPackage.isPresent()) {
			TourPackage pkg= optionalTourPackage.get(); 
			return pkg;
		}
		return null;
	}

}
