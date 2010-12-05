package sse.ejb.dao;

import javax.ejb.Stateless;

import sse.ejb.dao.common.BasicEntityDAO;
import sse.model.Reservation;

@Stateless(name="ReservationDAO")
public class ReservationDAO extends BasicEntityDAO<Reservation, Long> {

}
