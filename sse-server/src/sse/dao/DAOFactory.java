package sse.dao;

import javax.ejb.Local;

@Local
public interface DAOFactory {

	<T> EntityDAO<T, Long> getDAO(Class<T> clazz);
	
}
