/**
 * 
 */
package sse.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.LocalDate;

import sse.dao.hibernate.EntityDAO;
import sse.model.Room;

/**
 * @author tobihammerer
 *
 */
@Stateless
public class ReservationServiceBean implements ReservationService{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Room> getFreeRoomsInTimespan(LocalDate fromDate, LocalDate toDate) {
//		EntityDAO<Room, Long> roomDAO = new EntityDAO<Room, Long>();
		
		//TODO set params
//		List<Room> freeRooms = (List<Room>) roomDAO.findByNamedQuery("freeRoomsInTimespan", null);
				
		Query q = em.createNamedQuery("freeRoomsInTimespan").setParameter("fromDate", fromDate).setParameter("toDate",toDate);
		List<Room> freeRooms = q.getResultList();
		
		return freeRooms;
		
	}

}
