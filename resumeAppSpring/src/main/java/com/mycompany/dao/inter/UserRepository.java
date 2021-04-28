package com.mycompany.dao.inter;

import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Integer>/*, UserRepositoryCustom */{
    UserTable findByName(String name);
    UserTable findBySurname(String surname);

    /*@Query(value = "select u from UserTable u where u.email =:email")
    UserTable findByEmail(@Param(value = "email") String email);*/


    @Query(value = "select u from UserTable u where u.email =?1")
    UserTable findByEmail(String email);
}
