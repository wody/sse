package sse.client.controller;

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
	private List<Reservation> reservationsForSelectedCustomer;
	private Reservation stornoReservation;
	private Boolean reservationsForCustomer = false;
	private Boolean process = false;

	@EJB
	private CustomerDAO customerDao;
	@EJB
	ReservationService reservationService;

	public String load() {

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
		this.reservationsForSelectedCustomer = reservationService.getReservationsForCustomer(selectedCustomer);
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
	
	public String selectCustomerAndDeparture() {
		
		this.reservationsForCustomer = true;
		return "invoiceCustomer.xhtml";
		
	}

	public void setStornoReservation(Reservation stornoReservation) {
		this.stornoReservation = stornoReservation;
	}

	public Reservation getStornoReservation() {
		return stornoReservation;
	}

	public void setReservationsForSelectedCustomer(
			List<Reservation> reservationsForSelectedCustomer) {
		this.reservationsForSelectedCustomer = reservationsForSelectedCustomer;
	}

	public List<Reservation> getReservationsForSelectedCustomer() {
		return reservationsForSelectedCustomer;
	}

	public void setReservationsForCustomer(Boolean reservationsForCustomer) {
		this.reservationsForCustomer = reservationsForCustomer;
	}

	public Boolean getReservationsForCustomer() {
		return reservationsForCustomer;
	}

	public void setProcess(Boolean process) {
		this.process = process;
	}

	public Boolean getProcess() {
		return process;
	}

}
