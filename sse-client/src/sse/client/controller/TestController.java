package sse.client.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sse.ejb.dao.BillDAO;
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
	
	
	@EJB
	BillDAO billDAO;
	
	@PersistenceContext
	EntityManager em;
	
	
	public void testDateFunction(){
	
//		Reservation reservation = em.find(Reservation.class, 1L);
		
//		Reservation reservation = reservationDAO.findById(21L);
//		
//		System.out.println(reservation.getId());
//		
//		Date resDate = reservation.getToDate();
//		
//		System.out.println("Reservation 1 => from: " + reservation.getToDate() + " Class: " + reservation.getToDate().getClass().getName());
//		System.out.println("Reservation 1 => from: " + resDate + " Class: " + resDate.getClass().getName());
//		System.out.println("TestDate as Date: " + testDate + " Class: " + testDate.getClass().getName());
//		
//		Date now = new Date();
//		
//		boolean tester = false;
//		System.out.println("testDate before reservationToDate 21");
//		if(testDate.before(resDate)); 
//			tester = true;
//		
//		System.out.println(tester);
		
		
				
		
		List<Room> result = roomDAO.findByNamedQuery("test", testDate, testDate);
		System.out.println(result);
		
		for (Room room : result) {
			System.out.println("ID: " + room.getId() + "Nr: " + room.getRoomNumber());
		}
		
			
		
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	
	

}
