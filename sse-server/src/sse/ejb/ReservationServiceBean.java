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

import sse.dao.EntityDAO;
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
	
	@Override
	public List<Room> getFreeRoomsInTimespan(LocalDate fromDate, LocalDate toDate) {

//		EntityDAO<Reservation, Long> reservationDAO = dao.getDAO(Reservation.class);
		//		EntityDAO<Room, Long> roomDAO = new EntityDAO<Room, Long>();
		
		//TODO set params
//		List<Room> freeRooms = (List<Room>) roomDAO.findByNamedQuery("freeRoomsInTimespan", null);
				
		Query q = em.createNamedQuery("freeRoomsInTimespan").setParameter("fromDate", fromDate).setParameter("toDate",toDate);
		Query q1 = em.createNamedQuery("freeRoomsInTimespan");
		List<Room> freeRooms = (List<Room>)q.getResultList();
		
		System.out.println(freeRooms);
		
		return freeRooms;
		
	}

}
