package sse.client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sse.ejb.ReservationService;
import sse.ejb.dao.CustomerDAO;
import sse.model.Customer;
import sse.model.Reservation;

@ManagedBean(name = "invoiceCtrl")
@SessionScoped
public class InvoiceController {

	private Date departureDate;
	private List<Customer> customers;
	private Customer selectedCustomer;
	private String filterCustTxt;
	private List<Reservation> reservations;
	private List<Reservation> selectedReservations;
	private Reservation stornoReservation;
	private Boolean showReservations = false;

	@EJB
	private CustomerDAO customerDao;
	@EJB
	ReservationService reservationService;

	public String load() {
		reservations = new ArrayList<Reservation>();
		selectedReservations = new ArrayList<Reservation>();
		departureDate = new Date();
		customers = customerDao.findAll();
		filterCustTxt = "";
		return "invoiceCustomer.xhtml";
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public String getFilterCustTxt() {
		return filterCustTxt;
	}

	public void setFilterCustTxt(String filterCustTxt) {
		this.filterCustTxt = filterCustTxt;
	}

	public List<Customer> filterCust() {
		if (!filterCustTxt.equals("")) {
			customers = customerDao.findByNamedQuery("filterCustomers",
					filterCustTxt);
		} else {
			customers = customerDao.findAll();
		}

		return customers;
	}
	
	public String loadReservations() {
		
		reservations.clear();
		
		for (Customer c : this.customers) {
			if (c.getSelected()) {
				reservations.addAll(reservationService.getReservationsForCustomer(c));
				this.showReservations = true;
			}
		}
		
		return "invoiceCustomer.xhtml";
		
	}
	
	public String doStorno() {
		
		selectedReservations.clear();
		selectedReservations = getSelectedReservations();
		
		return "invoice.xhtml";
		
	}
	
	public String doInvoice() {
		
		selectedReservations.clear();
		selectedReservations = getSelectedReservations();
		
		return "invoice.xhtml";
	}
	
	private List<Reservation> getSelectedReservations() {
		
		List<Reservation> temp = new ArrayList<Reservation>();
		for(Reservation r : reservations) {
			if(r.getSelected()) {
				temp.add(r);
			}
		}
		
		return temp;
	}

	public void setStornoReservation(Reservation stornoReservation) {
		this.stornoReservation = stornoReservation;
	}

	public Reservation getStornoReservation() {
		return stornoReservation;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Boolean getShowReservations() {
		return showReservations;
	}

	public void setShowReservations(Boolean showReservations) {
		this.showReservations = showReservations;
	}

	public void setSelectedReservations(List<Reservation> selectedReservations) {
		this.selectedReservations = selectedReservations;
	}



}
