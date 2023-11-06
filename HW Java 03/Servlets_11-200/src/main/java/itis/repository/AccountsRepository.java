package itis.repository;

import itis.models.User;

import java.sql.SQLException;
import java.util.List;

public interface AccountsRepository {
    void save(User user) throws SQLException;
    User findByUsernameAndPassword(String username, String password) throws SQLException;
    List<User> getAllUsers() throws SQLException;
}