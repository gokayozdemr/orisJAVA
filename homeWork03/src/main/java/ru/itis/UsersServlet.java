package ru.itis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres1453";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11200";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<User> users = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM users";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String secondName = resultSet.getString("second_name");
                    String email = resultSet.getString("email");
                    users.add(new User(firstName, secondName, email));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Kullanıcıları JSON formatında döndürüyoruz
        PrintWriter out = response.getWriter();
        out.print("[");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            out.print("{\"firstName\":\"" + user.getFirstName() + "\",\"secondName\":\"" + user.getSecondName() + "\",\"email\":\"" + user.getEmail() + "\"}");
            if (i < users.size() - 1) {
                out.print(",");
            }
        }
        out.print("]");
        out.flush();
    }
}
