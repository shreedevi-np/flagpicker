/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.db.dao.impl;

import com.apple.flagpicker.db.dao.AbstractDao;
import com.apple.flagpicker.db.dao.ContinentDao;
import com.apple.flagpicker.db.models.Continent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shree
 */
@Repository("continentDao")
@Transactional
public class ContinentDaoImpl extends AbstractDao<Continent> implements ContinentDao {

    @Override
    public Continent getContinentByName(String continentName) {
        return this.getByName(continentName);
    }

    @Override
    public void update(Continent continent) {
        getSession().saveOrUpdate(continent);
    }
  
}
