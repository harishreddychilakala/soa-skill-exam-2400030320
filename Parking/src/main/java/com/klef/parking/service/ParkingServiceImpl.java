package com.klef.parking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.parking.entity.Parkingg;
import com.klef.parking.repository.ParkingRepository;

@Service
public class ParkingServiceImpl implements ParkingService
{
	@Autowired
	private ParkingRepository repo;

	@Override
	public Parkingg addParking(Parkingg parking)
	{
		return repo.save(parking);
	}

	@Override
	public List<Parkingg> displayAllParkings()
	{
		return repo.findAll();
	}

	@Override
	public Parkingg updateParking(Parkingg p)
	{
		Optional<Parkingg> optional = repo.findById(p.getParkingId());

		if(optional.isPresent())
		{
			Parkingg parking = optional.get();

			parking.setName(p.getName());
			parking.setType(p.getType());
			parking.setStatus(p.getStatus());
			parking.setLocation(p.getLocation());

			return repo.save(parking);
		}
		else
		{
			return null;
		}
	}

	@Override
	public Parkingg displayParkingById(Long parkingId)
	{
		return repo.findById(parkingId).orElse(null);
	}

	@Override
	public String deleteParkingById(Long parkingId)
	{
		boolean status = repo.existsById(parkingId);

		if(status)
		{
			repo.deleteById(parkingId);
			return "Parking Deleted Successfully";
		}
		else
		{
			return "Parking ID Not Found";
		}
	}

	@Override
	public List<Parkingg> displayParkingsByName(String name)
	{
		return repo.findByName(name);
	}

	@Override
	public List<Parkingg> displayParkingsByType(String type)
	{
		return repo.findByType(type);
	}
}