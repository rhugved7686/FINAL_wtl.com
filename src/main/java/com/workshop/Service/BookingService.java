package com.workshop.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.Entity.Booking;
import com.workshop.Entity.Vendor;
import com.workshop.Repo.BookingRepo;
import com.workshop.Repo.VendorRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingService {
	
	@Autowired
	BookingRepo repo;
	
	@Autowired
	private VendorRepository vendorRepo;
	
	
	public void saveBooking(Booking booking ) {
		repo.save(booking);
	}
	
	 public String getLastUsedBookingId() {
	        // Fetch the maximum booking ID from the database
	        String lastUsedId = repo.findMaxBookingId();
	        
	        // If lastUsedId is null, return 0
	        if (lastUsedId == null) {
	            return "";
	        }
	        
	        return lastUsedId;
	    }
	 
	 public List<Booking> getBooking(String userid){
		 return repo.findByUserId(userid);
	 }
	 
	 public List<Booking> getAll(){
		 return repo.findAll();
	 }
	 
	 public Booking findBookingbyId(int id) {
		    // Fetch the booking from the repository by ID with its associated vendor (EAGER fetch)
		    Booking b = this.repo.findById(id).orElse(null);

		    // Check if the booking exists
		    if (b != null) {
		        // Return the booking with the vendor included
		        return b;
		    } else {
		        // If no booking is found, return null
		        return null;
		    }
		}
	 
	 
	


	 public List<Booking> getAllBookings() {
        return repo.findAllByOrderByIdDesc();
    }

	 
	 public void deleteBooking(int id ) {
		 this.repo.deleteById(id);
	 }

	//  public void deleteBooking1(int id) {
	// 	repo.delete(id);
		
	// }
      
    @Transactional
		public String deleteBookingByBookingId(String bookingId) {
        // Check if the booking exists before attempting to delete
		//System.out.println(bookingId);
        if (repo.existsByBookingId(bookingId)) {
            repo.deleteByBookingId(bookingId); // Use the custom delete method
            return "Booking with ID " + bookingId + " has been deleted successfully.";
        } else {
            return "Booking with ID " + bookingId + " not found.";
        }
    }
    
    public Booking updateStatus(int id, int newStatus) {
    	Booking b = this.repo.findById(id).get();
    	b.setStatus(newStatus);
    	return this.repo.save(b);
    }
    
    public List<Booking> getBookingByStatus(int status) {
        return this.repo.findAll()
                         .stream() 
                         .filter(b -> b.getStatus() == status)  
                         .collect(Collectors.toList());  
    }
    
    
    public Booking customBooking(Booking booking) {
        try {
            booking.setBookingType("custom_booking");
            System.out.println(booking);
            return this.repo.save(booking);
        } catch (Exception e) {
            // Log the error
            throw new RuntimeException("Error saving the booking", e);
        }
    }

    
    
    
    public Booking assignVendorToBooking(int bookingId, Long vendorId) {
        // Step 1: Fetch the existing booking from the database using the bookingId
        Optional<Booking> bookingOptional = this.repo.findById(bookingId);
        
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();  // Get the existing booking
            
            // Step 2: Fetch the vendor by vendorId
            Optional<Vendor> vendorOptional = this.vendorRepo.findById(vendorId);
            
            if (vendorOptional.isPresent()) {
                Vendor vendor = vendorOptional.get();  // Get the existing vendor
                
                // Step 3: Assign the vendor to the booking
                booking.setVendor(vendor);
                
                // Step 4: Save the updated booking (the vendor is now assigned to this specific booking row)
                return this.repo.save(booking);
            } else {
                // Vendor not found, returning null or can log the error if necessary
                System.out.println("Vendor with ID " + vendorId + " not found.");
                return null;
            }
        } else {
            // Booking not found, returning null or can log the error if necessary
            System.out.println("Booking with ID " + bookingId + " not found.");
            return null;
        }
    }



}
