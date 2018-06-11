/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.db.dao;

import com.apple.flagpicker.db.models.Continent;

/**
 *
 * @author Shree
 */
public interface ContinentDao {

    public Continent getContinentByName(String continentName);
    
    public void update(Continent continent);
}
