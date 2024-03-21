package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.Booking;
import com.app.pojo.TourPackage;
import com.app.service.BookingService;
import com.app.service.TourPackageService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private TourPackageService tourPackageService;

	@Autowired
	private BookingService bookService;

	// getting list of available buses from database and sending it to the next
	// request
	@GetMapping
	public ResponseEntity<Object> showFilteredTourPackages(@RequestParam String fromSource,
			@RequestParam String toDestination, @RequestParam String departureDate, HttpSession session) {
		try {
			List<TourPackage> packages = tourPackageService.getFilteredTourPackages(fromSource, toDestination,
					departureDate);
			session.setAttribute("pkgs", packages);
			if (packages.isEmpty())
				throw new Exception("jhj");
			return ResponseEntity.status(HttpStatus.OK).body(packages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No buses available");
		}

	}
	
	@GetMapping("/records")
	public ResponseEntity<Object> showTourPackages(@RequestParam String from, @RequestParam String to,
			@RequestParam String date) {
		try {
			List<TourPackage> packages = tourPackageService.getFilteredTourPackages(from, to, date);
			return ResponseEntity.status(HttpStatus.OK).body(packages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("could not found resource");
		}

	}

	// after clicking book button getting all info of a perticular tourPackage
	// + each option will have book option . after clicking it. user will be
	// navigated to the next page
	@GetMapping("/book/{id}")
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

	@PostMapping("/book/payAndBook")
	public ResponseEntity<Object> bookPackage(@RequestBody Booking booking,
			HttpSession session) {

		try {
			System.out.println("in request controller");
//			int userId = (int) session.getAttribute("userId");
			//unable to set session while making request from frontend
			Booking newBooking = bookService.BookPackage(booking, booking.getUserId());
			return ResponseEntity.status(HttpStatus.CREATED).body("booked tickets successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("booking failed");
		}

	}

	@GetMapping("/myBookings")
	public ResponseEntity<Object> showBookings(@RequestParam int userId) {
		try {
			//int userId = (int) session.getAttribute("userId");
			List<Booking> myBookings = bookService.getMyBookings(userId);
			if (myBookings == null)
				throw new Exception();
			return ResponseEntity.status(HttpStatus.OK).body(myBookings);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("No bookings found");
		}

	}
	
	@DeleteMapping("/myBookings/{id}")
	public ResponseEntity<Object> cancelBooking(@PathVariable int id){
		try {
			bookService.cancelBooking(id);
			return ResponseEntity.status(HttpStatus.OK).body("Booking canceled, refund initiated"); 
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not found resource.....");
		}
		
		
	}

}
