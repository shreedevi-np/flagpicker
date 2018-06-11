/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.services;

import com.apple.flagpicker.FlagpickerApplicationTest;
import com.apple.flagpicker.db.models.Country;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MReddy
 */
public class CountryServiceTest extends FlagpickerApplicationTest {
    @Autowired
    CountryService countryService;
    
    @Test
    public void getCountryByNameTest() {
        Country country = countryService.getCountryByName("India");
        Assert.assertEquals("India", country.getName());        
    }
    
    @Test
    public void getFlagsOfCountriesTest() {
        List<Country> countries = countryService.getFlagsOfCountries("India");
        List<Country> moreCountries = countryService.getFlagsOfCountries("India,Indonesia,Argentina");
        List<Country> noCountries = countryService.getFlagsOfCountries("no country");
        Assert.assertEquals(countries.size(), 1);
        Assert.assertEquals(moreCountries.size(), 3);
        Assert.assertTrue(noCountries.isEmpty());
    }
}

