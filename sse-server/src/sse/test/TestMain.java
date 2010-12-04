package sse.test;

import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import sse.ejb.ReservationService;
import sse.model.Room;

public class TestMain {

	private static InitialContext ctx;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Properties properties = new Properties();
			properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
			properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
			properties.put("java.naming.provider.url","localhost:1099");
			ctx = new InitialContext(properties);
			
			ReservationService reservation = (ReservationService) ctx.lookup(ReservationService.JNDI_NAME);
			DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
			
			LocalDate fromDate = new LocalDate(fmt.parseDateTime("03.12.2000"));
			
			LocalDate toDate = new LocalDate(fmt.parseDateTime("06.12.2011"));
			List<Room> rooms = reservation.getFreeRoomsInTimespan(fromDate, toDate);
			System.out.println(rooms);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
