package sse.client.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.joda.time.LocalDate;

import sse.ejb.ReservationService;
import sse.model.Room;

@ManagedBean(name="reservationCtrl")
@SessionScoped
public class ReservationController {
	private List<Room> freeRooms;
	private Room selected;
	
	private Date fromDate;
	private Date toDate;
	
	@EJB
	ReservationService reservationService;
	
	public ReservationController() {
//		freeRooms = new ArrayList<Room>();
//
//		for (int i = 0; i < 3; i++) {
//			Room r = new Room();
//
//			r.setOccupancy(3);
//			r.setRateSingle(new BigDecimal(50+i));
//			r.setRateDouble(new BigDecimal(48+i));
//			r.setRateSingleOneChild(new BigDecimal(55+i));
//			r.setRateSingleTwoChildren(new BigDecimal(65+i));
//			r.setRateDoubleOneChild(new BigDecimal(58+i));
//			freeRooms.add(r);
//		}
//
//		selected = freeRooms.get(freeRooms.size() - 1);
	}

	
	public Room getSelected() {
		return selected;
	}

	public void setSelected(Room selected) {
		this.selected = selected;
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
	
	public String findFreeRooms(){
		LocalDate transformedFromDate = new LocalDate(fromDate);
		LocalDate transformedToDate = new LocalDate(toDate);
		
		freeRooms = reservationService.getFreeRoomsInTimespan(transformedFromDate, transformedToDate);
		
		System.out.println("DEBUG: FREE rooms: " + freeRooms);
		System.out.println("To date" + toDate + " LocalToDate: " + transformedToDate);
		
		if (freeRooms != null) {
			selected = freeRooms.get(freeRooms.size() - 1);
		}
		else {
			System.out.println("DEBUG: no Free Rooms");
		}
		return "teste";
	}
	
	

}
