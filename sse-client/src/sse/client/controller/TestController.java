package sse.client.controller;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sse.ejb.dao.ReservationDAO;
import sse.ejb.dao.RoomDAO;
import sse.model.Reservation;
import sse.model.Room;

@ManagedBean(name = "testCtrl")
@SessionScoped
public class TestController {
	
	private Date testDate;
		
	@EJB
	ReservationDAO reservationDAO;
	
	@EJB
	RoomDAO roomDAO;
	
	
	
	public void testDateFunction(){
	
//		Reservation reservation = em.find(Reservation.class, 1L);
		
		Room r = roomDAO.findById(1L);
		
		Reservation r1 = reservationDAO.findById(15L);
		
		System.out.println("test " + r.getRoomNumber());
		System.out.println("test " + r1.getRoomRate());
		
//		System.out.println("Reservation 1 => from: " + reservation.getToDate() + " Class: " + reservation.getToDate().getClass().getName());
//		System.out.println("TestDate as Date: " + testDate + " Class: " + testDate.getClass().getName());
//		
//		LocalDate transformedDate = new LocalDate(testDate);
//		System.out.println("TestDate as LocalDate: " + transformedDate + " Class: " + transformedDate.getClass().getName());
		
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	
	

}
