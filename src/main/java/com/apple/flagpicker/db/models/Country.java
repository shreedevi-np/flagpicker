/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Shree
 */
@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String flag;
    @Column(name = "datecreated")
    private Date dateCreated;
    @Column(name = "dateupdated")
    private Date dateUpdated;

    @ManyToOne
    @JoinColumn(name = "continent", nullable = false)
    @JsonIgnore
    private Continent continent;

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
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return the continent
     */
    public Continent getContinent() {
        return continent;
    }

    /**
     * @param continent the continent to set
     */
    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
        final Country other = (Country) obj;
        if (other == null || other.getId() != this.id) {
            return false;
        }
        return other.getName().equalsIgnoreCase(this.name);
    }

    @Override
    @JsonIgnore
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
}
