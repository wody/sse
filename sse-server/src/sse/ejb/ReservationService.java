package sse.ejb;

import sse.model.Customer;
import sse.model.Reservation;
import sse.model.Room;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface ReservationService {
	
	public static final String JNDI_NAME = "sse/ReservationService/remote";
	
	List<Room> getFreeRoomsInTimespan(Date fromDate, Date toDate);
	
	List<Customer> getAllCustomers();
	
	List<Reservation> getReservationsForCustomer(Customer customer);
	
	void doReservation(Reservation reservation);

    List<Reservation> getReservationsByArrival(Date arrival);

    List<Reservation> getReservationsByDeparture(Date departure);
}
