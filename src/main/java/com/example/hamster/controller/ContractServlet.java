package com.example.hamster.controller;

import com.example.hamster.dao.impl.ContractDAOImpl;
import com.example.hamster.model.*;
import com.example.hamster.service.impl.ContractServiceImpl;
import com.example.hamster.service.impl.CustomerServiceImpl;
import com.example.hamster.service.impl.ServiceServiceImpl;
import com.example.hamster.service.impl.StaffServiceImpl;
import com.example.hamster.util.ParsingValidationUtils;
import com.example.hamster.util.ValidationUtils;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.BiConsumer;

@WebServlet(name = "ContractServlet", urlPatterns = "/contract")
public class ContractServlet extends HttpServlet {
    private ContractServiceImpl contractService = new ContractServiceImpl();
    private ContractDAOImpl contractDAO = new ContractDAOImpl();
    private StaffServiceImpl staffService = new StaffServiceImpl();
    private CustomerServiceImpl customerService = new CustomerServiceImpl();
    private ServiceServiceImpl serviceService = new ServiceServiceImpl();
    private String errors;
    private Staff staff;
    private Customer customer;
    private Service service;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showCreateForm(request, response);
                    break;
                default:
                    listContract(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    createNewContract(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void createNewContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        Contract contract = new Contract();
        boolean flag = true;
        Map<String, String> hashMap = new HashMap<>();

        System.out.println(this.getClass() + " insertUser with validate");
        try {
            String startDate = request.getParameter("start_date");
            String endDate = request.getParameter("end_date");
            double deposit = Double.parseDouble(request.getParameter("deposit"));
            contract.setDeposit(deposit);
            double totalMoney = Double.parseDouble(request.getParameter("total_money"));
            contract.setTotalMoney(totalMoney);
            int staffId = Integer.parseInt(request.getParameter("staff_id"));
            contract.setStaffId(staffId);
            int customerId = Integer.parseInt(request.getParameter("customer_id"));
            contract.setCustomerId(customerId);
            int serviceId = Integer.parseInt(request.getParameter("service_id"));
            contract.setServiceId(serviceId);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Contract>> constraintViolations = validator.validate(contract);

            System.out.println("Contract: " + contract);

            if (!constraintViolations.isEmpty()) {
                errors = "<ul>";

                for (ConstraintViolation<Contract> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                            + "</li>";
                }
                errors += "</ul>";

                request.setAttribute("contract", contract);
                request.setAttribute("errors", errors);

                System.out.println(this.getClass() + " !constraintViolations.isEmpty()");
                request.getRequestDispatcher("/WEB-INF/contract/create.jsp").forward(request, response);
            } else {
                if (ParsingValidationUtils.isDateParsingType2(startDate)) {
//                    contract.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    if (ValidationUtils.checkStartDate(LocalDate.parse(startDate))) {
                        contract.setStartDate(LocalDate.parse(startDate));
                    } else {
                        hashMap.put("startDate", "Ngày bắt đầu hợp đồng phải là ngày hiện tại, " + ValidationUtils.validStartDate + ".");
                        flag = false;
                    }
                } else {
                    flag = false;
                    hashMap.put("startDate", "Ngày bắt đầu hợp đồng không hợp lệ!");
                }

                if (ParsingValidationUtils.isDateParsingType2(endDate)) {
//                    contract.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    if (ValidationUtils.checkEndDate(LocalDate.parse(endDate))) {
                        contract.setEndDate(LocalDate.parse(endDate));
                    } else {
                        hashMap.put("endDate", "Ngày kết thúc hợp đồng không được lớn hơn " + ValidationUtils.validEndDate + ".");
                        flag = false;

                    }
                } else {
                    hashMap.put("endDate", "Ngày kết thúc hợp đồng không hợp lệ!");
                    flag = false;

                }


                //staff.getId()
//                if (contract.getStaffId() != staff.getId()) {
//                    flag = false;
//                    hashMap.put("staffId", "Mã nhân viên sai");
//                }
//                if (contract.getCustomerId() != customer.getId()) {
//                    flag = false;
//                    hashMap.put("customerId", "Mã khách hàng sai");
//                }
//                if(contract.getServiceId() != service.getId()) {
//                    flag = false;
//                    hashMap.put("serviceId", "Mã dịch vụ sai");
//                }


                if (flag) {
                    contractDAO.insertContract(contract);
                    Contract c = new Contract();
                    request.setAttribute("contract", c);

                    request.setAttribute("success", "Đã thêm hợp đồng thành công");
                    request.getRequestDispatcher("/WEB-INF/contract/create.jsp").forward(request, response);
                } else {
                    errors = "<ul>";

                    hashMap.forEach(new BiConsumer<String, String>() {
                        @Override
                        public void accept(String keyError, String valueError) {
                            errors += "<li>" + valueError
                                    + "</li>";
                        }
                    });
                    errors += "</ul>";
                    request.setAttribute("contract", contract);
                    request.setAttribute("errors", errors);

                    System.out.println(this.getClass() + " !constraintViolations.isEmpty()");

                    request.getRequestDispatcher("/WEB-INF/contract/create.jsp").forward(request, response);
                }
            }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println(this.getClass() + " NumberFormatException: User info from request: " + contract);
            errors = "<ul>";
            errors += "<li>" + "Input format not right"
                    + "</li>";

            errors += "</ul>";


            request.setAttribute("contract", contract);
            request.setAttribute("errors", errors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contract/create.jsp");
            dispatcher.forward(request, response);
        } catch (ParseException | SQLException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }


    private void listContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contract> contractList = contractDAO.showAllContract();
        request.setAttribute("contractList", contractList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contract/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Staff> staffList = staffService.showAllStaff();
        List<Customer> customerList = customerService.showListCustomer();
        List<Service> serviceList = serviceService.showAllService();
        request.setAttribute("staffList", staffList);
        request.setAttribute("customerList", customerList);
        request.setAttribute("serviceList", serviceList);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contract/create.jsp");
        dispatcher.forward(request, response);

    }
}
