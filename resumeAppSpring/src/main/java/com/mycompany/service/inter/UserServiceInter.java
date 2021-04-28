package com.mycompany.service.inter;

import com.mycompany.entity.UserTable;

import java.util.List;

/**
 *
 * @author azizg
 */
public interface UserServiceInter {
    //get all users
    public List<UserTable> getAllUsers();
    //get one user
    public UserTable getById(int id);
    //update user
    public boolean updateUser(UserTable u);
    //insert user
    public boolean addUser(UserTable u);
    //delete user
    public boolean deleteUser(int id);
    
    public List<UserTable> searchUsers(String name, String surname, Integer Nationality);
    //get all skill by user id
    //public List<UserSkill> getAllSkillByUserId(int userId);
    //get user according to email and pass
    public UserTable getUserByEmailAndPass(String email, String password);
    //get user by email
    public UserTable getUserByEmail(String email);

}
