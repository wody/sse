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
	private List<Reservation> reservationsForSelectedCustomer;
	private List<Reservation> billForselectedCustomers;
	private Reservation stornoReservation;
	private Boolean reservationsForCustomer = false;

	@EJB
	private CustomerDAO customerDao;
	@EJB
	ReservationService reservationService;

	public String load() {
		billForselectedCustomers = new ArrayList<Reservation>();
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
	
	public String selectCustomerAndDeparture() {
		
		this.reservationsForCustomer = true;
		billForselectedCustomers = new ArrayList<Reservation>();
		for (Customer c : this.customers) {
			if (c.getSelected()) {
				this.reservationsForSelectedCustomer = reservationService.getReservationsForCustomer(c);
				for (Reservation r : reservationsForSelectedCustomer) {
					billForselectedCustomers.add(r);
				}
			}
		}
		
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

	public void setBillForselectedCustomers(List<Reservation> billForselectedCustomers) {
		this.billForselectedCustomers = billForselectedCustomers;
	}

	public List<Reservation> getBillForselectedCustomers() {
		return billForselectedCustomers;
	}

}
