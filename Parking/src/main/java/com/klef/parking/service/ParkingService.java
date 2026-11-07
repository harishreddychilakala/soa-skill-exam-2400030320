package com.klef.parking.service;

import java.util.List;

import com.klef.parking.entity.Parkingg;

public interface ParkingService
{
	Parkingg addParking(Parkingg parking);

	List<Parkingg> displayAllParkings();

	Parkingg updateParking(Parkingg p);

	Parkingg displayParkingById(Long parkingId);

	String deleteParkingById(Long parkingId);

	List<Parkingg> displayParkingsByName(String name);

	List<Parkingg> displayParkingsByType(String type);
}