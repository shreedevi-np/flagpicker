/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.services;

import com.apple.flagpicker.db.models.Continent;
import com.apple.flagpicker.db.models.Country;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Shree
 */
public interface ContinentService {

    public Continent getContinentByName(String name);

    public Set<Country> getCountriesOfContinent(String name);
    
    public List<Country> inflateCountries(String continent, List<Country> countries);
}
