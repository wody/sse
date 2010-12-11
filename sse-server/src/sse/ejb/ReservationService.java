package sse.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import sse.model.Customer;
import sse.model.Reservation;
import sse.model.Room;

@Remote
public interface ReservationService {
	
	public static final String JNDI_NAME = "sse/ReservationService/remote";
	
	public List<Room> getFreeRoomsInTimespan(Date fromDate, Date toDate);
	
	public List<Customer> getAllCustomers();
	
	public List<Reservation> getReservationsForCustomer(Customer customer);
	
	public void doReservation(Reservation reservation);
}
