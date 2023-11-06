import java.sql.SQLException;
import java.util.List;

public interface AccountsRepository {
    List<User> getAllUsers() throws SQLException;

    List<User> getUsersByName(String name) throws SQLException;

    List<User> getUsersByAge(int age) throws SQLException;
}