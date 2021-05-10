package com.mycompany.dto;

import com.mycompany.entity.UserSkill;

public class UserSkillDTO {

    private Integer id;
    private Integer level;
    private SkillDTO skill;

    public UserSkillDTO() {

    }

    public UserSkillDTO(UserSkill userSkill) {
        this.id = userSkill.getId();
        this.level = userSkill.getSkillLevel();
        this.skill = new SkillDTO(userSkill.getSkill());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public SkillDTO getSkillDTO() {
        return skill;
    }

    public void setSkillDTO(SkillDTO skillDTO) {
        this.skill = skillDTO;
    }
}
