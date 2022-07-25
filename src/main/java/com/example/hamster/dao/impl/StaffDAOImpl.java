package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.IStaffDAO;
import com.example.hamster.model.Staff;
import com.example.hamster.util.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements IStaffDAO {
    private static final String SHOW_ALL_STAFF = "SELECT * FROM staff";
    private static final String SHOW_STAFF_BY_ID = "SELECT * FROM staff WHERE staff_id = ?";
    private static final String INSERT_STAFF = "INSERT INTO staff (staff_name, staff_birthday, " +
            "staff_id_card, staff_salary, staff_phone, staff_email, staff_address, position_id, " +
            "education_degree_id, division_id, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_STAFF = "UPDATE staff SET staff_name = ?, staff_birthday = ?, " +
            "staff_id_card = ?, staff_salary = ?, staff_phone = ?, staff_email = ?, staff_address = ?, " +
            "position_id = ?, education_degree_id = ?, division_id = ?, username = ?, password = ? WHERE staff_id = ?;";
    private static final String DELETE_STAFF = "DELETE FROM staff WHERE staff_id = ?; ";
    private static final String SELECT_STAFF_BY_NAME = "SELECT * FROM staff WHERE substring_index(staff_name,' ', -1) LIKE ?;";
    private static final String IS_PHONE_NUMBER_EXISTED = "CALL sp_is_phone_number_existed(?, ?)";
    private ResultSet resultSet;

    public StaffDAOImpl() {
    }

    @Override
    public Staff showStaffId(int id) {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Staff staff = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SHOW_STAFF_BY_ID);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("staff_name");
                    Date birthday = resultSet.getDate("staff_birthday");
                    int idCard = Integer.parseInt(resultSet.getString("staff_id_card"));
                    double salary = Double.parseDouble(resultSet.getString("staff_salary"));
                    String phone = resultSet.getString("staff_phone");
                    String email = resultSet.getString("staff_email");
                    String address = resultSet.getString("staff_address");
                    int positionId = Integer.parseInt(resultSet.getString("position_id"));
                    int educationDegreeId = Integer.parseInt(resultSet.getString("education_degree_id"));
                    int divisionId = Integer.parseInt(resultSet.getString("division_id"));
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    staff = new Staff(id, name, birthday, idCard, salary, phone, email, address, positionId, educationDegreeId, divisionId, username, password);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionDB.close();
            }
        }
        return staff;
    }

    @Override
    public List<Staff> showAllStaff() {

        Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Staff> staffList = new ArrayList<>();
        if (connection != null) {
            try {
                statement = connection.prepareStatement(SHOW_ALL_STAFF);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("staff_id"));
                    String name = resultSet.getString("staff_name");
                    Date birthday = resultSet.getDate("staff_birthday");
                    int idCard = Integer.parseInt(resultSet.getString("staff_id_card"));
                    double salary = Double.parseDouble(resultSet.getString("staff_salary"));
                    String phone = resultSet.getString("staff_phone");
                    String email = resultSet.getString("staff_email");
                    String address = resultSet.getString("staff_address");
                    int positionId = Integer.parseInt(resultSet.getString("position_id"));
                    int educationDegreeId = Integer.parseInt(resultSet.getString("education_degree_id"));
                    int divisionId = Integer.parseInt(resultSet.getString("division_id"));
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    Staff staff = new Staff(id, name, birthday, idCard, salary, phone, email, address, positionId, educationDegreeId, divisionId, username, password);
                    staffList.add(staff);
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
        return staffList;

    }

    @Override
    public void insertStaff(Staff staff) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(INSERT_STAFF);
                preparedStatement.setString(1, staff.getName());
                preparedStatement.setDate(2, staff.getBirthday());
                preparedStatement.setInt(3, staff.getIdCard());
                preparedStatement.setDouble(4, staff.getSalary());
                preparedStatement.setString(5, staff.getPhone());
                preparedStatement.setString(6, staff.getEmail());
                preparedStatement.setString(7, staff.getAddress());
                preparedStatement.setInt(8, staff.getPositionId());
                preparedStatement.setInt(9, staff.getEducationDegreeId());
                preparedStatement.setInt(10, staff.getDivisionId());
                preparedStatement.setString(11, staff.getUsername());
                preparedStatement.setString(12, staff.getPassword());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                printSQLException(e);
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionDB.close();
            }
        }
    }

    @Override
    public boolean updateStaff(Staff staff) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        boolean rowUpdated;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(UPDATE_STAFF);
                preparedStatement.setString(1, staff.getName());
                preparedStatement.setDate(2, staff.getBirthday());
                preparedStatement.setInt(3, staff.getIdCard());
                preparedStatement.setDouble(4, staff.getSalary());
                preparedStatement.setString(5, staff.getPhone());
                preparedStatement.setString(6, staff.getEmail());
                preparedStatement.setString(7, staff.getAddress());
                preparedStatement.setInt(8, staff.getPositionId());
                preparedStatement.setInt(9, staff.getEducationDegreeId());
                preparedStatement.setInt(10, staff.getDivisionId());
                preparedStatement.setString(11, staff.getUsername());
                preparedStatement.setString(12, staff.getPassword());
                preparedStatement.setInt(13, staff.getId());
                rowUpdated = preparedStatement.executeUpdate() > 0;
                return rowUpdated;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionDB.close();
            }
        }
        return false;
    }

    @Override
    public void deleteStaff(int id) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(DELETE_STAFF);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionDB.close();
            }
        }
    }

    @Override
    public List<Staff> searchStaff(String search) {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Staff> staffList = new ArrayList<>();

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_NAME);
                String searchSQL = search.concat("%");
                preparedStatement.setString(1, searchSQL);
                resultSet = preparedStatement.executeQuery();
                Staff staff = null;
                while (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("staff_id"));
                    String name = resultSet.getString("staff_name");
                    Date birthday = resultSet.getDate("staff_birthday");
                    int idCard = Integer.parseInt(resultSet.getString("staff_id_card"));
                    double salary = Double.parseDouble(resultSet.getString("staff_salary"));
                    String phone = resultSet.getString("staff_phone");
                    String email = resultSet.getString("staff_email");
                    String address = resultSet.getString("staff_address");
                    int positionId = Integer.parseInt(resultSet.getString("position_id"));
                    int educationDegreeId = Integer.parseInt(resultSet.getString("education_degree_id"));
                    int divisionId = Integer.parseInt(resultSet.getString("division_id"));
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    staff = new Staff(id, name, birthday, idCard, salary, phone, email, address, positionId, educationDegreeId, divisionId, username, password);
                    staffList.add(staff);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionDB.close();
            }
        }
        return staffList;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public boolean isPhoneNumberExisted(Staff staff) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(IS_PHONE_NUMBER_EXISTED);
        statement.setString(1, staff.getPhone());
        statement.execute();
        return statement.getBoolean(2);
    }

    private static final String IS_EMAIL_EXISTED = "CALL sp_is_email_existed(?, ?)";

    public boolean isEmailExisted(Staff staff) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(IS_EMAIL_EXISTED);
        statement.setString(1, staff.getEmail());
        statement.execute();
        return statement.getBoolean(2);
    }
    private static final String IS_USERNAME_EXISTED = "CALL sp_is_username_existed(?, ?)";
    public boolean isUsernameExisted(Staff staff) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(IS_USERNAME_EXISTED);
        statement.setString(1, staff.getUsername());
        statement.execute();
        return statement.getBoolean(2);
    }
    @Override
    public boolean isExisted(Staff staff) throws SQLException {
        return false;
    }
}
