package com.example.hamster.controller;

import com.example.hamster.dao.impl.StaffDAOImpl;
import com.example.hamster.model.Customer;
import com.example.hamster.model.Staff;
import com.example.hamster.service.impl.StaffServiceImpl;
import com.example.hamster.util.ParsingValidationUtils;


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
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;

@WebServlet(name = "StaffServlet", urlPatterns = {"/staff"})
public class StaffServlet extends HttpServlet {
    private StaffServiceImpl staffService = new StaffServiceImpl();
    private StaffDAOImpl staffDAO = new StaffDAOImpl();
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
                    createNewStaff(request, response);
                    break;
                case "edit":
                    editStaff(request, response);
                    break;
                case "search":
                    searchStaff(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
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
                    deleteStaff(request, response);
                    break;
                case "search":
                    showSearchForm(request, response);
                    break;
                default:
                    listStaff(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Staff> staffList = staffService.showAllStaff();
        request.setAttribute("staffList", staffList);
        request.getRequestDispatcher("/WEB-INF/staff/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Staff staff = new Staff();
        request.setAttribute("staffList",  staff);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/staff/create.jsp");
        dispatcher.forward (request, response);
    }

    private void createNewStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Staff staff = new Staff();
        boolean flag = true;
        Map<String, String> hashMap = new HashMap<>();

        System.out.println(this.getClass() + " insertUser with validate");
        try {
            String name = request.getParameter("name").trim();
            staff.setName(name.trim());
            Date birthday = Date.valueOf(request.getParameter("birthday"));
            staff.setBirthday(birthday);
            String idCard = request.getParameter("id_card");
            staff.setIdCard(idCard);
            double salary = Double.parseDouble(request.getParameter("salary"));
            staff.setSalary(salary);
            String phone = request.getParameter("phone").trim();
            staff.setPhone(phone);
            String email = request.getParameter("email");
            staff.setEmail(email);
            String address = request.getParameter("address");
            staff.setAddress(address.trim());
            int positionId = Integer.parseInt(request.getParameter("position_id"));
            staff.setPositionId(positionId);
            int educationDegreeId = Integer.parseInt(request.getParameter("education_degree_id"));
            staff.setEducationDegreeId(educationDegreeId);
            int divisionId = Integer.parseInt(request.getParameter("division_id"));
            staff.setDivisionId(divisionId);
            String username = request.getParameter("username");
            staff.setUsername(username.trim());
            String password = request.getParameter("password").trim();
            staff.setPassword(password.trim());


            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Staff>> constraintViolations = validator.validate(staff);

            System.out.println("Staff: " + staff);

            if (!constraintViolations.isEmpty()) {
                errors = "<ul>";

                for (ConstraintViolation<Staff> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                            + "</li>";
                }
                errors += "</ul>";

                request.setAttribute("staff", staff);
                request.setAttribute("errors", errors);

                System.out.println(this.getClass() + " !constraintViolations.isEmpty()");
                request.getRequestDispatcher("/WEB-INF/staff/create.jsp").forward(request, response);
            } else {
                if (staffDAO.isIdCardExisted(idCard)) {
                    flag = false;
                    hashMap.put("id_card", "CCCD/CMND đã tồn tại!");
                }

                if (staffDAO.isPhoneNumberExisted(phone)) {
                    flag = false;
                    hashMap.put("phone", "Số điện thoại đã được đăng ký");
                }
                if (staffDAO.isEmailExisted(email)) {
                    flag = false;
                    hashMap.put("email", "Email đã được đăng ký");
                }
                if (staffDAO.isUserNameExisted(username)) {
                    flag = false;
                    hashMap.put("username", "Username đã tồn tại");
                }

                if (flag) {
                    staffDAO.insertStaff(staff);
                    Staff s = new Staff();
                    request.setAttribute("staff", s);

                    request.setAttribute("success", "Đã thêm nhân viên thành công");
                    request.getRequestDispatcher("/WEB-INF/staff/create.jsp").forward(request, response);
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

                    request.setAttribute("staff", staff);
                    request.setAttribute("errors", errors);

                    System.out.println(this.getClass() + " !constraintViolations.isEmpty()");

                    request.getRequestDispatcher("/WEB-INF/staff/create.jsp").forward(request, response);
                }
            }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println(this.getClass() + " NumberFormatException: User info from request: " + staff);
            errors = "<ul>";
            errors += "<li>" + "Input format not right"
                    + "</li>";

            errors += "</ul>";


            request.setAttribute("staff", staff);
            request.setAttribute("errors", errors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/staff/create.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }


    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException,
                 IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.showStaffId(id);
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("/WEB-INF/staff/create.jsp").forward(request, response);
    }

    private void editStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String idCard = request.getParameter("id_card");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String position = request.getParameter("position");
        int positionId = 0;
        switch (position) {
            case "Lễ tân":
                positionId = 1;
                break;
            case "Phục vụ":
                positionId = 2;
                break;
            case "Chuyên viên":
                positionId = 3;
                break;
            case "Giám sát":
                positionId = 4;
                break;
            case "Quản lý":
                positionId = 5;
                break;
            case "Giám đốc":
                positionId = 6;
                break;
        }
        String educationDegree = request.getParameter("education_degree");
        int educationDegreeId = 0;
        switch (educationDegree) {
            case "Trung cấp":
                educationDegreeId = 1;
                break;
            case "Cao đẳng":
                educationDegreeId = 2;
                break;
            case "Đại học":
                educationDegreeId = 3;
                break;
            case "Sau đại học":
                educationDegreeId = 4;
                break;
        }
        String division = request.getParameter("division");
        int divisionId = 0;
        switch (division) {
            case "Sale":
                divisionId = 1;
                break;
            case "Marketing":
                divisionId = 2;
                break;
            case "Hành chính":
                divisionId = 3;
                break;
            case "Phục vụ":
                divisionId = 4;
                break;
            case "Quản lý":
                divisionId = 5;
                break;
        }
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        Staff staffs = new Staff(id, name, birthday, idCard, salary, phone, email, address, positionId, educationDegreeId, divisionId, username, password);
        staffDAO.updateStaff(staffs);
//        request.setAttribute("message", "Create successful");
//        request.getRequestDispatcher("/WEB-INF/staff/edit.jsp").forward(request, response);
        response.sendRedirect("/staff");
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String message = staffDAO.deleteStaff(id);
        staffService.deleteStaff(id);
        List<Staff> staffList = staffService.showAllStaff();
        request.setAttribute("message", message);
        request.setAttribute("staffList", staffList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/staff/list.jsp");
        dispatcher.forward(request, response);

    }

    private void showSearchForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String search = request.getParameter("search");
        request.setAttribute("search", search);
        request.getRequestDispatcher("/WEB-INF/staff/search.jsp").forward(request, response);
    }

    private void searchStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Staff> staffList = new ArrayList<>();
        RequestDispatcher dispatcher;
        String search = request.getParameter("name");
        staffList = staffService.searchStaff(search);
        if (staffList == null) {
            dispatcher = request.getRequestDispatcher("/error-404.jsp");
        } else {
            request.setAttribute("search", search);
            request.setAttribute("staffList", staffList);
            request.setAttribute("message", "Đã tìm thấy");
            dispatcher = request.getRequestDispatcher("/WEB-INF/staff/search-result.jsp");
            dispatcher.forward(request, response);
        }
    }
}
