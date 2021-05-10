package com.mycompany.dto;

import com.mycompany.entity.UserSkill;
import com.mycompany.entity.UserTable;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private List<UserSkillDTO> skill;

    public UserDTO() {
    }

    public UserDTO(UserTable u){
        this.id =u.getId();
        this.name =u.getName();
        this.surname = u.getSurname();
        List<UserSkillDTO> list = new ArrayList<>();

        List<UserSkill> userSkills = u.getUserSkillList();
        for(int i=0; i<userSkills.size(); i++){
            UserSkill userSkill = userSkills.get(i);
            list.add(new UserSkillDTO(userSkill));
        }
        skill = list;
    }

    public UserDTO(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public UserDTO(int id, String name, String surname, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email =email;
        this.password =password;
        this.phone =phone;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<UserSkillDTO> getSkill() {
        return skill;
    }


    public void setSkill(List<UserSkillDTO> skill) {
        this.skill = skill;
    }
}
