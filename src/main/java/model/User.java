package model;

public class User {
    private static User instance;
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String document;
    private int type;

    private User() {}

    public static User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;
    }

    public void cleanUserSession() {
        id = 0;
        name = "";
        email = "";
        password = "";
        phone = "";
        document = "";
        type = 0;
    }

    public static void setInstance(User instance) { User.instance = instance; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDocument(String document) { this.document = document; }
    public void setType(int type) { this.type = type; }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getDocument() { return document; }
    public int getType() { return type; }
}
