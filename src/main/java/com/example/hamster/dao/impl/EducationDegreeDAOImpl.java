package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.IEducationDegreeDAO;
import com.example.hamster.model.EducationDegree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EducationDegreeDAOImpl implements IEducationDegreeDAO {
    private static String SHOW_ALL_EDUCATION= "SELECT*FROM education_degree;";

    public EducationDegreeDAOImpl() {
    }

    @Override
    public List<EducationDegree> showAllEducation() {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<EducationDegree> educationDegreeList = new ArrayList<>();
        if (connection != null) {
            try {
                statement = connection.prepareStatement(SHOW_ALL_EDUCATION);
                resultSet = statement.executeQuery();
                EducationDegree educationDegree = null;
                while (resultSet.next()) {
                    int id = resultSet.getInt("education_degree_id");
                    String name = resultSet.getString("education_degree_name");
                    educationDegree  = new EducationDegree(id, name);
                    educationDegreeList.add(educationDegree);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ConnectionDB.close();
            }
        }
        return educationDegreeList;
    }
}
