package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JDBC {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static  String url = "jdbc:mysql://localhost:3306/passwrodmanager";
    private static String username = "root";
    private static String password = "***";

    public static Connection getConnection() {
        // register the JDBC driver
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // create a connection
        Connection connection = null;
        try {
            connection = DriverManager.getConnection (url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();;
        }
        return connection;
    }

    public static void close(Connection connection) throws SQLException
    {
        try
        {
            connection.close();
        } catch (SQLException e)
        {
            throw e;
        }
    }


    public static List<PasswordInformation> queryLoginFromDB(){
        ArrayList<PasswordInformation> arr = new ArrayList<>();
        String query = "select * from login";
        Connection con = JDBC.getConnection();
        Statement statement = null; // statement to query
        try {
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(query); // perform the query and get the result
            while ( result.next()){
                PasswordInformation pw = new PasswordInformation();
                pw.setTitle(result.getString("title"));
                pw.setUsername(result.getString("username"));
                pw.setPassword((result.getString("password")));
                arr.add(pw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static List<CreditCardInformation> queryCreditCardFromDB(){
        ArrayList<CreditCardInformation> arr = new ArrayList<>();
        String query = "select * from creditcard";
        Connection con = JDBC.getConnection();
        Statement statement = null; // statement to query
        try {
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(query); // perform the query and get the result
            while ( result.next()){
                CreditCardInformation card = new CreditCardInformation();
                card.setTitle(result.getString("title"));
                card.setCardName(result.getString("holder"));
                card.setCardNumber(Integer.parseInt(result.getString("number")));
                card.setExprDate(result.getString("expiration"));
                card.setCardCVV(Integer.parseInt(result.getString("cvv")));
                card.setCardPIN(Integer.parseInt(result.getString("pin")));
                card.setCardZip(result.getString("zipcode"));
                arr.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static void insertToLogin (PasswordInformation pw) throws SQLException
    {
        // get the connection
        Connection connection = JDBC.getConnection();
        // create the INSERT SQL
        String sql = "INSERT into login (title, username, password) VALUES(?,?,?)";
        // create the statement
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,pw.getTitle());
        statement.setString(2,pw.getUsername());
        statement.setString(3,pw.getPassword());
        try
        {
            // Insert the data
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            statement.close();
            JDBC.close(connection);
        }
    }

    public static void insertToCreditCard (CreditCardInformation card) throws SQLException
    {
        // get the connection
        Connection connection = JDBC.getConnection();
        // create the INSERT SQL
        String sql = "INSERT into creditcard (title, holder, number, expiration, cvv, pin, zipcode) VALUES(?,?,?,?,?,?,?)";
        // create the statement
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,card.getTitle());
        statement.setString(2,card.getCardName());
        statement.setString(3,String.valueOf(card.getCardNumber()));
        statement.setString(4,card.getExprDate());
        statement.setString(5,String.valueOf(card.getCardCVV()));
        statement.setString(6,String.valueOf(card.getCardPIN()));
        statement.setString(7,String.valueOf(card.getCardZip()));
        try
        {
            // Insert the data
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            statement.close();
            JDBC.close(connection);
        }
    }



    public static void main(String []arg) throws SQLException{
        List<CreditCardInformation> list = JDBC.queryCreditCardFromDB();

       for(CreditCardInformation card : list) {
           System.out.println(card.toString());
       }
    }

}
