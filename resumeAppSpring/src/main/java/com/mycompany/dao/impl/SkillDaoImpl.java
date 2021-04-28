package com.mycompany.dao.impl;

import com.mycompany.entity.Skill;
import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.SkillDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new Skill(id,name);
    }

    @Override
    public List<Skill> getAllSkills() {
         List<Skill> skills = new ArrayList<Skill>();
        try( Connection con = dbConnect();) {//try with resource 
            Statement statement = con.createStatement();
            statement.execute("SELECT *FROM skill");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
               Skill country = getSkill(resultSet);
               skills.add(country);
            }
            //close connection
            //con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        return skills;
    }

    @Override
    public boolean updateSkill(Skill l) {
        try(Connection con = dbConnect();) {
            PreparedStatement statement = con.prepareStatement("UPDATE skill SET name=? WHERE id=?");
            statement.setString(1, l.getName());
            statement.setInt(2, l.getId());
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
    public boolean addSkill(Skill skill) {
        try (Connection con = dbConnect()){
            PreparedStatement statement = con.prepareStatement("INSERT INTO skill(name) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, skill.getName());
            statement.execute();
            
            ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()){
                    skill.setId(generatedKeys.getInt(1));
                }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSkill(int id) {
        try( Connection con = dbConnect();) {
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM skill WHERE id="+id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
