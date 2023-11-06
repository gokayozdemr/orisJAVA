package itis.repository;

import itis.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountsRepositoryJdbcImpl implements AccountsRepository {

    private final Connection connection;

    private static final String SQL_INSERT = "insert into accounts(first_name, second_name, age, username, password) values ";
    private static final String SQL_FIND_BY_USERNAME_AND_PASSWORD = "select * from accounts where username = ? and password = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM accounts";

    public AccountsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void save(User user) throws SQLException {
        String sql = SQL_INSERT + "(?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getNameOfUser());
        preparedStatement.setString(2, user.getSurnameOfUser());
        preparedStatement.setInt(3, user.getAgeOfUser());
        preparedStatement.setString(4, user.getUsername());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_USERNAME_AND_PASSWORD);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return User.builder()
                    .id(resultSet.getLong("id"))
                    .nameOfUser(resultSet.getString("first_name"))
                    .surnameOfUser(resultSet.getString("second_name"))
                    .ageOfUser(resultSet.getInt("age"))
                    .username(resultSet.getString("username"))
                    .password(resultSet.getString("password"))
                    .build();
        } else {
            return null;
        }
    }
    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user = User.builder()
                    .id(resultSet.getLong("id"))
                    .nameOfUser(resultSet.getString("first_name"))
                    .surnameOfUser(resultSet.getString("second_name"))
                    .username(resultSet.getString("username"))
                    .build();
            users.add(user);
        }

        return users;
    }
}