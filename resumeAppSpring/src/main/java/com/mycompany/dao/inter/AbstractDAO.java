package com.mycompany.dao.inter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
/*
    irrelevant in this project
 */

public abstract class AbstractDAO { 

    public Connection dbConnect() throws Exception{
        //in new versions there is not need for this method
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume_db";
        String username = "root";
        String password = "";
        Connection con = DriverManager.getConnection(url,username, password);
        return con;
    }
    
    private static EntityManagerFactory emf = null;
    
    public EntityManager entityManage(){
        if(emf ==null){
            emf = Persistence.createEntityManagerFactory("com.mycompany_resumeApp_jar_1.0-SNAPSHOTPU3");
        }
        EntityManager em = emf.createEntityManager();
        return em;
    }
    
    public void closeEmf(){
        emf.close();
    }
}
