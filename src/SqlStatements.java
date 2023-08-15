/*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 4
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Sun Mar 13 2022
 **************************************************************************************************/

/**
 * SQL Statements
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class SqlStatements {
    public static final String CHECK_IF_TABLE_EXISTS = "select count(*) from USER_TABLES where upper(TABLE_NAME) = 'STAFF'";
    public static final String CREATE_TABLE = "create table STAFF (id char(9) not null, lastName varchar(15), firstName varchar(15), mi char(1), address varchar(20), city varchar(20), state char(2), telephone char(10), email varchar(40), primary key (id))";
    public static final String GET_ALL_IDS = "select ID from STAFF";
    public static final String CHECK_IF_STAFF_EXISTS = "select count(*) from STAFF where ID = ?";
    public static final String VIEW_STAFF = "select * from STAFF where ID = ?";
    public static final String INSERT_STAFF = "insert into STAFF values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_STAFF = "update STAFF set LASTNAME = ?, FIRSTNAME = ?, MI = ?, ADDRESS = ?, CITY = ?, STATE = ?, TELEPHONE = ?, EMAIL = ? where ID = ?";
    public static final String DROP_TABlE = "drop table STAFF purge";
}