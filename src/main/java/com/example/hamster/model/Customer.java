package com.example.hamster.model;

import com.example.hamster.util.ValidationUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.text.ParseException;


public class Customer {
    private int id;
    private String name;
    private Date birthday;
    private int gender;
    private String idCard;
    private String phone;
    private String email;
    private String address;
    private int customerTypeId;

    public Customer() throws ParseException {

    }

    public Customer(int id, String name, Date birthday, int gender, String idCard,
                    String phone, String email, String address, int customerTypeId) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.idCard = idCard;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.customerTypeId = customerTypeId;
    }

    public Customer(String name, Date birthday, int gender, String idCard, String phone,
                    String email, String address, int customerTypeId) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.idCard = idCard;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.customerTypeId = customerTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Length(max = 100, message = "Tên quá dài! Tên chỉ có thể chưa tối đa 100 ký tự.")
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
    @NotNull(message = "Gender must NOT be null.")
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Length(min = 9, max = 9, message = "Thẻ khách hàng chỉ được 9 số")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    @NotNull(message = "Email không được bỏ trống.")
    @Pattern(regexp = ValidationUtils.EMAIL_REGEX,
            message = "Email đã tồn tại hoặc không đúng định dạng!")
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

    public int getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(int customerTypeId) {
        this.customerTypeId = customerTypeId;
    }


}
