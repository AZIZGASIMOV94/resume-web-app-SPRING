package com.mycompany.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.entity.UserTable;
import com.mycompany.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInter {

    @Autowired
    private UserDaoInter userDao;

    @Override
    public List<UserTable> searchUsers(String name, String surname, Integer nationalityId) {
        return userDao.searchUsers(name, surname, nationalityId);
    }

    @Override
    public List<UserTable> getAllUsers() {
        List<UserTable> res = new ArrayList<>();
        return res;
    }

    @Override
    public UserTable getUserByEmailAndPass(String email, String password) {
        return userDao.getUserByEmailAndPass(email,password);
    }

    @Override
    public UserTable getUserByEmail(String email){
       return userDao.getUserByEmail(email);
    }

    @Override
    public UserTable getById(int userId){
        return userDao.getById(userId);
    }
    
    @Override
    public boolean updateUser(UserTable u) {
       return userDao.updateUser(u);
    }

    @Override
    public boolean deleteUser(int id) {
       return userDao.deleteUser(id);
    }

    private BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(UserTable u) {
       return userDao.addUser(u);
    }

}
