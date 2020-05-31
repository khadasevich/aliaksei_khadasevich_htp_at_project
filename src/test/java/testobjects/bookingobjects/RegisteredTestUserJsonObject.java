package testobjects.bookingobjects;

public class RegisteredTestUserJsonObject {

    private final String email;
    private final String password;


    public RegisteredTestUserJsonObject(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
