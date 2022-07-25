package com.example.hamster.controller;

import com.example.hamster.dao.impl.StaffDAOImpl;
import com.example.hamster.model.Staff;
import com.example.hamster.service.impl.StaffServiceImpl;
import com.example.hamster.util.ParsingValidationUtils;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StaffServlet", urlPatterns = {"/staff"})
public class StaffServlet extends HttpServlet {
    StaffServiceImpl staffService = new StaffServiceImpl();
    StaffDAOImpl staffDAO = new StaffDAOImpl();

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
        request.getRequestDispatcher("/WEB-INF/staff/create.jsp").forward(request, response);
    }

    private void createNewStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String name = request.getParameter("name").trim();
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        int idCard = Integer.parseInt(request.getParameter("id_card"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        String phone = request.getParameter("phone").trim();
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int positionId = Integer.parseInt(request.getParameter("position_id"));
        int educationDegreeId = Integer.parseInt(request.getParameter("education_degree_id"));
        int divisionId = Integer.parseInt(request.getParameter("division_id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password").trim();

        Staff staff = new Staff(name, birthday, idCard, salary, phone, email, address, positionId, educationDegreeId, divisionId, username, password);
        staffService.insertStaff(staff);
        request.setAttribute("message", "Create successful");
        request.getRequestDispatcher("/WEB-INF/staff/list.jsp").forward(request, response);
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
        int idCard = Integer.parseInt(request.getParameter("id_card"));
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
        Staff staff = new Staff(id, name, birthday, idCard, salary, phone, email, address, positionId, educationDegreeId, divisionId, username, password);
        staffService.updateStaff(staff);
        request.setAttribute("message", "Create successful");
        request.getRequestDispatcher("/WEB-INF/staff/edit.jsp").forward(request, response);
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        staffService.deleteStaff(id);
        List<Staff> staffList = staffService.showAllStaff();
        request.setAttribute("staffList", staffList);
        request.getRequestDispatcher("/WEB-INF/staff/list.jsp").forward(request, response);
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
