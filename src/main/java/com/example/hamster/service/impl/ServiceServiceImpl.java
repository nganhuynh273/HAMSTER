package com.example.hamster.service.impl;

import com.example.hamster.model.Service;
import com.example.hamster.dao.impl.ServiceDAOImpl;
import com.example.hamster.service.IServiceService;

import java.sql.SQLException;
import java.util.List;

public class ServiceServiceImpl implements IServiceService {
    ServiceDAOImpl serviceDAO =new ServiceDAOImpl();

    @Override
    public Service showService(int id) {
        return serviceDAO.showService(id);
    }

    public List<Service> showAllService() {
        return serviceDAO.showAllService();
    }

    @Override
    public void insertService(Service service) throws SQLException {
        serviceDAO.insertService(service);
    }
}
