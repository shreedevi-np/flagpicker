/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.services.impl;

import com.apple.flagpicker.db.dao.CountryDao;
import com.apple.flagpicker.db.models.Country;
import com.apple.flagpicker.exceptions.InvalidParameterException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apple.flagpicker.services.CountryService;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Shree
 */
@Service
@Transactional
public class CountriyServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Override
    public Country getCountryByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidParameterException("Country name is missing or null");
        }
        return countryDao.getCountryByName(name);
    }

    @Override
    public List<Country> getFlagsOfCountries(String names) {
        if (names.trim().isEmpty()) {
            throw new InvalidParameterException("Country name must not be empty, request more than one country flag by passing comma separated country names");
        }
        final Set<String> countriesNames = Arrays.stream(names.split(","))
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toSet());
        final List<Country> countries = countryDao.getFlagsOfCounrties(countriesNames);
        return countries;
    }
}
