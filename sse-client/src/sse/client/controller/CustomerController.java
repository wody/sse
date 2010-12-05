package sse.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sse.dao.EntityDAO;
import sse.ejb.DAOFactory;
import sse.model.Customer;

@ManagedBean(name="customerCtrl")
@SessionScoped
public class CustomerController {
	
	@EJB
	private DAOFactory dao;
	private EntityDAO<Customer, Long> customerDAO;

	public CustomerController() {

		customers = new ArrayList<Customer>();

		for (int i = 0; i < 10; i++) {
			Customer c = new Customer();

			c.setBillingAdress("Address " + i);
			c.setCity("City" + i);
			c.setCompany("com");
			c.setDiscount(0.5);
			c.seteMail("mail");
			c.setFax("fax");
			c.setName("Customer Name" + i);
			c.setNote("note");
			c.setPhone("phone");
			c.setSelected(false);
			c.setSurname("SURNAME" + i);
			c.setWeb("web");
			c.setZip("COG1");

			customers.add(c);
		}

		selected = customers.get(customers.size() - 1);

	}

	private List<Customer> customers;
	private Customer selected;
	private Boolean delete;

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer getSelected() {
		return selected;
	}

	public void setSelected(Customer selected) {
		this.selected = selected;
	}

	public void create() {

	}

	public void save() {
		customerDAO = (EntityDAO<Customer,Long>) dao.getDAO(Customer.class);
		Customer c = new Customer();
		c.setName("Hallo");
		c.setSurname("Ich");
		c.setBillingAdress("heisse");
		c.setCity("TESTPERSON");
		if (customerDAO == null) {
			System.out.println("DEBUG customerDAO is NULL");
		}
		customerDAO.save(c);
	}

	public void delete() {
		this.setDelete(Boolean.TRUE);
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Boolean getDelete() {
		return delete;
	}

}
