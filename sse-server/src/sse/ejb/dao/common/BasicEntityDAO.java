package sse.ejb.dao.common;

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
public abstract class BasicEntityDAO<T, ID extends Serializable> implements EntityDAO<T, ID> {

	@PersistenceContext
	private EntityManager em;
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BasicEntityDAO() {
		this.persistentClass = (Class<T>) ReflectionUtils.getTypeArguments(BasicEntityDAO.class, getClass()).get(0);
	}
	
	/**
	 * @see src.sse.dao.IEntityDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(T entity) {
		T ent = em.merge(entity);
		em.remove(ent);
	}

	/**
	 * @see src.sse.dao.IEntityDAO#findAll()
	 */
	@Override
	public List<T> findAll() {
		return em.createQuery("SELECT e FROM " + persistentClass.getSimpleName() + " e", persistentClass).getResultList();
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
	 * @see src.sse.dao.IEntityDAO#save(java.lang.Object)
	 */
	@Override
	public T save(T entity) {
		T savedEntity = em.merge(entity);
		return savedEntity;
	}

}