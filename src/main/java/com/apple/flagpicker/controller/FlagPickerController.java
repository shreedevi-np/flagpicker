/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.controller;

import com.apple.flagpicker.db.models.Country;
import com.apple.flagpicker.exceptions.InvalidParameterException;
import com.apple.flagpicker.services.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.apple.flagpicker.services.CountryService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Shree
 */
@RestController
@RequestMapping(path = "/api/v1")
public class FlagPickerController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ContinentService continentService;

    @GetMapping(path = "/continents/{name}")
    public ResponseEntity<ResponseRecord<Set<Country>>> countries(@PathVariable("name") String name) {
        try {
            final Set<Country> countries = continentService.getCountriesOfContinent(name);
            final ResponseRecord<Set<Country>> resRecord = new ResponseRecord<>("", "success", countries);
            return ResponseEntity.status(200).body(resRecord);
        } catch (InvalidParameterException ex) {
            final ResponseRecord<Set<Country>> resRecord = new ResponseRecord<>(ex.getMessage(), "error", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resRecord);
        } catch (Exception ex) {
            final ResponseRecord<Set<Country>> resRecord = new ResponseRecord<>(ex.getMessage(), "error", new HashSet<>());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resRecord);
        }
    }

    @GetMapping(path = "/flags/{countries}")
    public ResponseEntity<ResponseRecord<List<Country>>> flagOfCountry(@PathVariable("countries") String countries) {

        try {
            final List<Country> country = countryService.getFlagsOfCountries(countries);
            final ResponseRecord<List<Country>> resRecord = new ResponseRecord<>("", "success", country);
            return ResponseEntity.status(200).body(resRecord);
        } catch (InvalidParameterException ex) {
            final ResponseRecord<List<Country>> resRecord = new ResponseRecord<>(ex.getMessage(), "error", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resRecord);
        } catch (Exception ex) {
            final ResponseRecord<List<Country>> resRecord = new ResponseRecord<>(ex.getMessage(), "error", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resRecord);
        }
    }

    @PostMapping(path = "/countries/{continent}")
    public List<Country> loadCountriesOfContinent(@RequestBody List<Country> countries, @PathVariable("continent") String continent) {
        return continentService.inflateCountries(continent, countries);
    }    
}
