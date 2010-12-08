package sse.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sse.ejb.dao.CustomerDAO;
import sse.model.Customer;
import sse.model.Room;

@ManagedBean(name="customerCtrl")
@SessionScoped
public class CustomerController {
	
	@EJB
	private CustomerDAO dao;
	private String filterTxt;

	public CustomerController() {
		customers = new ArrayList<Customer>();
	}

	private List<Customer> customers;
	private Customer selectedCustomer;

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

	public void create() {
		this.selectedCustomer = new Customer();
	}

	public void save() {
		dao.save(selectedCustomer);
	}

	public void delete() {
		dao.delete(selectedCustomer);
		this.selectedCustomer = new Customer();
	}
	
	public List<Customer> filter() {
		if (!filterTxt.equals("")) {
			return customers = dao.findByNamedQuery("filterCustomers", filterTxt);
		} else {
			return customers = dao.findAll();
		}
	}
	
	public String load() {
		
		customers = dao.findAll();
		
		return "customer.jsf";
	}

	public void setFilterTxt(String filterTxt) {
		this.filterTxt = filterTxt;
	}

	public String getFilterTxt() {
		return filterTxt;
	}

}
