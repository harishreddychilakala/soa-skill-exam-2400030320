
package com.klef.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.parking.entity.Parkingg;
import com.klef.parking.repository.ParkingRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParkingRepositoryTest {

    @Autowired
    private ParkingRepository repository;

    private Long existingParkingId;

    @BeforeEach
    void setUp() {

        repository.deleteAll();

        Parkingg p1 = new Parkingg();
        p1.setName("Harish Parking");
        p1.setType("Car");
        p1.setStatus("Available");
        p1.setLocation("Vijayawada");

        Parkingg p2 = new Parkingg();
        p2.setName("Anand Parking");
        p2.setType("Bike");
        p2.setStatus("Occupied");
        p2.setLocation("Guntur");

        Parkingg p3 = new Parkingg();
        p3.setName("Sunil Parking");
        p3.setType("Car");
        p3.setStatus("Available");
        p3.setLocation("Hyderabad");

        Parkingg savedParking = repository.saveAndFlush(p1);
        existingParkingId = savedParking.getParkingId();

        repository.save(p2);
        repository.save(p3);
        repository.flush();
    }

    // 1. Find All
    @Test
    void testFindAllParkings() {

        List<Parkingg> parkings = repository.findAll();

        assertNotNull(parkings);
        assertEquals(3, parkings.size());

        System.out.println("Total Parkings: " + parkings.size());
    }

    // 2. Find by ID
    @Test
    void testFindParkingById() {

        Optional<Parkingg> parking =
                repository.findById(existingParkingId);

        assertTrue(parking.isPresent());
        assertEquals(existingParkingId,
                parking.get().getParkingId());

        Optional<Parkingg> invalidParking =
                repository.findById(Long.MAX_VALUE);

        assertFalse(invalidParking.isPresent());

        System.out.println("Parking ID: " +
                parking.get().getParkingId());
        System.out.println("Parking Name: " +
                parking.get().getName());
    }

    // 3. Find by Derived Query
    @Test
    void testFindParkingByName() {

        List<Parkingg> parkings =
                repository.findByName("Harish Parking");

        assertNotNull(parkings);
        assertFalse(parkings.isEmpty());
        assertEquals(1, parkings.size());
        assertEquals("Harish Parking",
                parkings.get(0).getName());

        System.out.println("Parking Name: " +
                parkings.get(0).getName());
    }

    // 4. Count
    @Test
    void testParkingCount() {

        long count = repository.count();

        assertEquals(3L, count);

        System.out.println("Total Parkings in Database: " + count);
    }

    // 5. Entity Details
    @Test
    void testParkingDetails() {

        Optional<Parkingg> parking =
                repository.findById(existingParkingId);

        assertTrue(parking.isPresent());

        Parkingg foundParking = parking.get();

        assertNotNull(foundParking.getParkingId());
        assertNotNull(foundParking.getName());
        assertNotNull(foundParking.getType());
        assertNotNull(foundParking.getStatus());
        assertNotNull(foundParking.getLocation());
        assertNotNull(foundParking.getCreatedAt());

        System.out.println("Parking ID: " +
                foundParking.getParkingId());
        System.out.println("Name: " +
                foundParking.getName());
        System.out.println("Type: " +
                foundParking.getType());
        System.out.println("Status: " +
                foundParking.getStatus());
        System.out.println("Location: " +
                foundParking.getLocation());
    }
}
