package com.mycompany.dao.inter;

import com.mycompany.entity.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDaoInter {
    //get all employment history by user id
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);
    //update employment history
    public boolean updateEmploymentHistory(EmploymentHistory emphistory);
    //insert employment history
    public boolean addEmploymentHistory(EmploymentHistory emphistory);
    //delete employment history
    public boolean deleteEmploymentHistory(int id);
}
