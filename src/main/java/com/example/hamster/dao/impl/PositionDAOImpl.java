package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.IPositionDAO;
import com.example.hamster.model.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDAOImpl implements IPositionDAO {
    private static String SHOW_ALL_POSITION = "SELECT*FROM position;";

    public PositionDAOImpl() {
    }

    @Override
    public List<Position> showAllPosition() {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Position> positionList = new ArrayList<>();
        if (connection != null) {
            try {
                statement = connection.prepareStatement(SHOW_ALL_POSITION);
                resultSet = statement.executeQuery();
                Position position = null;
                while (resultSet.next()) {
                    int id = resultSet.getInt("position_id");
                    String name = resultSet.getString("position_name");
                    position  = new Position(id, name);
                    positionList.add(position);
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
        return positionList;

    }
}
