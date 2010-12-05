package sse.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import sse.utils.ReflectionUtils;

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
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public DefaultEntityDAO() {
		this.persistentClass = (Class<T>) ReflectionUtils.getTypeArguments(DefaultEntityDAO.class, getClass()).get(0);
//		System.out.println("DEBUG persistentCLass "+this.persistentClass.getName());
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
		em.remove(entity);
	}

	/**
	 * @see src.sse.dao.IEntityDAO#findAll()
	 */
	@Override
	public List<T> findAll() {
		return em.createQuery(em.getCriteriaBuilder().createQuery(persistentClass)).getResultList();
	}

	/**
	 * @see src.sse.dao.IEntityDAO#findById(java.io.Serializable)
	 */
	@Override
	public T findById(ID id) {
		return em.find(persistentClass, id);
	}

	/**
	 * @see src.sse.dao.IEntityDAO#findByNamedQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<T> findByNamedQuery(String name, Object... params) {
		TypedQuery<T> query = em.createNamedQuery(name, persistentClass);

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
		if (em == null) {
			System.out.println("DEBUG em is NULL");
		}
		T savedEntity = em.merge(entity);
		return savedEntity;
	}

}