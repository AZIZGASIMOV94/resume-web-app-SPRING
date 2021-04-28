/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author User
 */
@Entity
@Table(name = "country_table")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "CountryTable.findAll", query = "SELECT c FROM CountryTable c"),
//    @NamedQuery(name = "CountryTable.findById", query = "SELECT c FROM CountryTable c WHERE c.id = :id"),
//    @NamedQuery(name = "CountryTable.findByName", query = "SELECT c FROM CountryTable c WHERE c.name = :name"),
//    @NamedQuery(name = "CountryTable.findByNationality", query = "SELECT c FROM CountryTable c WHERE c.nationality = :nationality")})
public class CountryTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "nationality")
    private String nationality;
    @OneToMany(mappedBy = "birthplace")
    private List<UserTable> userTableList;
    @OneToMany(mappedBy = "nationality")
    private List<UserTable> userTableList1;

    public CountryTable() {
    }

    public CountryTable(Integer id) {
        this.id = id;
    }

    public CountryTable(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @XmlTransient
    public List<UserTable> getUserTableList() {
        return userTableList;
    }

    public void setUserTableList(List<UserTable> userTableList) {
        this.userTableList = userTableList;
    }

    @XmlTransient
    public List<UserTable> getUserTableList1() {
        return userTableList1;
    }

    public void setUserTableList1(List<UserTable> userTableList1) {
        this.userTableList1 = userTableList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CountryTable)) {
            return false;
        }
        CountryTable other = (CountryTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.CountryTable[ id=" + id + " ]";
    }
    
}
