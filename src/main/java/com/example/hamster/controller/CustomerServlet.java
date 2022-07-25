package com.example.hamster.controller;


import com.example.hamster.dao.impl.CustomerDAOImpl;
import com.example.hamster.model.Customer;
import com.example.hamster.service.impl.CustomerServiceImpl;
import com.example.hamster.util.ValidationUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.sql.Date;
import java.util.function.BiConsumer;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    CustomerServiceImpl customerService = new CustomerServiceImpl();
    private String errors = "";



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
                    createNewCustomer(request, response);
                    break;
                case "edit":
                    editCustomer(request, response);
                    break;
                case "search":
                    searchCustomer(request, response);
                    break;

            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }
    private void createNewCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ParseException {
//        List<String> errors = new ArrayList<>();
//        List<Customer> customers = customerDAO.showListCustomer();

        Customer customer = new Customer();
        boolean flag = true;
        Map<String, String> hashMap = new HashMap<>();

        System.out.println(this.getClass() + " insertUser with validate");
        try {
            String name = request.getParameter("name");
            customer.setName(name.trim());

            Date birthday= Date.valueOf(request.getParameter("birthday"));
            customer.setBirthday(birthday);

            int gender = Integer.parseInt(request.getParameter("gender"));
            customer.setGender(gender);

            String idCard = request.getParameter("id_card");
            customer.setIdCard(idCard);

            String phone = request.getParameter("phone");
            customer.setPhone(phone.trim());

            String email = request.getParameter("email");
            customer.setEmail(email.trim());

            String address = request.getParameter("address");
            customer.setAddress(address.trim());

            int customerTypeId = Integer.parseInt(request.getParameter("customer_type_id"));
            customer.setCustomerTypeId(customerTypeId);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(customer);

            System.out.println("Customer: " + customer);

            if (!constraintViolations.isEmpty()) {
                errors = "<ul>";

                for (ConstraintViolation<Customer> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                            + "</li>";
                }
                errors += "</ul>";

                request.setAttribute("customer", customer);
                request.setAttribute("errors", errors);

                System.out.println(this.getClass() + " !constraintViolations.isEmpty()");
                request.getRequestDispatcher("/WEB-INF/customer/create.jsp").forward(request, response);
            } else {
                if (customerDAO.isIdCardExisted(idCard)) {
                    flag = false;
                    hashMap.put("id_card", "Thẻ khách hàng đã tồn tại");
                }

                if (flag) {
                    customerDAO.insertCustomer(customer);
                    Customer c = new Customer();
                    request.setAttribute("customer", c);

                    request.setAttribute("success", "Đã thêm khách hàng thành công");
                    request.getRequestDispatcher("/WEB-INF/customer/create.jsp").forward(request, response);
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
                    request.setAttribute("customer", customer);
                    request.setAttribute("errors", errors);

                    System.out.println(this.getClass() + " !constraintViolations.isEmpty()");

                    request.getRequestDispatcher("/WEB-INF/customer/create.jsp").forward(request, response);
                    }
                }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println(this.getClass() + " NumberFormatException: User info from request: " + customer);
            errors = "<ul>";
            errors += "<li>" + "Input format not right"
                    + "</li>";

            errors += "</ul>";


            request.setAttribute("customer", customer);
            request.setAttribute("errors", errors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/customer/create.jsp");
            dispatcher.forward(request, response);
            } catch (ParseException | SQLException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }



    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ParseException {
        List<String> errors = new ArrayList<>();
        List<Customer> customers = customerDAO.showListCustomer();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name").trim();
        Date birthday= Date.valueOf(request.getParameter("birthday"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        String idCard = request.getParameter("id_card");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int customerTypeId = Integer.parseInt(request.getParameter("customer_type_id"));

            Customer customer = new Customer(id, name, birthday, gender, idCard, phone, email, address, customerTypeId);
            customerService.editCustomer(customer);
            request.setAttribute("message", "Thêm mới thành công");
        request.getRequestDispatcher("/WEB-INF/customer/edit.jsp").forward(request, response);

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
                    showCreateForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteCustomer(request, response);
                    break;
                case "search":
                    searchCustomer(request,response);
                    break;
                default:
                    listCustomer(request, response);
                    break;
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }


    private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Customer> customerList = customerService.showListCustomer();
        request.setAttribute("customerList", customerList);
        request.getRequestDispatcher("/WEB-INF/customer/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ParseException {
        Customer customer = new Customer();
        request.setAttribute("customerList",  customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/customer/create.jsp");
              dispatcher.forward (request, response);
    }



    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String message = customerDAO.deleteCustomer(id);
        customerService.deleteCustomer(id);
        List<Customer> customerList = customerService.showListCustomer();
        request.setAttribute("message", message);
        request.setAttribute("customerList", customerList);
        request.getRequestDispatcher("/WEB-INF/customer/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.showCustomer(id);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/WEB-INF/customer/edit.jsp").forward(request, response);
    }

    private void searchCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Customer> customerList = new ArrayList<>();
        RequestDispatcher dispatcher;
        String search = request.getParameter("name");
        customerList = customerService.searchCustomer(search);
        if (customerList == null) {
            dispatcher = request.getRequestDispatcher("/error-404.jsp");
        } else {
            request.setAttribute("search", search);
            request.setAttribute("customerList", customerList);
            dispatcher = request.getRequestDispatcher("/WEB-INF/customer/search-result.jsp");
            dispatcher.forward(request, response);
        }
    }
}
