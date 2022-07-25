package com.example.hamster.dao;

import com.example.hamster.model.Contract;

import java.sql.SQLException;
import java.util.List;

public interface IContractDAO {
    Contract showContract(int id);
    List<Contract> showAllContract();
    void insertContract (Contract contract) throws SQLException;
}
