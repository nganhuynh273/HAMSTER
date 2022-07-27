package com.example.hamster.model;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class Contract {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double deposit;
    private double totalMoney;
    private int staffId;
    private int customerId;
    private int serviceId;

    public Contract(LocalDate startDate, LocalDate endDate, double deposit, double totalMoney, int staffId, int customerId, int serviceId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.totalMoney = totalMoney;
        this.staffId = staffId;
        this.customerId = customerId;
        this.serviceId = serviceId;
    }

    public Contract() throws ParseException{
        startDate = new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        endDate = new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Contract(int id, LocalDate startDate, LocalDate endDate,
                    double deposit, double totalMoney, int staffId, int customerId, int serviceId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.totalMoney = totalMoney;
        this.staffId = staffId;
        this.customerId = customerId;
        this.serviceId = serviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    @Future(message = "Ngày kết thúc phải sau ngày hiện tại!")
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
