package voiceout.ryme.Helper;

public class CurrentSession {

    private static CurrentSession instance;
    private int userId;
    private String username;
    private String password;
    private String contactNumber;
    private String gender;
    private int age;
    private String email;
    private String address;
    private String name;
    private String creationDate;

    private CurrentSession() {
    }

    public static synchronized CurrentSession getInstance() {
        if (instance == null) {
            instance = new CurrentSession();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } // Consider storing hashed passwords

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void clearSession() {
        userId = 0;
        username = null;
        password = null;
        contactNumber = null;
        gender = null;
        age = 0;
        email = null;
        address = null;
        name = null;
        creationDate = null;
    }
}
