

package com.workshop.Controller;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.workshop.DTO.CityDTO;
import com.workshop.DTO.PriceUpdateRequest;
import com.workshop.DTO.StateDTO;
import com.workshop.Entity.Booking;
import com.workshop.Entity.CabInfo;
import com.workshop.Entity.Cities;
import com.workshop.Entity.Popup;
import com.workshop.Entity.States;
import com.workshop.Entity.Tripprice;
import com.workshop.Entity.User;
import com.workshop.Entity.onewayTrip;
import com.workshop.Entity.roundTrip;
import com.workshop.Repo.StateRepository;
import com.workshop.Repo.Trip;
import com.workshop.Service.BookingService;
import com.workshop.Service.CabInfoService;
import com.workshop.Service.CitiesService;
import com.workshop.Service.PopupService;
import com.workshop.Service.StatesService;
import com.workshop.Service.TripService;
import com.workshop.Service.UserDetailServiceImpl;
import com.workshop.Service.TripRateService;



@RestController
//@RequestMapping
public class WtlAdminController {
    @Autowired
    BookingService ser;
    
     @Autowired
      private TripService tripSer;
     
     
     @Autowired
     CabInfoService cabser;
     
     @Autowired
     private TripRateService tripRateService;
     
    @Autowired
    private StatesService statesService;

    @Autowired
    private CitiesService citiesService;

     @Autowired
        PopupService service;
      
     
     @Autowired
        UserDetailServiceImpl userService;
     
//      private final String apiKey = "AIzaSyCelDo4I5cPQ72TfCTQW-arhPZ7ALNcp8w"; // Replace with your Google API key

    

 @GetMapping("/states/{id}")
    public ResponseEntity<States> getStateById(@PathVariable Long id) {
        return this.statesService.getStateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<States> getAllState(){
        return this.statesService.getAllState();
    }

    @Autowired
    private  StateRepository stateRepository;
    @GetMapping("/api/states")
    public List<StateDTO> getStates() {
        return stateRepository.findAll()
                .stream()
                .map(state -> new StateDTO(state.getId(), state.getName()))
                .collect(Collectors.toList());
    }
     

    @GetMapping("/city/all")
    public List<Cities> getAllCities() {
        return citiesService.getAllCities();
    }

    @GetMapping("/{id}")
    public Optional<Cities> getCityById(@PathVariable Long id) {
        return citiesService.getCityById(id);
    }

  
    
   @GetMapping("/cities/{stateId}")
public ResponseEntity<List<CityDTO>> getCitiesByState(@PathVariable Long stateId) {
    List<Cities> cities = citiesService.getCitiesByState(stateId);
    if (cities.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    }

    // Convert Cities entities to CityDTO
    List<CityDTO> cityDTOs = cities.stream()
        .map(city -> {
            CityDTO dto = new CityDTO();
            dto.setId(city.getId());
            dto.setName(city.getName());
            return dto;
        })
        .collect(Collectors.toList());

    return ResponseEntity.ok(cityDTOs);
}


    


    
    @PostMapping("/updateTripPricing")
       public ResponseEntity<Map<String, String>> updateTripPricing(@RequestBody Tripprice tripPricing) {
    // Update trip pricing logic
            this.tripRateService.updateTripPricing(tripPricing);

    // Create a map to return as a JSON object
              Map<String, String> response = new HashMap<>();
            response.put("message", "Trip pricing updated successfully!");

            return ResponseEntity.ok(response);
       }


        @PutMapping("/update-price/{id}")
    public ResponseEntity<onewayTrip> updateTripPrice(@PathVariable Long id, @RequestBody PriceUpdateRequest request) {
        onewayTrip updatedTrip = tripSer.updatePrice(id, request.getHatchback(), request.getSedan(),
                request.getSedanpremium(), request.getSuv(), request.getSuvplus(), request.getSourceState(), request.getSourceCity(), request.getDestinationState(), request.getDestinationCity());
        return ResponseEntity.ok(updatedTrip);
    }

    @PutMapping("/update-prices")
    public ResponseEntity<Map<String, String>> updatePrices(
            @RequestParam String sourceState,
            @RequestParam String destinationState,
            @RequestParam String sourceCity,
            @RequestParam String destinationCity,
            @RequestParam int hatchbackPrice,
            @RequestParam int sedanPrice,
            @RequestParam int sedanPremiumPrice,
            @RequestParam int suvPrice,
            @RequestParam int suvPlusPrice) {

        // Call the service to update trip prices
        tripSer.updatePrices(sourceState, destinationState, sourceCity, destinationCity,
                hatchbackPrice, sedanPrice, sedanPremiumPrice, suvPrice, suvPlusPrice);

        // Construct a JSON response
        Map<String, String> response = new HashMap<>();
        response.put("message", "Prices updated successfully");

        return ResponseEntity.ok(response);
    }


    
    @DeleteMapping("/delete-booking/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable String bookingId) {
        String responseMessage = ser.deleteBookingByBookingId(bookingId);
        if (responseMessage.contains("not found")) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the booking does not exist
        }
        return ResponseEntity.ok(responseMessage); // Return 200 OK with the success message
    }
    
    
    
           
        
    

    
    
    
    //  @PostMapping("/getPrice")
    //   public List<Trip> getPrice(@RequestBody Map<String, String> requestBody) {
    //       String to = requestBody.get("to");
    //       String from = requestBody.get("from");
    //       String tripType = requestBody.get("tripType");

    //       String city1 = userService.getLongNameByCity(to, apiKey);
    //       String[] parts = city1.split(" ");
    //       String cityName = parts[0];
    //       String city2 = userService.getLongNameByCity(from, apiKey);
    //       String[] parts1 = city2.split(" ");
    //       String cityName1 = parts1[0];
          
    //       System.out.println(city1);
    //       System.out.println(city2);
    //       System.out.println(cityName);
    //       System.out.println(cityName1);

    //       if ("oneWay".equals(tripType)) {
    //           return tripSer.getonewayTrip(cityName, cityName1);
    //       } else if ("roundTrip".equals(tripType)) {
    //           return tripSer.getRoundTrip(cityName, cityName1);
    //       } else {
    //           // Handle other cases or return an empty list if needed
    //           return new ArrayList<>();
    //       }
    //   }
     
    
     
     @Autowired
     private CabInfoService cabInfoService;
 
     
    
    @Autowired
    private BookingService bookingService;

    @GetMapping("/details")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings); // Return the list of bookings with HTTP 200 OK status
    }
    
    
    @GetMapping("/booking/{id}")  // Define the path variable for booking ID
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
        // Fetch the booking by ID using the service
        Booking booking = bookingService.findBookingbyId(id);

        // Check if the booking is found
        if (booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.OK);  // Return the booking with a 200 OK status
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // If not found, return 404 Not Found
        }
    }
    
    
    


    @PutMapping("/{id}/status")
    public ResponseEntity<Booking> changeStatus(
            @PathVariable int id, 
            @RequestBody Map<String, Integer> requestBody) {
        
        int newStatus = requestBody.get("status"); // Extract status from the request body

        try {
            // Call the service to update the booking status
            Booking updatedBooking = bookingService.updateStatus(id, newStatus);
            return ResponseEntity.ok(updatedBooking); // Return the updated booking
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if booking not found
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
    	this.bookingService.deleteBooking(id);
    }
    
    @GetMapping("/getStatus/{status}")
	 public List<Booking> getBookingByStatus(@PathVariable int status){
		 return this.bookingService.getBookingByStatus(status);
	 }
    
    
    @PutMapping("/update-roundway-prices")
    public ResponseEntity<Map<String, String>> updateRoundWayPrices(
            @RequestParam String sourceState,
            @RequestParam String destinationState,
            @RequestParam String sourceCity,
            @RequestParam String destinationCity,
            @RequestParam int hatchbackPrice,
            @RequestParam int sedanPrice,
            @RequestParam int sedanPremiumPrice,
            @RequestParam int suvPrice,
            @RequestParam int suvPlusPrice) {

        // Call the service to update trip prices
        tripSer.updatePricesByRoundWay(sourceState, destinationState, sourceCity, destinationCity,
                hatchbackPrice, sedanPrice, sedanPremiumPrice, suvPrice, suvPlusPrice);

        // Construct a JSON response
        Map<String, String> response = new HashMap<>();
        response.put("message", "Prices updated successfully");

        return ResponseEntity.ok(response);
    }
    
    
    @PostMapping("/customBooking")
    public ResponseEntity<Booking> createCustomBooking(@RequestBody Booking booking) {
        try {
            // Log the incoming request to ensure the fields are being passed correctly
            System.out.println("Booking received: " + booking.toString());

            // Pass the booking to the service layer for processing
            Booking savedBooking = bookingService.customBooking(booking);

            // Log the saved booking to verify its values
            System.out.println("Booking saved: " + savedBooking.toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PutMapping("/{bookingId}/assignVendor/{vendorId}")
    public ResponseEntity<Booking> assignVendorToBooking(
            @PathVariable int bookingId, 
            @PathVariable Long vendorId) {

        // Call the service method to assign vendor
        Booking updatedBooking = bookingService.assignVendorToBooking(bookingId, vendorId);
        
        if (updatedBooking == null) {
            // If the booking or vendor was not found, return a 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // If the vendor is assigned successfully, return the updated booking
        return ResponseEntity.ok(updatedBooking);
    }    
}
