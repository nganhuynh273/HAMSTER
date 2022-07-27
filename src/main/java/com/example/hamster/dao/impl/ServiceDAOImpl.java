package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.IServiceDAO;
import com.example.hamster.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements IServiceDAO {
    private static final String SHOW_SERVICE_BY_ID ="SELECT * FROM service WHERE service_id = ?";
    private static final String SHOW_ALL_SERVICE ="SELECT * FROM service";
    private static final String INSERT_SERVICE ="INSERT INTO service (service_name,service_area,service_cost,service_max_people,rental_type_id, service_type_id,standard_room,\n" +
            "description_other_convenience,pool_area,number_of_floors)\n" +
            "values(?,?,?,?,?,?,?,?,?,?);";

    public ServiceDAOImpl() {
    }

    @Override
    public Service showService(int id) {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Service service = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SHOW_SERVICE_BY_ID);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("service_name");
                    int area = resultSet.getInt("service_area");
                    double cost = resultSet.getDouble("service_area");
                    int maxPeople = resultSet.getInt("service_max_people");
                    int rentalTypeId = resultSet.getInt("rental_type_id");
                    int serviceTypeId = resultSet.getInt("service_type_id");
                    String standardRoom = resultSet.getString("standard_room");
                    String descriptionOtherConvenience = resultSet.getString("description_other_convenience");
                    double poolArea = resultSet.getDouble("pool_area");
                    int numberOfFloors = resultSet.getInt("number_of_floors");
                    service = new Service(id, name, area, cost, maxPeople, rentalTypeId, serviceTypeId, standardRoom, descriptionOtherConvenience, poolArea, numberOfFloors);
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
        return service;
    }

    @Override
    public List<Service> showAllService() {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Service> serviceList = new ArrayList<>();
        if (connection != null) {
            try {
                statement = connection.prepareStatement(SHOW_ALL_SERVICE);
                resultSet = statement.executeQuery();
                Service service = null;
                while (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("service_id"));
                    String name = resultSet.getString("service_name");
                    int area = resultSet.getInt("service_area");
                    double cost = resultSet.getDouble("service_area");
                    int maxPeople = resultSet.getInt("service_max_people");
                    int rentalTypeId = resultSet.getInt("rental_type_id");
                    int serviceTypeId = resultSet.getInt("service_type_id");
                    String standardRoom = resultSet.getString("standard_room");
                    String descriptionOtherConvenience = resultSet.getString("description_other_convenience");
                    double poolArea = resultSet.getDouble("pool_area");
                    int numberOfFloors = resultSet.getInt("number_of_floors");
                    service = new Service(id, name, area, cost, maxPeople, rentalTypeId, serviceTypeId, standardRoom, descriptionOtherConvenience, poolArea, numberOfFloors);
                    serviceList.add(service);
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
        return serviceList;

    }

    @Override
    public void insertService(Service service) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(INSERT_SERVICE);
                preparedStatement.setString(1, service.getName());
                preparedStatement.setInt(2, service.getArea());
                preparedStatement.setDouble(3, service.getCost());
                preparedStatement.setInt(4, service.getMaxPeople());
                preparedStatement.setInt(5, service.getRentalTypeId());
                preparedStatement.setInt(6, service.getServiceTypeId());
                preparedStatement.setString(7, service.getStandardRoom());
                preparedStatement.setString(8, service.getDescriptionOtherConvenience());
                preparedStatement.setDouble(9, service.getPoolArea());
                preparedStatement.setInt(10, service.getNumberOfFloors());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                printSQLException(e);
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionDB.close();
            }
        }
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
