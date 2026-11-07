package com.klef.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.parking.entity.Parkingg;
import com.klef.parking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController
{
	@Autowired
	private ParkingService service;

	@GetMapping("/")
	public String test()
	{
		return "Parking Management System";
	}

	// Add Parking
	@PostMapping("/add")
	public ResponseEntity<Parkingg> addParking(@RequestBody Parkingg p)
	{
		Parkingg parking = service.addParking(p);
		return ResponseEntity.status(201).body(parking);
	}

	
	@GetMapping("/displayall")
	public ResponseEntity<List<Parkingg>> displayAllParkings()
	{
		List<Parkingg> parkings = service.displayAllParkings();
		return ResponseEntity.status(200).body(parkings);
	}

	
	@GetMapping("/display")
	public ResponseEntity<?> displayParkingById(@RequestParam Long parkingId)
	{
		Parkingg parking = service.displayParkingById(parkingId);

		if(parking != null)
		{
			return ResponseEntity.status(200).body(parking);
		}
		else
		{
			return ResponseEntity.status(404).body("Parking ID Not Found");
		}
	}

	
	@PutMapping("/update")
	public ResponseEntity<?> updateParking(@RequestBody Parkingg parking)
	{
		Parkingg p = service.updateParking(parking);

		if(p != null)
		{
			return ResponseEntity.ok(p);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking ID Not Found");
		}
	}


	@DeleteMapping("/delete/{parkingId}")
	public ResponseEntity<String> deleteParkingById(@PathVariable Long parkingId)
	{
		String message = service.deleteParkingById(parkingId);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	
	@GetMapping("/displaybyname/{name}")
	public ResponseEntity<List<Parkingg>> displayParkingsByName(@PathVariable String name)
	{
		List<Parkingg> parkings = service.displayParkingsByName(name);
		return ResponseEntity.status(200).body(parkings);
	}

	
	@GetMapping("/displaybytype/{type}")
	public ResponseEntity<List<Parkingg>> displayParkingsByType(@PathVariable String type)
	{
		List<Parkingg> parkings = service.displayParkingsByType(type);
		return ResponseEntity.status(200).body(parkings);
	}
}