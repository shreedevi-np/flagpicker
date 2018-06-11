/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.controller;

import com.apple.flagpicker.FlagpickerApplicationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Shree
 */
public class FlagPickerControllerTest extends FlagpickerApplicationTest {

    @Value("${fp.security.token}")
    String token;

    @Test
    public void testCountriesOfContinent() throws Exception {
        String res = this.controller.perform(get(API_PREFIX + "/continents/America").header("auth-token", token))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        Assert.assertTrue(res.contains("\"status\":\"success\""));
    }
    
    @Test
    public void testflagOfCountry() throws Exception {
        String res = this.controller.perform(get(API_PREFIX + "/flags/Egypt").header("auth-token", token))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        Assert.assertTrue(res.contains("\"status\":\"success\"") && res.contains("Egypt"));
    }
    
    @Test
    public void testflagOfUnknowCountry() throws Exception {
        String res = this.controller.perform(get(API_PREFIX + "/flags/ ").header("auth-token", token))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();

        Assert.assertTrue(res.contains("\"status\":\"error\"") && res.contains("Country name must not be empty, request more than one country flag by passing comma separated country names"));
    }
}
