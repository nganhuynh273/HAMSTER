package com.example.hamster.controller;

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
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ContractServlet",urlPatterns = "/contract")
public class ContractServlet extends HttpServlet {
    ContractServiceImpl contractService = new ContractServiceImpl();
    StaffServiceImpl staffService = new StaffServiceImpl();
    CustomerServiceImpl customerService = new CustomerServiceImpl();
    ServiceServiceImpl serviceService = new ServiceServiceImpl();

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
                    createNewContract (request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void createNewContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        ArrayList<String> parsingErrors = new ArrayList<>();

        String startDate = request.getParameter("start_date");
        Contract contract = null;
        if (ParsingValidationUtils.isDateParsingType1(startDate)) {
            contract.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if (ValidationUtils.checkStartDate(contract.getStartDate())) {
                contract.setStartDate(contract.getStartDate());
            } else
                parsingErrors.add("Ngày bắt đầu hợp đồng không được nhỏ hơn " + ValidationUtils.validStartDate + ".");
        } else parsingErrors.add("Ngày bắt đầu hợp đồng không hợp lệ!");
        String endDate = request.getParameter("end_date");
        if (ParsingValidationUtils.isDateParsingType1(endDate)) {
            contract.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if (ValidationUtils.checkEndDate(contract.getEndDate())) {
                contract.setEndDate(contract.getEndDate());
            } else
                parsingErrors.add("Ngày kết thúc hợp đồng không được lớn hơn " + ValidationUtils.validEndDate + ".");
        } else parsingErrors.add("Ngày kết thúc hợp đồng không hợp lệ!");

        double deposit = Double.parseDouble(request.getParameter("deposit"));
        double totalMoney = Double.parseDouble(request.getParameter("total_money"));
        int staffId = Integer.parseInt(request.getParameter("staff_id"));
        int customerId = Integer.parseInt(request.getParameter("customer_id"));
        int serviceId = Integer.parseInt(request.getParameter("service_id"));
        contract = new Contract(LocalDate.parse(startDate), LocalDate.parse(endDate), deposit, totalMoney, staffId, customerId, serviceId);
        contractService.insertContract(contract);
        request.setAttribute("message", "Thêm mới Thành công");
        request.getRequestDispatcher("/WEB-INF/contract/create.jsp").forward(request, response);
    }


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
                    showCreateForm (request, response);
                    break;
                default:
                    listContract (request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void listContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contract> contractList = contractService.showALlContract();
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
        request.getRequestDispatcher("/WEB-INF/contract/create.jsp").forward(request, response);
    }
}
