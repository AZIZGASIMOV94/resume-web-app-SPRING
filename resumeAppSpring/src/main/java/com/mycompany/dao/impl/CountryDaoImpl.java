package com.mycompany.dao.impl;

import com.mycompany.entity.CountryTable;
import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.CountryDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {
    
    private CountryTable getCountry(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String nationality = resultSet.getNString("nationality");
        return new CountryTable(id, name, nationality);
    }

    @Override
    public List<CountryTable> getAllCountries() {
        List<CountryTable> countries = new ArrayList<CountryTable>();
        try( Connection con = dbConnect();) {//try with resource 
            Statement statement = con.createStatement();
            statement.execute("SELECT *FROM country_table");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
               CountryTable country = getCountry(resultSet);
               countries.add(country);
            }
           // con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        return countries;
    }

    @Override
    public boolean updateCountry(CountryTable c) {
        try(Connection con = dbConnect();) {
            PreparedStatement statement = con.prepareStatement("UPDATE country_table SET name=?, nationality=? WHERE id=?");
            statement.setString(1, c.getName());
            statement.setString(2, c.getNationality());
            statement.setInt(3, c.getId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean addCountry(CountryTable c) {
        try (Connection con = dbConnect()){
            PreparedStatement statement = con.prepareStatement("INSERT INTO country_table(name,nationality) VALUES(?,?)");
            statement.setString(1, c.getName());
            statement.setString(2, c.getNationality());
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCountry(int id) {
        try( Connection con = dbConnect();) {
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM country_table WHERE id="+id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public List<CountryTable> getAllNationalities() {
         List<CountryTable> countries = new ArrayList<CountryTable>();
        try( Connection con = dbConnect();) {//try with resource 
            Statement statement = con.createStatement();
            statement.execute("SELECT id, nationality FROM country_table");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nationality = resultSet.getNString("nationality");
                countries.add(new CountryTable(id,null,nationality));
            }
           // con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        return countries;
    }
}
