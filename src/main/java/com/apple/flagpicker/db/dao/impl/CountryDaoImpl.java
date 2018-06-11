/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.db.dao.impl;

import com.apple.flagpicker.db.dao.AbstractDao;
import com.apple.flagpicker.db.dao.CountryDao;
import com.apple.flagpicker.db.models.Country;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shree
 */
@Repository("countryDao")
@Transactional
public class CountryDaoImpl extends AbstractDao<Country> implements CountryDao {

    @Override
    public Country getCountryByName(String name) {
        return this.getByName(name);
    }

    @Override
    public List<Country> getFlagsOfCounrties(Set<String> names) {
        final Criteria query = createCriteria();
        query.add(Restrictions.in("name", names))
                .setProjection(Projections.distinct(Projections.property("name")));

        return query.list();
    }
}
