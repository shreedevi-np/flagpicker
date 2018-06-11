/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.services.impl;

import com.apple.flagpicker.db.dao.ContinentDao;
import com.apple.flagpicker.db.models.Continent;
import com.apple.flagpicker.db.models.Country;
import com.apple.flagpicker.exceptions.InvalidParameterException;
import com.apple.flagpicker.exceptions.NoMatchingRecordsFoundException;
import com.apple.flagpicker.services.ContinentService;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shree
 */
@Service
@Transactional
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    private ContinentDao continentDao;
    
    @Override
    public Continent getContinentByName(String name) {
        return continentDao.getContinentByName(name);
    }

    @Override
    public Set<Country> getCountriesOfContinent(String name) {
        if(name == null || name.isEmpty()) {
            throw new InvalidParameterException("Continent name is either missing or null");
        }
        final Continent continent = continentDao.getContinentByName(name);
        if(continent == null) {
            throw new NoMatchingRecordsFoundException("No matching continents found for the name: \"" + name +"\"");
        }
        
        return continent.getCountries();
    }

    @Override
    public List<Country> inflateCountries(String continentName, List<Country> countries) {
        final Continent continent = continentDao.getContinentByName(continentName);
        if(continent != null) {
            countries.forEach(country -> {
                country.setDateCreated(new Date());
                country.setDateUpdated(new Date());
                country.setContinent(continent);
            });
            continent.setCountries(new HashSet<>(countries));
            continentDao.update(continent);
            return countries;
        }               
        return null;
    }
    
}
