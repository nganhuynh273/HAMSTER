package com.example.hamster.service;

import com.example.hamster.model.Staff;

import java.sql.SQLException;
import java.util.List;

public interface IStaffService {
    Staff showStaffId(int id);
    List<Staff> showAllStaff();
    void insertStaff (Staff staff) throws SQLException;
    boolean updateStaff (Staff staff) throws SQLException;
    void deleteStaff (int id) throws SQLException;
    List<Staff> searchStaff(String search);
}
