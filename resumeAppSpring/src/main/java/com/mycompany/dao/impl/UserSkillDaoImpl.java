package com.mycompany.dao.impl;

import com.mycompany.entity.UserSkill;
import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.UserSkillDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet resultSet) throws Exception{
        int userSkillId = resultSet.getInt("userSkillId");
        int userId = resultSet.getInt("id");
        String userName = resultSet.getString("name");
        String userSurName = resultSet.getString("surname");
        int skillId = resultSet.getInt("skill_id");
        String skillName = resultSet.getString("skill_name");
        int skillLevel = resultSet.getInt("skill_level");
        //return new UserSkill(userSkillId,new UserTable(userId,userName,userSurName), new Skill(skillId,skillName), skillLevel);
        return null;
    }
    
    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> uSkill = new ArrayList<UserSkill>();
     
        try( Connection con = dbConnect();) {
            PreparedStatement pstatement = con.prepareStatement("SELECT usk.id as userSkillId,"
                    + "                                          ut.*, "
                    + "                                          usk.skill_id, "
                    + "                                          sk.name as skill_name, "
                    + "                                          usk.skill_level as skill_level FROM "
                    + "                                          user_table ut LEFT JOIN user_skill usk on ut.id = usk.user_id LEFT join "
                    + "                                          skill sk on sk.id = usk.skill_id WHERE ut.id = ?");
            pstatement.setInt(1, userId);
            pstatement.execute();
            ResultSet resultSet = pstatement.getResultSet();
            while (resultSet.next()){
               UserSkill userSkill = getUserSkill(resultSet);
               uSkill.add(userSkill);
            }
            //close connection
           // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uSkill;
    }
    
    @Override
    public boolean deleteUserSkill(int id) {
        try(Connection con = dbConnect();) {
            PreparedStatement pst = con.prepareStatement("DELETE FROM user_skill WHERE id=?");
            pst.setInt(1, id);
            pst.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    @Override
    public boolean insertUserSkill(UserSkill us){
        boolean b = true;
        try (Connection con = dbConnect()){
            PreparedStatement pst = con.prepareCall("INSERT INTO user_skill(skill_id,user_id,skill_level) values(?,?,?)");
            pst.setInt(1, us.getSkill().getId());
            pst.setInt(2, us.getUser().getId());
            pst.setInt(3, us.getSkillLevel());
            b = pst.execute();
        } catch (Exception ex) {
            b = false;
            ex.printStackTrace();
        }
        return b;
        
    }

    @Override
    public boolean updateUserSkill(UserSkill usrSkl) {
        try(Connection con = dbConnect();) {
            PreparedStatement statement = con.prepareStatement("UPDATE user_skill SET skill_id=?, user_id=?,  skill_level=? WHERE id=?");
            statement.setInt(2, usrSkl.getSkill().getId());
            statement.setInt(1, usrSkl.getUser().getId());
            statement.setInt(3, usrSkl.getSkillLevel());
            statement.setInt(4, usrSkl.getId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
