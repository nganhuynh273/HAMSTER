package com.example.hamster.dao;

import com.example.hamster.model.Service;

import java.sql.SQLException;
import java.util.List;

public interface IServiceDAO {
    Service showService(int id);
    List<Service> showAllService ();
    void insertService (Service service) throws SQLException;
}
