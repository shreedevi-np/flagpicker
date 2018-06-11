/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.db.dao;

import com.apple.flagpicker.db.models.Country;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Shree
 */
public interface CountryDao {

    public Country getCountryByName(String name);

    public List<Country> getFlagsOfCounrties(Set<String> names);
}
