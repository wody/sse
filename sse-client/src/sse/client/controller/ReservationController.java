package sse.client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.joda.time.LocalDate;

import sse.client.dto.RoomReservationDTO;
import sse.ejb.ReservationService;
import sse.model.Customer;
import sse.model.Reservation;
import sse.model.Room;

@ManagedBean(name = "reservationCtrl")
@SessionScoped
public class ReservationController {
	
	private List<Room> freeRooms;
	private List<Customer> customers;
	private Room selectedRoom;
	private RoomReservationDTO roomToRemove;
	private Customer selectedCustomer;
	private Double selectedDiscount;
	private List<RoomReservationDTO> selectedRoomReservations;

	private Date fromDate;
	private Date toDate;

	@EJB
	ReservationService reservationService;

	public ReservationController() {
		selectedRoomReservations = new ArrayList<RoomReservationDTO>();
		selectedDiscount = new Double(0d);
		
	}

	public Room getSelectedRoom() {
		return selectedRoom;
	}

	public void setSelectedRoom(Room selectedRoom) {

		RoomReservationDTO r = new RoomReservationDTO(selectedRoom);
		selectedRoomReservations.add(r);
		this.selectedRoom = selectedRoom;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public List<Room> getFreeRooms() {

		return freeRooms;
	}

	public void setFreeRooms(List<Room> freeRooms) {
		this.freeRooms = freeRooms;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	

	public List<RoomReservationDTO> getSelectedRoomReservations() {
		return selectedRoomReservations;
	}

	public void setSelectedRoomReservations(List<RoomReservationDTO> selectedRoomReservations) {
		this.selectedRoomReservations = selectedRoomReservations;
	}
		

	public RoomReservationDTO getRoomToRemove() {
		return roomToRemove;
	}

	public void setRoomToRemove(RoomReservationDTO roomToRemove) {
		System.out.println("DEBUG: setRoomtoRemove: id=" + roomToRemove.getRoom().getId() + " selectedRate = " + roomToRemove.getSelectedRate());
		
		selectedRoomReservations.remove(roomToRemove);
		this.roomToRemove = roomToRemove;
	}

	public Double getSelectedDiscount() {
		return selectedDiscount;
	}

	public void setSelectedDiscount(Double selectedDiscount) {
		this.selectedDiscount = selectedDiscount;
	}

	public String findFreeRooms() {
		// LocalDate transformedFromDate = new LocalDate(fromDate);
		// LocalDate transformedToDate = new LocalDate(toDate);
		//
		freeRooms = reservationService.getFreeRoomsInTimespan(/*
															 * transformedFromDate,
															 * transformedToDate
															 */);
		//
		// System.out.println("DEBUG: FREE rooms: " + freeRooms);
		// System.out.println("To date" + toDate + " LocalToDate: " +
		// transformedToDate);
		//
		// if (freeRooms != null) {
		// selectedRoom = freeRooms.get(freeRooms.size() - 1);
		// }
		// else {
		// System.out.println("DEBUG: no Free Rooms");
		// }
		return "teste";
	}

	public String doReservation(){
		for ( RoomReservationDTO res: selectedRoomReservations) {
			System.out.println("Roomid: " + res.getRoom().getId() + " selectedRate: " + res.getSelectedRate());
			Reservation reservation = new Reservation();
			reservation.setCustomer(selectedCustomer);
			reservation.setRoom(res.getRoom());
			reservation.setDiscount(selectedDiscount);
			reservation.setRoomRate(res.getSelectedRate());
			reservation.setOccupacyAdult(res.getOccupacyAdult());
			reservation.setOccupacyChild(res.getOccupacyChild());
//			reservation.setFromDate(new LocalDate(fromDate));
//			reservation.setToDate(new LocalDate(toDate));
			
			reservationService.doReservation(reservation);
			
		}
		
		selectedRoomReservations.clear();
		selectedCustomer = null;
		fromDate = null;
		toDate = null;
		selectedDiscount = 0d;
				
		return "";
	}
	
	//TODO write a validator?
	public Boolean getAllowSubmit(){
		Boolean allow = true;
		if (selectedRoomReservations.isEmpty()){
			allow = false;
		}
		else{
			for (RoomReservationDTO roomreservation : selectedRoomReservations) {
				if(roomreservation.getSelectedRate() == null ){
					allow = false;
				}					
			}
		}
		
		if(toDate == null || fromDate == null || selectedCustomer == null){
			allow = false;
		}
		return !allow;
	}

	public String load() {

		customers = reservationService.getAllCustomers();
		return "reservation.xhtml";
	}

}
