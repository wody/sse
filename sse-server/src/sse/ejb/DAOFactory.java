package sse.ejb;

import javax.ejb.Stateless;

import sse.dao.DefaultEntityDAO;
import sse.dao.EntityDAO;

@Stateless(name="DAOFactory")
public class DAOFactory {

	public <T> EntityDAO<T, Long> getDAO(Class<T> clazz) {
		return new DefaultEntityDAO<T, Long>();
	}
	
}
