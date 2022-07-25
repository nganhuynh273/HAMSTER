package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.IContractDetailDAO;
import com.example.hamster.model.ContractDetail;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractDetailDAOImpl implements IContractDetailDAO {
    private static final String SELECT_ALL_CONTRACT_DETAIL = "SELECT * FROM contract_detail";
    private static final String SELECT_CONTRACT_DETAIL_BY_ID = "SELECT * FROM contract_detail WHERE contract_detail_id = ?";
    private static final String INSERT_CONTRACT_DETAIL_SQL = "INSERT INTO contract_detail (contract_id, accompanied_service_id, " +
            "quantity) VALUES (?, ?, ?);";
    private static final String SELECT_CONTRACT_DETAIL_BY_CONTRACT_ID = "SELECT * FROM contract_detail WHERE contract_id = ?;";

    public ContractDetailDAOImpl() {}

    public ContractDetail showContractDetail(int id) {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ContractDetail contractDetail = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SELECT_CONTRACT_DETAIL_BY_ID);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int contractId = Integer.parseInt(resultSet.getString("contract_id"));
                    int accompaniedServiceId = Integer.parseInt(resultSet.getString("accompanied_service_id"));
                    int quantity = Integer.parseInt(resultSet.getString("quantity"));
                    contractDetail = new ContractDetail(id, contractId, accompaniedServiceId, quantity);
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
        return contractDetail;
    }

    @Override
    public List<ContractDetail> showAllContractDetail() {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ContractDetail> contractDetailList = new ArrayList<>();

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SELECT_ALL_CONTRACT_DETAIL);
                resultSet = preparedStatement.executeQuery();
                ContractDetail contractDetail = null;
                while (resultSet.next()) {
                    int contractDetailId = Integer.parseInt(resultSet.getString("contract_detail_id"));
                    int contractId = Integer.parseInt(resultSet.getString("contract_id"));
                    int accompaniedServiceId = Integer.parseInt(resultSet.getString("accompanied_service_id"));
                    int quantity = Integer.parseInt(resultSet.getString("quantity"));
                    contractDetail = new ContractDetail(contractDetailId, contractId, accompaniedServiceId, quantity);
                    contractDetailList.add(contractDetail);
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
        return contractDetailList;
    }

    @Override
    public void insertContractDetail(ContractDetail contractDetail) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(INSERT_CONTRACT_DETAIL_SQL);
                preparedStatement.setInt(1, contractDetail.getContractId());
                preparedStatement.setInt(2, contractDetail.getAccompaniedServiceId());
                preparedStatement.setInt(3, contractDetail.getQuantity());
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

    public List<ContractDetail> selectContractDetailByContractId (int id) {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ContractDetail> contractDetailList = new ArrayList<>();

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SELECT_CONTRACT_DETAIL_BY_CONTRACT_ID);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                ContractDetail contractDetail = null;
                while (resultSet.next()) {
                    int contractDetailId = resultSet.getInt("contract_detail_id");
                    int accompaniedServiceId = resultSet.getInt("accompanied_service_id");
                    int quantity = resultSet.getInt("quantity");
                    contractDetail = new ContractDetail(contractDetailId, id, accompaniedServiceId, quantity);
                    contractDetailList.add(contractDetail);
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
        return contractDetailList;
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
