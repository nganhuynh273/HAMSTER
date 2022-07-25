package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.ICustomerTypeDAO;
import com.example.hamster.model.CustomerType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeDAOImpl implements ICustomerTypeDAO {
    private static final String SHOW_ALL_CUSTOMER_TYPE    = "SELECT* FROM customer_type;";

    public CustomerTypeDAOImpl() {
    }

    @Override
    public List<CustomerType> showAllCustomerType() {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CustomerType> customerTypeList = new ArrayList<>();
        if (connection != null) {
            try {
                statement = connection.prepareStatement(SHOW_ALL_CUSTOMER_TYPE);
                resultSet = statement.executeQuery();
                CustomerType customerType = null;
                while (resultSet.next()) {
                    int id = resultSet.getInt("customer_type_id");
                    String name = resultSet.getString("customer_type_name");
                    customerType = new CustomerType(id, name);
                    customerTypeList.add(customerType);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ConnectionDB.close();
            }
        }
        return customerTypeList;
    }

}
