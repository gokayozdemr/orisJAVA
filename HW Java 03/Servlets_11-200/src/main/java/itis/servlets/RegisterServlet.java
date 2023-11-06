package itis.servlets;

import itis.models.User;
import itis.repository.AccountsRepository;
import itis.repository.AccountsRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres1453";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11200";

    private AccountsRepository accountsRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            accountsRepository = new AccountsRepositoryJdbcImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/register.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountName = request.getParameter("name");
        String accountSurname = request.getParameter("surname");
        Integer accountAge = Integer.valueOf(request.getParameter("age"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = User.builder()
                .nameOfUser(accountName)
                .surnameOfUser(accountSurname)
                .ageOfUser(accountAge)
                .username(username)
                .password(password)
                .build();

        try {
            accountsRepository.save(user);
            response.sendRedirect("/done");
        } catch (SQLException e) {
            response.sendRedirect("/register");
            throw new RuntimeException(e);
        }
    }
}