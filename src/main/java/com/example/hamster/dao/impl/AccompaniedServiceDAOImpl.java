package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.IAccompaniedServiceDAO;
import com.example.hamster.model.AccompaniedService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccompaniedServiceDAOImpl implements IAccompaniedServiceDAO {
    private static final String SELECT_ALL_ACCOMPANIED_SERVICE = "SELECT * FROM accompanied_service";
    public AccompaniedServiceDAOImpl() {}
    @Override
    public List<AccompaniedService> showAllAccompaniedService() {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<AccompaniedService> accompaniedServiceList = new ArrayList<>();

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SELECT_ALL_ACCOMPANIED_SERVICE);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("accompanied_service_id"));
                    String name = resultSet.getString("accompanied_service_name");
                    double cost = Double.parseDouble(resultSet.getString("accompanied_service_cost"));
                    int unit = Integer.parseInt(resultSet.getString("accompanied_service_unit"));
                    String status = resultSet.getString("accompanied_service_status");
                    AccompaniedService accompaniedService = new AccompaniedService(id, name, cost, unit, status);
                    accompaniedServiceList.add(accompaniedService);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionDB.close();
            }
        }
        return accompaniedServiceList;
    }
}
