/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.dao;

import com.apple.flagpicker.controller.FlagPickerControllerTest;
import com.apple.flagpicker.db.dao.ContinentDao;
import com.apple.flagpicker.db.models.Continent;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MReddy
 */
public class ContinentDaoTets extends FlagPickerControllerTest {
    
    @Autowired
    ContinentDao continentDao;
    
    @Test
    public void getContinentByNameTest() {
        Continent continent = continentDao.getContinentByName("Europe");
        Assert.assertEquals("Europe", continent.getName());        
    }
}
