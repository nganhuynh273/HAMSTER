package com.example.hamster.service;

import com.example.hamster.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerService {
    Customer showCustomer(int id);
    List<Customer> showListCustomer();
    void insertCustomer (Customer customer) throws SQLException;
    boolean editCustomer(Customer customer) throws SQLException;
    void deleteCustomer (int id) throws SQLException;
    List<Customer> searchCustomer(String search);
}
