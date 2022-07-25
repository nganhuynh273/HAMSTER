package com.example.hamster.service;

import com.example.hamster.model.Contract;

import java.sql.SQLException;
import java.util.List;

public interface IContractService {
    Contract showContract(int id);
    List<Contract> showALlContract();
    void insertContract (Contract contract) throws SQLException;

}
