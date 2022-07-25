package com.example.hamster.controller;


import com.example.hamster.model.AccompaniedService;
import com.example.hamster.model.Contract;
import com.example.hamster.model.ContractDetail;
import com.example.hamster.model.User;
import com.example.hamster.service.impl.AccompaniedServiceServiceImpl;
import com.example.hamster.service.impl.ContractDetailServiceImpl;
import com.example.hamster.service.impl.ContractServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ContractDetailServlet",urlPatterns = "/contract-detail")
public class ContractDetailServlet extends HttpServlet {
    ContractDetailServiceImpl contractDetailService = new ContractDetailServiceImpl();
    ContractServiceImpl contractService = new ContractServiceImpl();
    AccompaniedServiceServiceImpl accompaniedServiceService = new AccompaniedServiceServiceImpl();

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
                    createNewContractDetail (request, response);
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
                    showCreateForm (request, response);
                    break;
                default:
                    listContractDetail (request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listContractDetail (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<ContractDetail> contractDetailList = contractDetailService.selectAllContractDetail();
        List<AccompaniedService> accompaniedServiceList = accompaniedServiceService.showAllAccompaniedService();
        request.setAttribute("contractDetailList", contractDetailList);
        request.setAttribute("accompaniedServiceList", accompaniedServiceList);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/contract-detail/list.jsp").forward(request, response);
    }

    private void showCreateForm (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Contract> contractList = contractService.showALlContract();
        List<AccompaniedService> accompaniedServiceList = accompaniedServiceService.showAllAccompaniedService();
        request.setAttribute("contractList", contractList);
        request.setAttribute("accompaniedServiceList", accompaniedServiceList);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/contract-detail/create.jsp").forward(request, response);
    }

    private void createNewContractDetail (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int contractId = Integer.parseInt(request.getParameter("contract_id"));
        int accompaniedServiceId = Integer.parseInt(request.getParameter("accompanied_service_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        ContractDetail contractDetail = new ContractDetail(contractId, accompaniedServiceId, quantity);
        contractDetailService.insertContractDetail(contractDetail);
        request.setAttribute("message", "Create successful");
        request.getRequestDispatcher("/WEB-INF/contract-detail/create.jsp").forward(request, response);
    }
}
