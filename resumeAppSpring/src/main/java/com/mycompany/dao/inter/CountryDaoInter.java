package com.mycompany.dao.inter;

import com.mycompany.entity.CountryTable;

import java.util.List;

public interface CountryDaoInter {
    //get all countries
    public List<CountryTable> getAllCountries();
    //get all countries
    public List<CountryTable> getAllNationalities();
    //update country
    public boolean updateCountry(CountryTable c);
    //insert country
    public boolean addCountry(CountryTable c);
    //delete country
    public boolean deleteCountry(int id);
}
