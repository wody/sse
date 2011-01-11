/**
 * 
 */
package sse.ejb;

import org.apache.log4j.Logger;
import org.joda.time.DateMidnight;
import org.joda.time.LocalDate;
import sse.ejb.dao.CustomerDAO;
import sse.ejb.dao.ReservationDAO;
import sse.model.Customer;
import sse.model.Reservation;
import sse.model.Room;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * @author tobihammerer
 *
 */
@Stateless(name="ReservationService")
public class ReservationServiceBean implements ReservationService{

    private static final Logger log = Logger.getLogger(ReservationServiceBean.class);

	@PersistenceContext
	private EntityManager em;
	
//	@EJB
//	DAOFactory dao;
	
	@EJB
	CustomerDAO customerDAO;
	
	@EJB
	ReservationDAO reservationDAO;
	
	@Override
	public List<Room> getFreeRoomsInTimespan(Date fromDate, Date toDate) {

//		EntityDAO<Reservation, Long> reservationDAO = dao.getDAO(Reservation.class);
		//		EntityDAO<Room, Long> roomDAO = new EntityDAO<Room, Long>();
		
		//TODO set params
//		List<Room> freeRooms = (List<Room>) roomDAO.findByNamedQuery("freeRoomsInTimespan", null);
				
		Query q = em.createNamedQuery("freeRoomsInTimespan").setParameter("fromDate", fromDate).setParameter("toDate",toDate);
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
		Customer myCust = em.merge(customer);
		return myCust.getReservations();
	}

	@Override
	public void doReservation(Reservation reservation) {
		reservationDAO.save(reservation);
		
	}

    @Override
    public List<Reservation> getReservationsByArrival(Date arrival) {
        LocalDate larrival = new LocalDate(arrival);
        DateMidnight dateMidnight = larrival.toDateMidnight();

        List<Reservation> reservations = em.createNamedQuery("arrivals", Reservation.class)
                .setParameter("arrival", dateMidnight.toDate())
                .getResultList();

        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByDeparture(Date departure) {
        LocalDate ldeparture = new LocalDate(departure);
        DateMidnight dateMidnight = ldeparture.toDateMidnight();

        List<Reservation> reservations = em.createNamedQuery("departures", Reservation.class)
                .setParameter("departure", dateMidnight.toDate())
                .getResultList();

        return reservations;
    }


}
