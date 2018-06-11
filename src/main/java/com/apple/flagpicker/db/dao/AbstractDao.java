/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Shree
 * @param <T>
 */
public abstract class AbstractDao<T extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<T> clazz;

    public AbstractDao() {
        this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];;
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    protected Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(clazz);
    }

    public T getByName(final String name) {
        final Criteria query = createCriteria();
        query.add(Restrictions.eq("name", name));
        return (T) query.uniqueResult();
    }
}
