/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.services;

import com.apple.flagpicker.FlagpickerApplicationTest;
import com.apple.flagpicker.db.models.Continent;
import com.apple.flagpicker.db.models.Country;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MReddy
 */
public class ContinentServiceTest extends FlagpickerApplicationTest {
    @Autowired
    ContinentService continentService;
    
    @Test
    public void getContinentByNameTest() {
        Continent continent = continentService.getContinentByName("Asia");
        Assert.assertEquals("Asia", continent.getName());
        Assert.assertEquals(continent.getCountries().size(), 5);
    }
    
    @Test
    public void getCountriesOfContinentTest() {
        Set<Country> countries = continentService.getCountriesOfContinent("Asia");
        Assert.assertEquals(countries.size(), 5);
    }
}
