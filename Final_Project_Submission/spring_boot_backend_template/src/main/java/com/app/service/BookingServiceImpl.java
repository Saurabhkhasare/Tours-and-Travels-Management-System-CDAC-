package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.BookingDAO;
import com.app.pojo.Booking;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDAO bookingDAO;
	
	@Override
	public Booking BookPackage(Booking booking,int userId) {
		booking.setUserId(userId);
		return bookingDAO.save(booking);
	}

	@Override
	public List<Booking> getMyBookings(int userId) {
		
		return bookingDAO.findByUserId(userId);
	}

	@Override
	public void cancelBooking(int id) {
		
		bookingDAO.deleteById(id);
	}

	
}
