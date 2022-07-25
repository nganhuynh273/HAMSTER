package com.example.hamster.model;

public class ContractDetail {
    private int id;
    private int contractId;
    private int accompaniedServiceId;
    private int quantity;

    public ContractDetail() {
    }
    public ContractDetail(int contractId, int accompaniedServiceId, int quantity) {
        super();
        this.contractId = contractId;
        this.accompaniedServiceId = accompaniedServiceId;
        this.quantity = quantity;
    }
    public ContractDetail(int id, int contractId, int accompaniedServiceId, int quantity) {
        this.id = id;
        this.contractId = contractId;
        this.accompaniedServiceId = accompaniedServiceId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getAccompaniedServiceId() {
        return accompaniedServiceId;
    }

    public void setAccompaniedServiceId(int accompaniedServiceId) {
        this.accompaniedServiceId = accompaniedServiceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
