package com.example.hamster.model;

import com.example.hamster.util.ValidationUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;

public class Staff {
    private int id;
    private String name;
    private Date birthday;
    private String idCard;
    private double salary;
    private String phone;
    private String email;
    private String address;
    private int positionId;
    private int educationDegreeId;
    private int divisionId;
    private String username;
    private String password;

    public Staff() {
    }

    public Staff(int id, String name, Date birthday, String idCard, double salary, String phone, String email,
                 String address, int positionId, int educationDegreeId, int divisionId, String username, String password) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.idCard = idCard;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.positionId = positionId;
        this.educationDegreeId = educationDegreeId;
        this.divisionId = divisionId;
        this.username = username;
        this.password = password;
    }
    public Staff(String name, Date birthday, String idCard, double salary, String phone, String email, String address, int positionId, int educationDegreeId, int divisionId,
                 String username, String password) {

        this.name = name;
        this.birthday = birthday;
        this.idCard = idCard;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.positionId = positionId;
        this.educationDegreeId = educationDegreeId;
        this.divisionId = divisionId;
        this.username = username;
        this.password = password;
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

    @NotNull(message = "Ngày sinh không được để trống.")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Length(min = 9, max = 9, message = "CCCD/CMND chỉ bao gồm 9 chữ số")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    @Min(value = 3000000, message = "Lương thấp nhất là 3.000.000VNĐ")
    @Max(value = 100000000, message = "Lương cao nhất là 100.000.000VNĐ")
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @NotNull(message = "Số điện thoại không được để trống.")
    @Pattern(regexp = ValidationUtils.PHONE_REGEX,
            message = "Số điện thoại: Chữ số đầu tiên phải là '0', chữ số thứ hai có dạng '1' đến '9' và độ dài từ 10 đến 11 chữ số.")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotNull(message = "Email không được để trống.")
    @Pattern(regexp = ValidationUtils.EMAIL_REGEX,
            message = "Định dạng địa chỉ email không hợp lệ!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getEducationDegreeId() {
        return educationDegreeId;
    }

    public void setEducationDegreeId(int educationDegreeId) {
        this.educationDegreeId = educationDegreeId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    @NotNull(message = "Username không được để trống.")
    @Pattern(regexp = ValidationUtils.USERNAME_REGEX,
            message = "Username không hợp lệ!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "Password không được để trống.")
    @Pattern(regexp = ValidationUtils.PASSWORD_REGEX,
            message = "Password không hợp lệ!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
