package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres1453";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11200";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from accounts");

        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("first_name"));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String secondName = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        String sqlInsertUser = "insert into accounts(first_name, second_name, age) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, secondName);
        preparedStatement.setInt(3, age);

        int affectedRows = preparedStatement.executeUpdate();

        System.out.println("Было добавлено " + affectedRows + " строк ");

        // Kullanıcı adı ile veritabanından kullanıcıları ara
        System.out.print("Enter the name of the user you want to search for: ");
        String arananIsim = scanner.next();
        searchUserByName(connection, arananIsim);

        // Tüm kullanıcıları veritabanından al
        getAllUsers(connection);

        // Kapat
        connection.close();
        scanner.close();
    }

    public static void searchUserByName(Connection connection, String name) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE first_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("The found user: " + resultSet.getString("first_name")
                    + " " + resultSet.getString("second_name") + ", Age: " + resultSet.getInt("age"));
        }
    }

    public static void getAllUsers(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM accounts");

        System.out.println("All users:");
        while (result.next()) {
            System.out.println(result.getString("first_name")
                    + " " + result.getString("second_name") + ", Age: " + result.getInt("age"));
        }
    }
}
