package com.mycompany.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.entity.UserTable;
import com.mycompany.dao.inter.UserDaoInter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/*
@Transactional
*/
@Repository
@Qualifier("userdao")
public class UserDaoImpl implements UserDaoInter {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<UserTable> searchUsers(String name, String surname, Integer nationalityId) {
        String jpql = "SELECT u FROM UserTable u WHERE 1=1";

            if(name != null && !name.trim().isEmpty()){
                jpql += " and u.name=:name ";
            }
            if(surname != null && !surname.trim().isEmpty()){

                jpql += " and u.surname=:surname ";
            }
            if(nationalityId != null ){
                jpql += " and u.nationality.id=:nid ";
            }

            Query query = em.createQuery(jpql, UserTable.class);
            if(name != null && !name.trim().isEmpty()){
                  query.setParameter("name", name);
            }
            if(surname != null &&!surname.trim().isEmpty()){
                query.setParameter("surname", surname);
            }
            if(nationalityId != null ){
                query.setParameter("nid", nationalityId);

            }
            List<UserTable> userList = query.getResultList();
        return userList;
    }

    @Override
    public List<UserTable> getAllUsers() {
        List<UserTable> res = new ArrayList<>();
        return res;
    }

    @Override
    public UserTable getUserByEmailAndPass(String email, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserTable> q1 = cb.createQuery(UserTable.class);
        Root<UserTable> userRoot = q1.from(UserTable.class);
        CriteriaQuery<UserTable> q2 = q1
                .where(cb.equal(userRoot.get("email"), email),cb.equal(userRoot.get("password"), password));
        
        Query query = em.createQuery(q2);

        List<UserTable>userList = query.getResultList();
        if(userList.size()==1){
            return userList.get(0);
        }
        return null;
    }
    
    
     //sql query in jpa
    @Override
    public UserTable getUserByEmail(String email){
        Query query = em.createNativeQuery("SELECT * FROM user_table WHERE email = ?",UserTable.class);
        query.setParameter(1, email);
        List<UserTable>userList = query.getResultList();
        if(userList.size()==1){
            return userList.get(0);
        }

        return null;
    }

    @Override
    public UserTable getById(int userId){
        UserTable user = em.find(UserTable.class, userId);
        return user;
    }
    
    @Override
    public boolean updateUser(UserTable u) {
        em.merge(u);
        return true;
    }

    @Override
    public boolean deleteUser(int id) {
        UserTable user = em.find(UserTable.class, id);
        em.remove(user);
        return true;
    }

    //private BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
    private BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(UserTable u) {
        //u.setPassword(crypt.encode(u.getPassword()));
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
        em.persist(u);
        return true;
    }

    public static void main(String[] args) {

        UserTable u2 = new UserTable(
                "eziz",
                "kerimov",
                "kerimov@mail.ru",
                "+994323232323",
                "12345");
        new UserDaoImpl().addUser(u2);
        System.out.println("added");
    }
}
