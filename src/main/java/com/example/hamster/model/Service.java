package com.example.hamster.model;

import com.example.hamster.util.ValidationUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Service {
    private int id;
    private String name;
    private int area;
    private double cost;
    private int maxPeople;
    private int rentalTypeId;
    private int serviceTypeId;
    private String standardRoom;
    private String descriptionOtherConvenience;
    private double poolArea;
    private int numberOfFloors;

    public Service() {
    }

    public Service(String name, int area, double cost, int maxPeople, int rentalTypeId, int serviceTypeId,
                   String standardRoom, String descriptionOtherConvenience, double poolArea,
                   int numberOfFloors) {
        this.name = name;
        this.area = area;
        this.cost = cost;
        this.maxPeople = maxPeople;
        this.rentalTypeId = rentalTypeId;
        this.serviceTypeId = serviceTypeId;
        this.standardRoom = standardRoom;
        this.descriptionOtherConvenience = descriptionOtherConvenience;
        this.poolArea = poolArea;
        this.numberOfFloors = numberOfFloors;
    }

    public Service(int id, String name, int area, double cost, int maxPeople, int rentalTypeId,
                   int serviceTypeId, String standardRoom, String descriptionOtherConvenience,
                   double poolArea, int numberOfFloors) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.cost = cost;
        this.maxPeople = maxPeople;
        this.rentalTypeId = rentalTypeId;
        this.serviceTypeId = serviceTypeId;
        this.standardRoom = standardRoom;
        this.descriptionOtherConvenience = descriptionOtherConvenience;
        this.poolArea = poolArea;
        this.numberOfFloors = numberOfFloors;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull(message = "Tên không được để trống.")
    @Length(max = 50, message = "Tên phải ít hơn 100 chữ cái")
    @Pattern(regexp = ValidationUtils.FULL_NAME_REGEX,
            message = "Tên chỉ được chứa các chữ cái, viết hoa chữ cái đầu tiên của mỗi từ và không có khoảng trắng thừa..")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(value = 50, message = "Lỗi! Vui lòng kiểm tra lại")
    @Max(value = 500, message = "Tối đa 500m2")
    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
    @Min(value = 0, message = "Lỗi! Vui lòng kiểm tra lại")
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    @Min(value = 0, message = "Lỗi! Vui lòng kiểm tra lại")
    @Max(value = 15, message = "Tối đa chỉ 15 người")
    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getRentalTypeId() {
        return rentalTypeId;
    }

    public void setRentalTypeId(int rentalTypeId) {
        this.rentalTypeId = rentalTypeId;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getStandardRoom() {
        return standardRoom;
    }

    public void setStandardRoom(String standardRoom) {
        this.standardRoom = standardRoom;
    }

    public String getDescriptionOtherConvenience() {
        return descriptionOtherConvenience;
    }

    public void setDescriptionOtherConvenience(String descriptionOtherConvenience) {
        this.descriptionOtherConvenience = descriptionOtherConvenience;
    }
    @Min(value = 10, message = "Lỗi! Vui lòng kiểm tra lại")
    @Max(value = 100, message = "Tối đa 100m2")
    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }
    @Min(value = 0, message = "Lỗi! Vui lòng kiểm tra lại")
    @Max(value = 3, message = "Tối đa 3 tầng")
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
}
