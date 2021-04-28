package com.mycompany.dao.inter;

import com.mycompany.entity.Skill;

import java.util.List;

public interface SkillDaoInter {
    //get all skill
    public List<Skill> getAllSkills();
    //update skill
    public boolean updateSkill(Skill s);
    //insert skill
    public boolean addSkill(Skill s);
    //delete skill
    public boolean deleteSkill(int id);
}
