package com.app.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojo.Booking;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Integer> {

	List<Booking> findByUserId(int id);
}
