/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Shree
 */
@Entity
@Table(name = "CONTINENT")
public class Continent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Column(name = "datecreated")
    private Date dateCreated;
    @Column(name = "dateupdated")
    private Date dateUpdated;
    
    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Country> countries;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the countries
     */
    public Set<Country> getCountries() {
        return countries == null? new HashSet<>() : countries;
    }

    /**
     * @param countries the countries to set
     */
    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }    

    /**
     * @return the dateUpdated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @param dateUpdated the dateUpdated to set
     */
    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
    
    
    @Override
    @JsonIgnore
    public boolean equals(Object obj) {
        final Continent other = (Continent) obj;
        if(other == null || other.getId() != this.getId()) {
            return false;
        }
        return other.getName().equalsIgnoreCase(this.name) 
                && other.getCountries().size() == this.getCountries().size();
    }    

    @Override
    @JsonIgnore
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
}
