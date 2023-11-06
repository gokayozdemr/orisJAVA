import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsRepositoryJdbcImpl implements AccountsRepository {

    private Connection connection;
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM demotestaccounts";
    private static final String SQL_SELECT_USERS_BY_NAME = "SELECT * FROM demotestaccounts WHERE name = ?";
    private static final String SQL_SELECT_USERS_BY_AGE = "SELECT * FROM demotestaccounts WHERE age = ?";

    public AccountsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
        while (resultSet.next()) {
            User user = extractUserFromResultSet(resultSet);
            users.add(user);
        }
        return users;
    }

    @Override
    public List<User> getUsersByName(String name) throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USERS_BY_NAME);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = extractUserFromResultSet(resultSet);
            users.add(user);
        }
        return users;
    }

    @Override
    public List<User> getUsersByAge(int age) throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USERS_BY_AGE);
        preparedStatement.setInt(1, age);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = extractUserFromResultSet(resultSet);
            users.add(user);
        }
        return users;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        String secondName = resultSet.getString("secondname");
        int age = resultSet.getInt("age");

        return new User(id, email, password, name, secondName, age);
    }


}
