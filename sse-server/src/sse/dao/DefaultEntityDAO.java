package sse.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 * JPA implementation of the EntityDAO.
 * 
 * @author Andrea Fueresz
 * @author Stefan Vallaster
 * 
 * @param <T>
 *            The persistent type
 * @param <ID>
 *            The primary key type
 */
public class DefaultEntityDAO<T, ID extends Serializable> implements EntityDAO<T, ID> {
	
	private Class<T> persistentClass;
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public DefaultEntityDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public DefaultEntityDAO(Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	/**
	 * @see src.sse.dao.IEntityDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	/**
	 * @see src.sse.dao.IEntityDAO#findAll()
	 */
	@Override
	public List<T> findAll() {
		return getEntityManager().createQuery(getEntityManager().getCriteriaBuilder().createQuery(persistentClass)).getResultList();
	}

	/**
	 * @see src.sse.dao.IEntityDAO#findById(java.io.Serializable)
	 */
	@Override
	public T findById(ID id) {
		return getEntityManager().find(persistentClass, id);
	}

	/**
	 * @see src.sse.dao.IEntityDAO#findByNamedQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<T> findByNamedQuery(String name, Object... params) {
		TypedQuery<T> query = getEntityManager().createNamedQuery(name, persistentClass);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		return query.getResultList();
	}

	/**
	 * @see src.sse.dao.IEntityDAO#getEntityClass()
	 */
	@Override
	public Class<T> getEntityClass() {
		return persistentClass;
	}

	/**
	 * @see src.sse.dao.IEntityDAO#save(java.lang.Object)
	 */
	@Override
	public T save(T entity) {
		T savedEntity = getEntityManager().merge(entity);
		return savedEntity;
	}
	
	/**
	 * set the JPA entity manager to use.
	 *
	 * @param entityManager
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
}