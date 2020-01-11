package org.hebrbot.bot.dao;

import org.hebrbot.bot.model.HebrewVerb;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Repository("hebrewVerbDao")
@Transactional
public class HebrewVerbDaoImpl implements HebrewVerbDao {

	@Autowired
	protected SessionFactory sessionFactory;

	protected Session getSession() {
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public HebrewVerb getEntity(Session session, Long id, LockMode lock) {
		if (id == null) {
			return null;
		}
		return session.get(HebrewVerb.class, id, new LockOptions(lock));
	}

	public HebrewVerb getEntity(final Long id, final LockMode lock) {
		if (id == null) {
			return null;
		}
		HebrewVerb entity = getEntity(getSession(), id, lock);
		Hibernate.initialize(entity);
		return entity;
	}

	public HebrewVerb getEntity(final Long id) {
		return getEntity(id, LockMode.READ);
	}

	//TODO:NRE
	public HebrewVerb getByName(final String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		Criteria crit = getSession().createCriteria(HebrewVerb.class);
		crit.add(Restrictions.eq("name",name));
//		List<HebrewVerb> results = crit.list();
		HebrewVerb entity = Optional.of((HebrewVerb)crit.uniqueResult()).orElse(null);
		Hibernate.initialize(entity);
		return entity;
	}

	public HebrewVerb getEntityDetached(Long id) {
		return getEntityDetached(id, LockMode.NONE);
	}

	public HebrewVerb getEntityDetached(Long id, LockMode lock) {
		return evictEntity(getEntity(id, lock));
	}

	public HebrewVerb evictEntity(HebrewVerb entity) {
		Session session = getSession();
		session.flush();
		if (entity != null) {
			session.evict(entity);
		}
		return entity;
	}

	public HebrewVerb getHebrewVerbById(Long id) {
		//TODO: bug
		Session session = null;
		HebrewVerb hebrewVerb = null;
		try {
			session = getSession();
			hebrewVerb =  session.get(HebrewVerb.class,
					id);
			Hibernate.initialize(hebrewVerb);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return hebrewVerb;
	}

}
