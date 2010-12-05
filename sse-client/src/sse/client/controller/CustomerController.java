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
    private String name;
    private String surname;
	private String billingAdress;
    private String zip;
    private String city;
	private String company;
	private String note;
	private Double discount;
	private String phone;
	private String eMail;
	private String web;
	private String fax;

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
		System.out.println("DEBUG SELECTED CUSTOMER "+selected);
		this.name = selected.getName();
		this.surname = selected.getSurname();
		this.billingAdress = selected.getBillingAdress();
		this.zip = selected.getZip();
		this.city = selected.getCity();
		this.company = selected.getCompany();
		this.note = selected.getNote();
		this.discount = selected.getDiscount();
		this.phone = selected.getPhone();
		this.eMail = selected.getPhone();
		this.web = selected.getWeb();
		this.fax = selected.getFax();
	}

	public void create() {
		this.name = "";
		this.surname = "";
		this.billingAdress = "";
		this.zip = "";
		this.city = "";
		this.company = "";
		this.note = "";
		this.discount = 0d;
		this.phone = "";
		this.eMail = "";
		this.web = "";
		this.fax = "";
	}

	public void save() {
		Customer c = new Customer();
		c.setName(name);
		c.setSurname(surname);
		c.setBillingAdress(billingAdress);
		c.setZip(zip);
		c.setCity(city);
		c.setCompany(company);
		c.setNote(note);
		c.setDiscount(discount);
		c.setPhone(phone);
		c.seteMail(eMail);
		c.setWeb(web);
		c.setFax(fax);
		dao.save(c);
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public CustomerDAO getDao() {
		return dao;
	}

	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBillingAdress() {
		return billingAdress;
	}

	public void setBillingAdress(String billingAdress) {
		this.billingAdress = billingAdress;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	
}
