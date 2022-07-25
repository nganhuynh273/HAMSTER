package com.example.hamster.dao.impl;

import com.example.hamster.dao.ConnectionDB;
import com.example.hamster.dao.ICustomerDAO;
import com.example.hamster.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements ICustomerDAO {
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE customer_id = ?";
    private static final String INSERT_CUSTOMER = "INSERT INTO customer (customer_name, customer_birthday, " +
            "customer_gender, customer_id_card, customer_phone, customer_email, customer_address, customer_type_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String EDIT_CUSTOMER = "UPDATE customer SET customer_name = ?, " +
            "customer_birthday = ?, customer_gender = ?, customer_id_card = ?, customer_phone = ?, customer_email = ?, " +
            "customer_address = ?, customer_type_id = ? WHERE customer_id = ?;";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customer_id = ?; ";
    private static final String SHOW_CUSTOMER = "SELECT * FROM customer WHERE substring_index(customer_name,' ', -1) LIKE ?;";
    private static final String IS_EMAIL_EXISTED = "CALL sp_is_email_existed(?, ?)";
    private static final String GENDERS_LIST = "SELECT g.id,g.name FROM genders AS g;";
    private static final String IS_PHONE_NUMBER_EXISTED = "CALL sp_is_phone_number_existed(?, ?)";
    private static final String IS_CUSTOMER_IDCARD_EXISTED = "SELECT * FROM hamster_resort.customer where customer_id_card = ?";

    public CustomerDAOImpl() {
    }

    @Override
    public Customer showCustomer(int id) {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("customer_name");
                    Date birthday = resultSet.getDate("customer_birthday");
                    int gender = Integer.parseInt(resultSet.getString("customer_gender"));
                    String idCard = resultSet.getString("customer_id_card");
                    String phone = resultSet.getString("customer_phone");
                    String email = resultSet.getString("customer_email");
                    String address = resultSet.getString("customer_address");
                    int customerTypeId = Integer.parseInt(resultSet.getString("customer_type_id"));
                    customer = new Customer(id, name, birthday, gender, idCard, phone, email, address, customerTypeId);
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
        return customer;
    }

    @Override
    public List<Customer> showListCustomer() {

        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Customer> customerList = new ArrayList<>();

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("customer_id"));
                    String name = resultSet.getString("customer_name");
                    Date birthday = resultSet.getDate("customer_birthday");
                    int gender = Integer.parseInt(resultSet.getString("customer_gender"));
                    String idCard = resultSet.getString("customer_id_card");
                    String phone = resultSet.getString("customer_phone");
                    String email = resultSet.getString("customer_email");
                    String address = resultSet.getString("customer_address");
                    int customerTypeId = Integer.parseInt(resultSet.getString("customer_type_id"));
                    Customer customer = new Customer(id, name, birthday, gender, idCard, phone, email, address, customerTypeId);
                    customerList.add(customer);
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
        return customerList;
    }

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;

        if (connection != null) {
            try {
                //"INSERT INTO customer (customer_name, customer_birthday, " +
                //            "customer_gender, customer_id_card, customer_phone, customer_email, customer_address, customer_type_id) " +
                //            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setDate(2, (Date) customer.getBirthday());
                preparedStatement.setInt(3, customer.getGender());
                preparedStatement.setString(4, customer.getIdCard());
                preparedStatement.setString(5, customer.getPhone());
                preparedStatement.setString(6, customer.getEmail());
                preparedStatement.setString(7, customer.getAddress());
                preparedStatement.setInt(8, customer.getCustomerTypeId());

                System.out.println(this.getClass() + " insertCustomer(): " + preparedStatement);
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
    public boolean editCustomer(Customer customer) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        boolean rowUpdated;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(EDIT_CUSTOMER);
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setDate(2, (Date) customer.getBirthday());
                preparedStatement.setInt(3, customer.getGender());
                preparedStatement.setString(4, customer.getIdCard());
                preparedStatement.setString(5, customer.getPhone());
                preparedStatement.setString(6, customer.getEmail());
                preparedStatement.setString(7, customer.getAddress());
                preparedStatement.setInt(8, customer.getCustomerTypeId());

                System.out.println(this.getClass() + " editCustomer() query: " + preparedStatement);
                preparedStatement.setInt(9, customer.getId());
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
    public String deleteCustomer(int id) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
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
        return null;
    }



    @Override
    public List<Customer> searchCustomer(String search) {

        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Customer> customerList = new ArrayList<>();

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(SHOW_CUSTOMER);
                String searchSQL = search.concat("%");
                preparedStatement.setString(1, searchSQL);
                resultSet = preparedStatement.executeQuery();
                Customer customer = null;
                while (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("customer_id"));
                    String name = resultSet.getString("customer_name");
                    Date birthday = resultSet.getDate("customer_birthday");
                    int gender = Integer.parseInt(resultSet.getString("customer_gender"));
                    String idCard = resultSet.getString("customer_id_card");
                    String phone = resultSet.getString("customer_phone");
                    String email = resultSet.getString("customer_email");
                    String address = resultSet.getString("customer_address");
                    int customerTypeId = Integer.parseInt(resultSet.getString("customer_type_id"));
                    customer = new Customer(id, name, birthday, gender, idCard, phone, email, address, customerTypeId);
                    customerList.add(customer);
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
        return customerList;
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

    public boolean isExisted(Customer customer) throws SQLException {
        return false;
    }
    public boolean isIdCardExisted(String idCard) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(IS_CUSTOMER_IDCARD_EXISTED);
        preparedStatement.setString(1, idCard);

        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            return true;
        }
        return false;
    }


}
