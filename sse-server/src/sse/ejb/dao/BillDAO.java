package sse.ejb.dao;

import javax.ejb.Stateless;

import sse.ejb.dao.common.BasicEntityDAO;
import sse.model.Bill;

@Stateless(name="BillDAO")
public class BillDAO extends BasicEntityDAO<Bill, Long> {

}