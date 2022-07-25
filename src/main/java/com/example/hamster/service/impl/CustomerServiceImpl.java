package com.example.hamster.service.impl;

import com.example.hamster.model.Customer;
import com.example.hamster.dao.impl.CustomerDAOImpl;
import com.example.hamster.service.ICustomerService;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    @Override
    public Customer showCustomer(int id) {
        return customerDAO.showCustomer(id);
    }

    public List<Customer> showListCustomer() {
        return customerDAO.showListCustomer();
    }

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        customerDAO.insertCustomer(customer);
    }

    @Override
    public boolean editCustomer(Customer customer) throws SQLException {
        return customerDAO.editCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        customerDAO.deleteCustomer(id);
    }

    @Override
    public List<Customer> searchCustomer(String search) {
        return customerDAO.searchCustomer(search);
    }
}
