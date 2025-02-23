package com.workshop.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.Entity.onewayTrip;
import com.workshop.Entity.roundTrip;
import com.workshop.Repo.OnewayTripRepo;
import com.workshop.Repo.RoundTripRepo;
import com.workshop.Repo.Trip;

import jakarta.transaction.Transactional;

@Service
public class TripService {
	
	@Autowired
	private OnewayTripRepo repo;
	
	@Autowired
	RoundTripRepo roundrepo;
	
	public List<Trip> getonewayTrip(String from , String to){
		return repo.findBySourceCityAndDestinationCity(to, from);
		
	}
	
	
	public List<Trip> getRoundTrip(String from , String to){
		return roundrepo.findBySourceCityAndDestinationCity(to, from);
		
	}
	
	
	
	public int getRoundDistance(LocalDate localDate1, LocalTime time1, LocalDate localDate2, LocalTime time2, String distance) {
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

//		 LocalDate localDate1 = LocalDate.parse(localDate1);
//		    LocalDate localDate2 = LocalDate.parse(localDate2);
//		    LocalTime time1  = LocalTime.parse(time1, DateTimeFormatter.ISO_DATE);
//		    LocalTime time2  = LocalTime.parse(time2, DateTimeFormatter.ISO_DATE);
		
		    int Distance = Integer.parseInt(distance);
		    
		    int days = localDate1.until(localDate2).getDays() + 1;

		    // Calculate service and driver based on distance and days
		    int driver = (int) (days * 300);
		    int service = Distance * 2;
		    
		    if (service > 300 && days == 1) {
		        return -1; // Service is unavailable
		    } else if (service > 600 && days == 2) {
		        return -2; // Service is unavailable
		    } else if (service > 900 && days == 3) {
		        return -3; // Service is unavailable
		    } else if (service > 1200 && days == 4) {
		        return -4; // Service is unavailable
		    } else if (service > 1500 && days == 5) {
		        return -5; // Service is unavailable
		    } else if (service > 1800 && days == 6) {
		        return -6; // Service is unavailable
		    } else if (service > 2100 && days == 7) {
		        return -7; // Service is unavailable
		    } else if (service > 2400 && days == 8) {
		        return -8; // Service is unavailable
		    } else if (service > 2700 && days == 9) {
		        return -9; // Service is unavailable
		    } else if (service > 3000 && days == 10) {
		        return -10; // Service is unavailable
		    }

		    int roundDist = Distance * 2;
		    int intAmount = -1;
		    int dr = days * 300;


		    if (roundDist <= 300 && days == 1) {
		        intAmount = 300 * days;
		    } else if (roundDist > 300 && days == 1) {
		        int dPlus = roundDist - 300;
		        intAmount = 300 * days + dPlus;
		    } else if (roundDist <= 600 && days == 2) {
		        intAmount = 300 * days;
		    } else if (roundDist > 600 && days == 2) {
		        int dPlus = roundDist - 600;
		        intAmount = 300 * days + dPlus;
		    } else if (roundDist <= 900 && days == 3) {
		        intAmount = 300 * days;
		    } else if (roundDist > 900 && days == 3) {
		        int dPlus = roundDist - 900;
		        intAmount = 300 * days + dPlus;
		    } else if (roundDist <= 1200 && days == 4) {
		        intAmount = 300 * days;
		    } else if (roundDist > 1200 && days == 4) {
		        int dPlus = roundDist - 1200;
		        intAmount = 300 * days + dPlus;
		    } else if (roundDist <= 1500 && days == 5) {
		        intAmount = 300 * days;
		    } else if (roundDist > 1500 && days == 5) {
		        int dPlus = roundDist - 1500;
		        intAmount = 300 * days + dPlus;
		    } else if (roundDist <= 1800 && days == 6) {
		        intAmount = 300 * days;
		    } else if (roundDist > 1800 && days == 6) {
		        int dPlus = roundDist - 1800;
		        intAmount = 300 * days + dPlus;
		    } else if (roundDist <= 2100 && days == 7) {
		        intAmount = 300 * days;
		    } else if (roundDist > 2100 && days == 7) {
		        int dPlus = roundDist - 1500;
		        intAmount = 300 * days + dPlus;
		    } else if (roundDist <= 2400 && days == 8) {
		        intAmount = 300 * days;
		    } else if (roundDist > 2400 && days == 8) {
		        int dPlus = roundDist - 2400;
		        intAmount = 300 * days + dPlus;
		    } else if (roundDist <= 2700 && days == 9) {
		        intAmount = 300 * days;
		    } else if (roundDist > 2700 && days == 9) {
		        int dPlus = roundDist - 2700;
		        intAmount = 300 * days + dPlus;
		    } else if (roundDist <= 3000 && days == 10) {
		        intAmount = 300 * days;
		    } else if (roundDist > 3000 && days == 10) {
		        int dPlus = roundDist - 3000;
		        intAmount = 300 * days + dPlus;
		    } else if (roundDist <= 30) {
		        // Handle the response here or return a specific value
		        intAmount = -1; // Service is unavailable for the given distance
		    }

		    if (intAmount == -1) {
		        // Handle the response here or return a specific value
		        System.out.println("Service is unavailable for the given distance");
		        // You can replace the print statement with your preferred way of handling this case.
		    }
		    
		    return intAmount;
		    
		
	}
	

	public onewayTrip updatePrice(Long id, int hatchback, int sedan, int sedanpremium, int suv, int suvplus, String sourceState,String sourceCity, String destinationState,String destinationCity) {
        Optional<onewayTrip> tripOptional = repo.findById(id);
        if (tripOptional.isPresent()) {
            onewayTrip trip = tripOptional.get();
            trip.setHatchback(hatchback);
            trip.setSedan(sedan);
            trip.setSedanpremium(sedanpremium);
            trip.setSuv(suv);
            trip.setSuvplus(suvplus);
			trip.setSourceState(sourceState);
            trip.setSourceCity(sourceCity);
            trip.setDestinationState(destinationState);
            trip.setDestinationCity(destinationCity);
            return repo.save(trip);
        } else {
            throw new RuntimeException("Trip not found with ID: " + id);
        }
    }
	
	

	// Method to update trip prices based on sourceState and destinationState
    public void updatePrices(String sourceState, String destinationState, String sourceCity, String destinationCity ,int hatchbackPrice, int sedanPrice, int sedanPremiumPrice, int suvPrice, int suvPlusPrice) {
        List<onewayTrip> trips = this.repo.findBySourceStateAndDestinationStateAndSourceCityAndDestinationCity(sourceState, destinationState,sourceCity,destinationCity);
        
        for (onewayTrip trip : trips) {
            trip.setHatchback(hatchbackPrice);
            trip.setSedan(sedanPrice);
            trip.setSedanpremium(sedanPremiumPrice);
            trip.setSuv(suvPrice);
            trip.setSuvplus(suvPlusPrice);
        }
        this.repo.saveAll(trips);
    }
	// public onewayTrip updatePrice(String sourceState, onewayTrip oneway){
	// 	oneway.setSourceState(sourceState);
	// 	return this.repo.save(oneway);
	// }

	public  onewayTrip updatePrice(String sourceState, String destinationState, onewayTrip oneway){
		oneway.setSourceState(sourceState);
		oneway.setDestinationState(destinationState);
		return this.repo.save(oneway);
	}
	
	
	public void updatePricesByRoundWay(String sourceState, String destinationState, String sourceCity, String destinationCity ,int hatchbackPrice, int sedanPrice, int sedanPremiumPrice, int suvPrice, int suvPlusPrice) {
        List<roundTrip> trips = this.roundrepo.findBySourceStateAndDestinationStateAndSourceCityAndDestinationCity(sourceState, destinationState,sourceCity,destinationCity);
        
        for (roundTrip trip : trips) {
            trip.setHatchback(hatchbackPrice);
            trip.setSedan(sedanPrice);
            trip.setSedanpremium(sedanPremiumPrice);
            trip.setSuv(suvPrice);
            trip.setSuvplus(suvPlusPrice);
        }
        this.roundrepo.saveAll(trips);
    }

}

