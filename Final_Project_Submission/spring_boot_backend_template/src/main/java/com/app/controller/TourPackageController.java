package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.TourPackage;
import com.app.service.TourPackageService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class TourPackageController {

	@Autowired
	private TourPackageService tourPackageService;

	@PostMapping("/register")
	public ResponseEntity<Object> addTourPackage(@RequestBody TourPackage tourPackage) {
		try {
			TourPackage newTourPackage = tourPackageService.addTourPackage(tourPackage);
			return ResponseEntity.status(HttpStatus.OK)
					.body("TourPackage registered successfully with busname : " + newTourPackage.getBusName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not register tour package");
		}

	}
	
	@GetMapping("/packages")
	public ResponseEntity<Object> showTourPackages() {
		try {
			List<TourPackage> packages = tourPackageService.getTourPackages();
			return ResponseEntity.status(HttpStatus.OK).body(packages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("could not found resource");
		}

	}

	// put this method in dashboard controller
	@GetMapping("/record")
	public ResponseEntity<Object> showTourPackages(@RequestParam String from, @RequestParam String to,
			@RequestParam String date) {
		try {
			List<TourPackage> packages = tourPackageService.getFilteredTourPackages(from, to, date);
			return ResponseEntity.status(HttpStatus.OK).body(packages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("could not found resource");
		}

	}
	
	@GetMapping("/records/{id}")
	public ResponseEntity<Object> findPerticularTourPackage(@PathVariable int id) {
		try {
			TourPackage pkg = tourPackageService.getPerticularTourPackage(id);
			if (pkg == null)
				throw new Exception();
			return ResponseEntity.status(HttpStatus.OK).body(pkg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No buses available");
		}

	}

	@PutMapping("/records/{id}")
	public ResponseEntity<Object> updateTourPackage(@PathVariable int id, @RequestBody TourPackage tourPackage) {
		try {
			TourPackage newTourPackage = tourPackageService.updateTourPackage(tourPackage, id);
			System.out.println(newTourPackage.getBusName());
			if (newTourPackage != null)
				return ResponseEntity.status(HttpStatus.OK).body("Tour package updated succcessfully");
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not found resource");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not found resource.....");
		}

	}

	@DeleteMapping("/records/{id}")
	public ResponseEntity<Object> deleteTourPackage(@PathVariable int id) {
		try {
		tourPackageService.deleteTourPackage(id);
		return ResponseEntity.status(HttpStatus.OK).body("Tour package deleted succcessfully");
		}
		catch (Exception e){
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not found resource.....");
		}

	}

}
