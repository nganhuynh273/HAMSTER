package com.example.hamster.service.impl;

import com.example.hamster.model.Contract;
import com.example.hamster.dao.impl.ContractDAOImpl;
import com.example.hamster.service.IContractService;

import java.sql.SQLException;
import java.util.List;

public class ContractServiceImpl implements IContractService {
    ContractDAOImpl contractDAO = new ContractDAOImpl();

    @Override
    public Contract showContract(int id) {
        return contractDAO.showContract(id);
    }

    public List<Contract> showALlContract() {
        return null;
    }

    @Override
    public void insertContract(Contract contract) throws SQLException {
        contractDAO.insertContract(contract);
    }

}
