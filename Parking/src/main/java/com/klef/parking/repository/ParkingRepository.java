package com.klef.parking.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.parking.entity.Parkingg;

@Repository
public interface ParkingRepository extends JpaRepository<Parkingg, Long> {
	List<Parkingg> findByType(String type);
	
	List<Parkingg> findByName(String name);

}
