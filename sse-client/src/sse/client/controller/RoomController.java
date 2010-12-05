package sse.client.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sse.ejb.dao.RoomDAO;
import sse.model.Room;

@ManagedBean(name="roomCtrl")
@SessionScoped
public class RoomController {
	
	@EJB
	private RoomDAO dao;
	
	public RoomController() {
		rooms = new ArrayList<Room>();

		for (int i = 0; i < 3; i++) {
			Room r = new Room();

			r.setOccupancy(3);
			r.setRateSingle(new BigDecimal(50+i));
			r.setRateDouble(new BigDecimal(48+i));
			r.setRateSingleOneChild(new BigDecimal(55+i));
			r.setRateSingleTwoChildren(new BigDecimal(65+i));
			r.setRateDoubleOneChild(new BigDecimal(58+i));
			rooms.add(r);
		}

		selected = rooms.get(rooms.size() - 1);
	}

	private List<Room> rooms;
	private Room selected;

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room getSelected() {
		return selected;
	}

	public void setSelected(Room selected) {
		this.selected = selected;
	}

	public void create() {
		this.selected = null;
	}

	public void save() {
		dao.save(selected);
	}

	public void delete() {
		dao.delete(selected);
	}

}
