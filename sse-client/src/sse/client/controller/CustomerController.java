package sse.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sse.ejb.dao.CustomerDAO;
import sse.model.Customer;

@ManagedBean(name="customerCtrl")
@SessionScoped
public class CustomerController {
	
	@EJB
	private CustomerDAO dao;

	public CustomerController() {

		customers = new ArrayList<Customer>();

//		for (int i = 0; i < 10; i++) {
//			Customer c = new Customer();
//
//			c.setBillingAdress("Address " + i);
//			c.setCity("City" + i);
//			c.setCompany("com");
//			c.setDiscount(0.5);
//			c.seteMail("mail");
//			c.setFax("fax");
//			c.setName("Customer Name" + i);
//			c.setNote("note");
//			c.setPhone("phone");
//			c.setSelected(false);
//			c.setSurname("SURNAME" + i);
//			c.setWeb("web");
//			c.setZip("COG1");
//
//			customers.add(c);
//		}
//
//		selected = customers.get(customers.size() - 1);

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
	
	public String load() {
		
		customers = dao.findAll();
		
		return "customer.jsf";
	}

}
