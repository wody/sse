package sse.dao;

import javax.ejb.Stateless;

@Stateless(name="DAOFactory")
public class DAOFactoryImpl {

	public <T> EntityDAO<T, Long> getDAO(Class<T> clazz) {
		return new DefaultEntityDAO<T, Long>();
	}
	
}
