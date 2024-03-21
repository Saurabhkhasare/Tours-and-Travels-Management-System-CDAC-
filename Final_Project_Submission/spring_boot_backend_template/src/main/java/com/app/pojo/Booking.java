package com.app.pojo;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bookings")
public class Booking {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int noOfPersons;
	private Double totalAmount;
	private String fromSource;
	private String toDestination;
	private String busName;
	private int userId;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private boolean tripStatus;
	private String fileName;
	
	public Booking() {
		super();
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", noOfPersons=" + noOfPersons + ", totalAmount=" + totalAmount + ", fromSource="
				+ fromSource + ", toDestination=" + toDestination + ", userId=" + userId + ", departureDate="
				+ departureDate + ", departureTime=" + departureTime + ", tripStatus=" + tripStatus + ", fileName="
				+ fileName + "]";
	}

	public int getNoOfPersons() {
		return noOfPersons;
	}
	
	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getFromSource() {
		return fromSource;
	}

	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
	}

	public String getToDestination() {
		return toDestination;
	}

	public void setToDestination(String toDestination) {
		this.toDestination = toDestination;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public boolean isTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(boolean tripStatus) {
		this.tripStatus = tripStatus;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
