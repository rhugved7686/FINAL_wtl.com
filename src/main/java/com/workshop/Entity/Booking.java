package com.workshop.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "user_booking")
@Entity
public class Booking {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fromLocation;
    private String toLocation;
    private String tripType;

    private LocalDate startDate;
    private LocalDate returnDate;
    private String time;
    private String distance;

    private String userId;        // userId (consider renaming if more meaningful)
    private String bookingId;
    private String name;
    private String email;
    private String phone;
    private String userPickup;    // user_pickup renamed to follow Java naming conventions
    private String userDrop;      // user_drop renamed to follow Java naming conventions

    private LocalDate date;
    private String userTripType;  // user_trip_type renamed to follow Java naming conventions
    private String bookid;        // bookid can be renamed to something more meaningful
    private String car;
    private String baseAmount;

    private Integer amount = 0;
    private Integer status = 0;
    private String driverBhata = "0";  // Assuming it's meant to be a String, or it can be Integer if numeric
    private Integer nightCharges = 0;
    private Integer gst = 0;
    private Integer serviceCharge = 0;
    private String offer;
    private Integer offerPartial = 0;
    private String offerAmount;
    private String txnId = "0";  // Renamed from txnid to txnId (camelCase)
    private String payment;
    private LocalDate dateEnd;
    private String timeEnd;
    private String bookingType;
    private String description;
    
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)  // Fetch the vendor eagerly
    @JoinColumn(name = "vendor_id", nullable = true)
    private Vendor vendor;




	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserPickup() {
		return userPickup;
	}
	public void setUserPickup(String userPickup) {
		this.userPickup = userPickup;
	}
	public String getUserDrop() {
		return userDrop;
	}
	public void setUserDrop(String userDrop) {
		this.userDrop = userDrop;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getUserTripType() {
		return userTripType;
	}
	public void setUserTripType(String userTripType) {
		this.userTripType = userTripType;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getBaseAmount() {
		return baseAmount;
	}
	public void setBaseAmount(String baseAmount) {
		this.baseAmount = baseAmount;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDriverBhata() {
		return driverBhata;
	}
	public void setDriverBhata(String driverBhata) {
		this.driverBhata = driverBhata;
	}
	public Integer getNightCharges() {
		return nightCharges;
	}
	public void setNightCharges(Integer nightCharges) {
		this.nightCharges = nightCharges;
	}
	public Integer getGst() {
		return gst;
	}
	public void setGst(Integer gst) {
		this.gst = gst;
	}
	public Integer getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(Integer serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getOffer() {
		return offer;
	}
	public void setOffer(String offer) {
		this.offer = offer;
	}
	public Integer getOfferPartial() {
		return offerPartial;
	}
	public void setOfferPartial(Integer offerPartial) {
		this.offerPartial = offerPartial;
	}
	public String getOfferAmount() {
		return offerAmount;
	}
	public void setOfferAmount(String offerAmount) {
		this.offerAmount = offerAmount;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public LocalDate getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getBookingType() {
		return bookingType;
	}
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	
	@Override
	public String toString() {
		return "Booking [id=" + id + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", tripType="
				+ tripType + ", startDate=" + startDate + ", returnDate=" + returnDate + ", time=" + time
				+ ", distance=" + distance + ", userId=" + userId + ", bookingId=" + bookingId + ", name=" + name
				+ ", email=" + email + ", phone=" + phone + ", userPickup=" + userPickup + ", userDrop=" + userDrop
				+ ", date=" + date + ", userTripType=" + userTripType + ", bookid=" + bookid + ", car=" + car
				+ ", baseAmount=" + baseAmount + ", amount=" + amount + ", status=" + status + ", driverBhata="
				+ driverBhata + ", nightCharges=" + nightCharges + ", gst=" + gst + ", serviceCharge=" + serviceCharge
				+ ", offer=" + offer + ", offerPartial=" + offerPartial + ", offerAmount=" + offerAmount + ", txnId="
				+ txnId + ", payment=" + payment + ", dateEnd=" + dateEnd + ", timeEnd=" + timeEnd + ", bookingType="
				+ bookingType + ", description=" + description + ", vendor=" + vendor + "]";
	}
	public Booking(int id, String fromLocation, String toLocation, String tripType, LocalDate startDate,
			LocalDate returnDate, LocalTime time, String distance, String userid, String bookingId, String name,
			String email, String phone, Vendor vendor) {
		super();
		this.id = id;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.tripType = tripType;
		this.startDate = startDate;
		this.returnDate = returnDate;
		this.time = "";
		this.distance = distance;
		this.userId = userid;
		this.bookingId = bookingId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.userPickup = fromLocation;
		this.userDrop = toLocation;
		this.date = startDate;
		this.userTripType = tripType;
		this.bookid = bookingId;
		this.car = "";
		this.baseAmount = "0";
		this.amount = 0;
		this.vendor=vendor;
	}
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}
