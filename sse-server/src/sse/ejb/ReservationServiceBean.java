/**
 * 
 */
package sse.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.LocalDate;

import sse.ejb.dao.CustomerDAO;
import sse.ejb.dao.ReservationDAO;
import sse.model.Customer;
import sse.model.Reservation;
import sse.model.Room;

/**
 * @author tobihammerer
 *
 */
@Stateless(name="ReservationService")
public class ReservationServiceBean implements ReservationService{
	
	@PersistenceContext
	private EntityManager em;
	
//	@EJB
//	DAOFactory dao;
	
	@EJB
	CustomerDAO customerDAO;
	
	@EJB
	ReservationDAO reservationDAO;
	
	@Override
	public List<Room> getFreeRoomsInTimespan(LocalDate fromDate, LocalDate toDate) {

//		EntityDAO<Reservation, Long> reservationDAO = dao.getDAO(Reservation.class);
		//		EntityDAO<Room, Long> roomDAO = new EntityDAO<Room, Long>();
		
		//TODO set params
//		List<Room> freeRooms = (List<Room>) roomDAO.findByNamedQuery("freeRoomsInTimespan", null);
				
		Query q = em.createNamedQuery("freeRoomsInTimespan").setParameter("fromDate", fromDate).setParameter("toDate",toDate);
//		Query q1 = em.createNamedQuery("allRooms");
		List<Room> freeRooms = (List<Room>)q.getResultList();
		
		System.out.println("ReservationServiceBean: " + freeRooms);
		
		return freeRooms;
		
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDAO.findAll();
	}

	@Override
	public List<Reservation> getReservationsForCustomer(Customer customer) {
		//TODO Lazy initialisation ?
		Customer myCust = em.find(Customer.class, customer.getId());
		return myCust.getReservations();
	}

	@Override
	public void doReservation(Reservation reservation) {
		reservationDAO.save(reservation);
		
	}
	
	
	
	

}
