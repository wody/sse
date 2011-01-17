package sse.client.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sse.ejb.dao.CustomerDAO;
import sse.model.Customer;

@ManagedBean(name = "invoiceCtrl")
@SessionScoped
public class InvoiceController {

	private Date departureDate;
	private List<Customer> customers;
	private Customer selectedCustomer;
	private String filterCustTxt;

	@EJB
	private CustomerDAO customerDao;

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
		
		// TODO move on to choose the reservations which should be processed :)
		return "invoiceCustomer.xhtml";
	}

}
