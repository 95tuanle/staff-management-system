/*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 4
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Sun Mar 13 2022
 **************************************************************************************************/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Connection
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class DatabaseConnection {
    Connection connection;

    //    Constructor
    public DatabaseConnection() {
        try {
            Class.forName(OracleSqlInfo.DRIVER_CLASS_ORACLE_SQL);
            this.connection = DriverManager.getConnection(OracleSqlInfo.URL, OracleSqlInfo.USER, OracleSqlInfo.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
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
}
