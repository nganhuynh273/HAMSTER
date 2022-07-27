package com.example.hamster.dao;

import com.example.hamster.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    Customer showCustomer(int id);
    List<Customer> showListCustomer();
    void insertCustomer (Customer customer) throws SQLException;
    boolean editCustomer(Customer customer) throws SQLException;
    String deleteCustomer (int id) throws SQLException;
    List<Customer> searchCustomer(String search);
   boolean isIdCardExisted(String idCard) throws SQLException;
   boolean isEmailExisted (String email) throws SQLException;
}
