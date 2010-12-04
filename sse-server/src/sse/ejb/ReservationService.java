package sse.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.joda.time.LocalDate;

import sse.model.Room;

@Remote
public interface ReservationService {
	
	public static final String JNDI_NAME = "jndi:sse/ReservationService/remote";
	
	public List<Room> getFreeRoomsInTimespan(LocalDate fromDate, LocalDate toDate);
}
