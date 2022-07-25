package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.IContractDAO;
import com.example.hamster.model.Contract;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractDAOImpl implements IContractDAO {
    private static final String SHOW_ALL_CONTRACT = "SELECT * FROM contract";
    private static final String SHOW_CONTRACT_BY_ID = "SELECT * FROM contract WHERE contract_id = ?";
    private static final String INSERT_CONTRACT = "INSERT INTO contract (contract_start_date, contract_end_date, " +
            "contract_deposit, contract_total_money, staff_id, customer_id, service_id) VALUES (?, ?, ?, ?, ?, ?, ?);";

    public ContractDAOImpl() {
    }


    @Override
    public Contract showContract(int id) {

        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Contract contract = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SHOW_CONTRACT_BY_ID);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String startDate = resultSet.getString("contract_start_date");
                    String endDate = resultSet.getString("contract_end_date");
                    double deposit = Double.parseDouble(resultSet.getString("contract_deposit"));
                    double totalMoney = Double.parseDouble(resultSet.getString("contract_total_money"));
                    int staffId = Integer.parseInt(resultSet.getString("staff_id"));
                    int customerId = Integer.parseInt(resultSet.getString("customer_id"));
                    int serviceId = Integer.parseInt(resultSet.getString("service_id"));
                    contract = new Contract(LocalDate.parse(startDate), LocalDate.parse(endDate), deposit, totalMoney, staffId, customerId, serviceId);
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
        return contract;
    }

    @Override
    public List<Contract> showAllContract() {

        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Contract> contractList = new ArrayList<>();

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SHOW_ALL_CONTRACT);
                resultSet = preparedStatement.executeQuery();
                Contract contract = null;
                while (resultSet.next()) {
                    String startDate = resultSet.getString("contract_start_date");
                    String endDate = resultSet.getString("contract_end_date");
                    double deposit = Double.parseDouble(resultSet.getString("contract_deposit"));
                    double totalMoney = Double.parseDouble(resultSet.getString("contract_total_money"));
                    int staffId = Integer.parseInt(resultSet.getString("staff_id"));
                    int customerId = Integer.parseInt(resultSet.getString("customer_id"));
                    int serviceId = Integer.parseInt(resultSet.getString("service_id"));
                    contract = new Contract(LocalDate.parse(startDate), LocalDate.parse(endDate), deposit, totalMoney, staffId, customerId, serviceId);
                    contractList.add(contract);
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
        return contractList;
    }

    @Override
    public void insertContract(Contract contract) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(INSERT_CONTRACT);
                preparedStatement.setDate(1, Date.valueOf(contract.getStartDate()));
                preparedStatement.setDate(2, Date.valueOf(contract.getEndDate()));
                preparedStatement.setDouble(3, contract.getDeposit());
                preparedStatement.setDouble(4, contract.getTotalMoney());
                preparedStatement.setInt(5, contract.getStaffId());
                preparedStatement.setInt(6, contract.getCustomerId());
                preparedStatement.setInt(7, contract.getServiceId());
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
