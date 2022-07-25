package com.example.hamster.controller;

import com.example.hamster.model.Service;
import com.example.hamster.service.impl.ServiceServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServiceServlet", urlPatterns = "/service")
public class ServiceServlet extends HttpServlet {
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
                    createNewService(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void createNewService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        int area = Integer.parseInt(request.getParameter("area"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        int maxPeople = Integer.parseInt(request.getParameter("max_people"));
        int rentalTypeId = Integer.parseInt(request.getParameter("rental_type_id"));
        int serviceTypeId = Integer.parseInt(request.getParameter("service_type_id"));
        String standardRoom = request.getParameter("standard_room");
        String descriptionOtherConvenience = request.getParameter("description_other_convenience");
        double poolArea = Double.parseDouble(request.getParameter("pool_area"));
        int numberOfFloors = Integer.parseInt(request.getParameter("number_of_floors"));
        Service service = new Service(name, area, cost, maxPeople, rentalTypeId, serviceTypeId, standardRoom,
                descriptionOtherConvenience, poolArea, numberOfFloors);
        serviceService.insertService(service);
        request.setAttribute("message", "Thêm mới Thành Công");
        request.getRequestDispatcher("/WEB-INF/service/create.jsp").forward(request, response);
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
                default:
                    listService(request, response);
                    break;
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Service> serviceList = serviceService.showAllService();
        request.setAttribute("serviceList", serviceList);
        request.getRequestDispatcher("/WEB-INF/service/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/service/create.jsp").forward(request, response);

    }
}
