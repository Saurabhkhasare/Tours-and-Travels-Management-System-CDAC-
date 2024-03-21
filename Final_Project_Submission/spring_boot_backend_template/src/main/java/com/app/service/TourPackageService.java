package com.app.service;

import java.util.List;

import com.app.pojo.TourPackage;

public interface TourPackageService {

	TourPackage addTourPackage(TourPackage tourPackage);

	List<TourPackage> getTourPackages();

	TourPackage updateTourPackage(TourPackage tourPackage, int id);

	void deleteTourPackage(int id);

	List<TourPackage> getFilteredTourPackages(String fromSource, String toDestination, String departureDate);

	TourPackage getPerticularTourPackage(int id);

}
