package com.mycompany.controller;


import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.dto.UserDTO;
import com.mycompany.entity.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

/*
    @GetMapping("/users")
    public String getUsers(){



        return "123245";
    }*/



    @Autowired
    private UserDaoInter userDaoInter;

    @GetMapping("/users")
    public ResponseEntity<List> getUsers(){
        List<UserTable> list = userDaoInter.searchUsers(null,null,null);

        List<UserDTO> userDTOS = new ArrayList<>();

        for(int i =0; i< list.size(); i++){
            UserTable u = list.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return ResponseEntity.ok(userDTOS);
    }
}
