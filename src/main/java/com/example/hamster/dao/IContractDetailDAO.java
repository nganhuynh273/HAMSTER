package com.example.hamster.dao;

import com.example.hamster.model.ContractDetail;

import java.sql.SQLException;
import java.util.List;

public interface IContractDetailDAO {
    ContractDetail showContractDetail (int id);
    List<ContractDetail> showAllContractDetail ();
    void insertContractDetail (ContractDetail contractDetail) throws SQLException;
}
