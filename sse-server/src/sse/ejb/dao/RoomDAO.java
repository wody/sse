package sse.ejb.dao;

import javax.ejb.Stateless;

import sse.ejb.dao.common.BasicEntityDAO;
import sse.model.Room;

@Stateless(name="RoomDAO")
public class RoomDAO extends BasicEntityDAO<Room, Long> {

}