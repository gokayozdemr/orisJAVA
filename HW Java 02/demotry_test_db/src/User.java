public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String secondName;
    private int age;

    public User(int id, String email, String password, String name, String secondName, int age) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.secondName = secondName;
        this.age = age;
    }

    // Getter ve setter metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
