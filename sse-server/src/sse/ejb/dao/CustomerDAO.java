package sse.ejb.dao;

import javax.ejb.Stateless;

import sse.ejb.dao.common.BasicEntityDAO;
import sse.model.Customer;

@Stateless(name="CustomerDAO")
public class CustomerDAO extends BasicEntityDAO<Customer, Long> {

}