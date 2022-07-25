package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.IRentalTypeDAO;
import com.example.hamster.model.RentalType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalTypeDAOImpl implements IRentalTypeDAO {
    private static final String SHOW_ALL_RENTAL_TYPE = "SELECT*FROM service_type;";

    public RentalTypeDAOImpl() {
    }

    @Override
    public List<RentalType> showAllRentalType() {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<RentalType> rentalTypeList = new ArrayList<>();
        if (connection != null) {
            try {
                statement = connection.prepareStatement(SHOW_ALL_RENTAL_TYPE);
                resultSet = statement.executeQuery();
                RentalType rentalType = null;
                while (resultSet.next()) {
                    int id = resultSet.getInt("rental_type_id");
                    String name = resultSet.getString("rental_type_name");
                    double cost = resultSet.getDouble("rental_type_cost");
                    rentalType = new RentalType(id, name, cost);
                    rentalTypeList.add(rentalType);
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
        return rentalTypeList;

    }
}
