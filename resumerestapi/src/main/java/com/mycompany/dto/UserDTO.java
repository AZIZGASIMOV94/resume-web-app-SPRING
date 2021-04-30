package com.mycompany.dto;

import com.mycompany.entity.UserTable;

public class UserDTO {

    private int id;
    private String name;
    private String surnname;

    public UserDTO() {
    }

    public UserDTO(int id, String name, String surnname) {
        this.id = id;
        this.name = name;
        this.surnname = surnname;
    }
    public UserDTO(UserTable u){
        this.id =u.getId();
        this.name =u.getName();
        this.surnname = u.getSurname();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnname() {
        return surnname;
    }

    public void setSurnname(String surnname) {
        this.surnname = surnname;
    }


}
