/*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 4
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Sun Mar 13 2022
 **************************************************************************************************/

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Staff Controller
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class StaffController {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    //	Constructor
    public StaffController() {
        try {
            connection = new DatabaseConnection().getConnection();
            statement = connection.createStatement();
//			statement.executeUpdate(SqlStatements.DROP_TABlE); // TODO comment out this line
            resultSet = statement.executeQuery(SqlStatements.CHECK_IF_TABLE_EXISTS);
            boolean tableIsExisted = false;
            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    tableIsExisted = true;
                }
            }
            if (!tableIsExisted) {
                statement.executeUpdate(SqlStatements.CREATE_TABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the connection
     *
     * @return Connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * get all ids from the database
     *
     * @return List<String>
     */
    public List<String> getAllIds() {
        List<String> ids = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(SqlStatements.GET_ALL_IDS);
            while (resultSet.next()) {
                ids.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    /**
     * check if the staff exists by id
     *
     * @return boolean
     */
    public boolean isStaffExisted(String id) {
        boolean staffIsExisted = false;
        try {
            preparedStatement = connection.prepareStatement(SqlStatements.CHECK_IF_STAFF_EXISTS);
            preparedStatement.setString(1, String.format("%1$-" + 9 + "s", id));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    staffIsExisted = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffIsExisted;
    }

    /**
     * get staff by id
     *
     * @return Staff
     */
    public Staff viewStaff(String id) {
        Staff staff = new Staff();
        try {
            preparedStatement = connection.prepareStatement(SqlStatements.VIEW_STAFF);
            preparedStatement.setString(1, String.format("%1$-" + 9 + "s", id));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                staff.setId(resultSet.getString("ID"));
                staff.setLastName(resultSet.getString("LASTNAME"));
                staff.setFirstName(resultSet.getString("FIRSTNAME"));
                staff.setMi(resultSet.getString("MI"));
                staff.setAddress(resultSet.getString("ADDRESS"));
                staff.setCity(resultSet.getString("CITY"));
                staff.setState(resultSet.getString("STATE"));
                staff.setTelephone(resultSet.getString("TELEPHONE"));
                staff.setEmail(resultSet.getString("EMAIL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    //	insert staff
    public void insertStaff(Staff staff) {
        try {
            preparedStatement = connection.prepareStatement(SqlStatements.INSERT_STAFF);
            preparedStatement.setString(1, staff.getId());
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setString(3, staff.getFirstName());
            preparedStatement.setString(4, staff.getMi());
            preparedStatement.setString(5, staff.getAddress());
            preparedStatement.setString(6, staff.getCity());
            preparedStatement.setString(7, staff.getState());
            preparedStatement.setString(8, staff.getTelephone());
            preparedStatement.setString(9, staff.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //	update staff
    public void updateStaff(Staff staff) {
        try {
            preparedStatement = connection.prepareStatement(SqlStatements.UPDATE_STAFF);
            preparedStatement.setString(1, staff.getLastName());
            preparedStatement.setString(2, staff.getFirstName());
            preparedStatement.setString(3, staff.getMi());
            preparedStatement.setString(4, staff.getAddress());
            preparedStatement.setString(5, staff.getCity());
            preparedStatement.setString(6, staff.getState());
            preparedStatement.setString(7, staff.getTelephone());
            preparedStatement.setString(8, staff.getEmail());
            preparedStatement.setString(9, String.format("%1$-" + 9 + "s", staff.getId()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //	close the connection
    public void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}