package sse.client.controller;

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
	private String filterTxt;
	
	public RoomController() {
		rooms = new ArrayList<Room>();

	}

	private List<Room> rooms;
	private Room selectedRoom;

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room getSelectedRoom() {
		return selectedRoom;
	}

	public void setSelectedRoom(Room selectedRoom) {
		this.selectedRoom = selectedRoom;
	}

	public void create() {
		this.selectedRoom = new Room();
	}

	public void save() {
		dao.save(selectedRoom);
	}

	public void delete() {
		dao.delete(selectedRoom);
		this.selectedRoom = new Room();
	}
	
	public List<Room> filter() {
		if (!filterTxt.equals("")) {
			Integer txt = Integer.valueOf(filterTxt).intValue();
			return rooms = dao.findByNamedQuery("filterRooms", txt);
		} else {
			return rooms = dao.findAll();
		}
	}
	
	public String load() {
		
		rooms = dao.findAll();
		
		return "room.jsf";
	}

	public void setFilterTxt(String filterTxt) {
		this.filterTxt = filterTxt;
	}

	public String getFilterTxt() {
		return filterTxt;
	}

}
