package com.example.hamster.service;

import com.example.hamster.model.Service;

import java.sql.SQLException;
import java.util.List;

public interface IServiceService {
    Service showService(int id);
    List<Service> showAllService ();
    void insertService (Service service) throws SQLException;
}
