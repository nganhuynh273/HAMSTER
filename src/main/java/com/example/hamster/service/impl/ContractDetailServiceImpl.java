package com.example.hamster.service.impl;


import com.example.hamster.dao.impl.ContractDetailDAOImpl;
import com.example.hamster.model.ContractDetail;
import com.example.hamster.service.IContractDetailService;


import java.sql.SQLException;
import java.util.List;

public class ContractDetailServiceImpl implements IContractDetailService {
    ContractDetailDAOImpl contractDetailRepository = new ContractDetailDAOImpl();


    @Override
    public ContractDetail selectContractDetail(int id) {

        return contractDetailRepository.showContractDetail(id);
    }

    public List<ContractDetail> selectAllContractDetail() {

        return contractDetailRepository.showAllContractDetail();
    }

    @Override
    public void insertContractDetail(ContractDetail contractDetail) throws SQLException {
        contractDetailRepository.insertContractDetail(contractDetail);
    }

    public List<ContractDetail> selectContractDetailByContractId (int id) {
        return contractDetailRepository.selectContractDetailByContractId(id);
    }
}
