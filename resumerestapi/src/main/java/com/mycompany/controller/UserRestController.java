package com.mycompany.controller;


import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.dto.ResponseDTO;
import com.mycompany.dto.UserDTO;
import com.mycompany.entity.UserTable;
import com.mycompany.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: check decoded token here using encoded token:
 * URL: https://jwt.io/
 */


@RestController
public class UserRestController {

    /*
    @GetMapping("/users")
    public String getUsers(){
        return "123245";
    }
    */

    /*
    @Autowired
    private UserDaoInter userDaoInter;
    */

    /*
    @Autowired
    private UserRepository userRepository;
    */

    @Autowired
    private UserServiceInter userDaoInter;


    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getUsers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "nationality", required = false) Integer nationality) {

        List<UserTable> list = userDaoInter.searchUsers(name, surname, nationality);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            UserTable u = list.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable("id") int id) {
        UserTable user = userDaoInter.getById(id);

        UserDTO userDTOS = new UserDTO(user);

        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id) {
        UserTable user = userDaoInter.getById(id);
        userDaoInter.deleteUser(id);
        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user), "successfully deleted"));
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> insertUser( @RequestBody UserDTO userDto) {
        UserTable userTable = new UserTable();
        userTable.setName(userDto.getName());
        userTable.setSurname(userDto.getSurname());
        userTable.setEmail(userDto.getEmail());
        userTable.setPhone(userDto.getPhone());
        userTable.setPassword(userDto.getPassword());
        userDaoInter.addUser(userTable);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userTable.getId());
        userDTO.setName(userTable.getName());
        userDTO.setSurname(userTable.getSurname());
        userDTO.setEmail(userTable.getEmail());
        userDTO.setPassword(userTable.getPassword());
        return ResponseEntity.ok(ResponseDTO.of(userDTO,"Successfully added"));
    }
}