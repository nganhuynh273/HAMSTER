package com.example.hamster.service;

import com.example.hamster.model.ContractDetail;

import java.sql.SQLException;
import java.util.List;

public interface IContractDetailService {
    ContractDetail selectContractDetail (int id);
    List<ContractDetail> selectAllContractDetail ();
    void insertContractDetail (ContractDetail contractDetail) throws SQLException;

}
