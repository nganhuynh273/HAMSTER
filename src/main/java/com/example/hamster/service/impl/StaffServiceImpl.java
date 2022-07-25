package com.example.hamster.service.impl;

import com.example.hamster.model.Staff;
import com.example.hamster.dao.impl.StaffDAOImpl;
import com.example.hamster.service.IStaffService;

import java.sql.SQLException;
import java.util.List;

public class StaffServiceImpl implements IStaffService {
    StaffDAOImpl staffDAO = new StaffDAOImpl();

    @Override
    public Staff showStaffId(int id) {
        return staffDAO.showStaffId(id);
    }

    public List<Staff> showAllStaff() {
        return staffDAO.showAllStaff();
    }

    @Override
    public void insertStaff(Staff staff) throws SQLException {
        staffDAO.insertStaff(staff);
    }

    @Override
    public boolean updateStaff(Staff staff) throws SQLException {
        return staffDAO.updateStaff(staff);
    }

    @Override
    public void deleteStaff(int id) throws SQLException {
        staffDAO.deleteStaff(id);
    }

    @Override
    public List<Staff> searchStaff(String search) {
        return staffDAO.searchStaff(search);
    }
}
