package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.IUserDAO;
import com.example.hamster.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    private static String SELECT_ALL_USER = "SELECT*FROM user;";

    public UserDAOImpl() {
    }

        @Override
        public List<User> showAllUser() {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            List<User> userList = new ArrayList<>();
            if (connection != null) {
                try {
                    statement = connection.prepareStatement(SELECT_ALL_USER);
                    resultSet = statement.executeQuery();
                    User user = null;
                    while (resultSet.next()) {
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");
//                        user = new User(username, password);
                        userList.add(user);
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
            return userList;
        }
    }

