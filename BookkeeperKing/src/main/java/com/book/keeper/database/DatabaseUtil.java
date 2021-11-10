package com.book.keeper.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private static volatile Connection connection = null;
    public static final String url = "jdbc:mysql://bkk.c4jwotegah6d.us-east-2.rds.amazonaws.com:3306/BK_main";
    private static final String userName = "admin";
    private static final String password = "Kingzone";

    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     *
     * Singleton method for getting Connection object
     * Singleton : there will be only one instance of Connection class all over the application
     */
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            synchronized (DatabaseUtil.class) {
                if(connection == null) {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection( url, userName, password);
                }
            }
        }
        return connection;
    }


    public static double findFederalIncomeTax(String tableName, double gross, int allowances) {
        String query = "select D1 from "+tableName + " where at_least <="+ gross + " and but_less_than >"+gross;
        Double result = 0d;

        try {
            Connection connection = getConnection();
            Statement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                result = rs.getDouble(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static double findAmountFromTableByQuery(String query) {
        Double result = 0d;

        try {
            Connection connection = getConnection();
            Statement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                result = rs.getDouble(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Double> findFieldsFromTaxRate(String query) {
        List<Double> resultList = new ArrayList<>();

        try {
            Connection connection = getConnection();
            Statement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {

                resultList.add(rs.getDouble(1));
                resultList.add(rs.getDouble(2));
                resultList.add(rs.getDouble(3));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
