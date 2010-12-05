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
	
	public RoomController() {
		rooms = new ArrayList<Room>();

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
		this.selected = new Room();
	}

	public void save() {
		dao.save(selected);
	}

	public void delete() {
		dao.delete(selected);
	}
	
	
	public String load() {
		
		rooms = dao.findAll();
		
		return "room.jsf";
	}

}
