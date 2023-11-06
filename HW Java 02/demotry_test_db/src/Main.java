import java.sql.*;
import java.util.Scanner;
import java.util.List;



public class Main {

    private static final String DB_USERNAME ="postgres";
    private static final String DB_PASSWORD ="postgres1453";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/newtestdemotry";

    public static void main(String[] args) throws Exception{
        Connection connection =DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement =connection.createStatement();
        ResultSet result = statement.executeQuery("select  * from demotestaccounts");

        System.out.println(" Kullanıcılar : ");
        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("name") + " " + result.getString("secondname"));
        }

        System.out.println("Lütfen Yeni Kullanıcıyı ekleyin: email, şifre, isim, soyisim ve yaş: ");
        Scanner scanner =new Scanner(System.in);
        String email = scanner.nextLine();
        String password = scanner.nextLine();
        String name = scanner.nextLine();
        String secondname = scanner.nextLine();
        int age = scanner.nextInt();

        String sqlInsert = "insert into demotestaccounts(email,password,name,secondname,age)" +
                "values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,name);
        preparedStatement.setString(4,secondname);
        preparedStatement.setInt(5,age);
        preparedStatement.executeUpdate();
        System.out.println("Başarılı");

        AccountsRepository repository = new AccountsRepositoryJdbcImpl(connection);

        System.out.println("Lütfen aramak istediğiniz yaşı girin: ");
        int searchAge = scanner.nextInt();
        List<User> usersByAge = repository.getUsersByAge(searchAge);
        System.out.println("Yaşa göre arama sonuçları: ");
        for (User user : usersByAge) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getSecondName() + " " + user.getAge());
        }

        System.out.println("Lütfen aramak istediğiniz ismi girin: ");
        scanner.nextLine(); // Bir sonraki girdi için boş satırı oku
        String searchName = scanner.nextLine();
        List<User> usersByName = repository.getUsersByName(searchName);
        System.out.println("İsme göre arama sonuçları: ");
        for (User user : usersByName) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getSecondName() + " " + user.getAge());
        }


    }


}