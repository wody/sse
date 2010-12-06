/**
 * 
 */
package sse.ejb;

import java.util.ArrayList;
import java.util.List;

//import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import org.joda.time.LocalDate;

//import sse.ejb.dao.CustomerDAO;
import sse.model.Customer;
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
	
//	@EJB
//	CustomerDAO customerDAO;
	
	@Override
	public List<Room> getFreeRoomsInTimespan(/*LocalDate fromDate, LocalDate toDate*/) {

//		EntityDAO<Reservation, Long> reservationDAO = dao.getDAO(Reservation.class);
		//		EntityDAO<Room, Long> roomDAO = new EntityDAO<Room, Long>();
		
		//TODO set params
//		List<Room> freeRooms = (List<Room>) roomDAO.findByNamedQuery("freeRoomsInTimespan", null);
				
//		Query q = em.createNamedQuery("freeRoomsInTimespan").setParameter("fromDate", fromDate).setParameter("toDate",toDate);
		Query q1 = em.createNamedQuery("allRooms");
		List<Room> freeRooms = (List<Room>)q1.getResultList();
		
		System.out.println(freeRooms);
		
		return freeRooms;
		
	}

	@Override
	public List<Customer> getAllCustomers() {		
		
		ArrayList<Customer> custs = new ArrayList<Customer>();
		Customer cust1 = new Customer();
		cust1.setCity("weit weg");
		cust1.setName("seppf");
		custs.add(cust1);
		Customer cust2 = new Customer();
		cust2.setCity("weit weit weg");
		cust2.setName("hannes");
		custs.add(cust2);
		return custs;
//		return customerDAO.findAll();
	}

}
