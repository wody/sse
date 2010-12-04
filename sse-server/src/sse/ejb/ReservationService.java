package sse.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.joda.time.LocalDate;

import sse.model.Room;

@Remote
public interface ReservationService {
	public List<Room> getFreeRoomsInTimespan(LocalDate fromDate, LocalDate toDate);
}
