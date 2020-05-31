package testobjects.wssearrchobjects;

import java.util.Objects;

public class UserJsonObject {

    private final int id;
    private final String username;
    private final String realname;
    private final String password;
    private final String email;

    public UserJsonObject(int id, String username, String realname, String password, String email) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return this.id + " " + this.username + " " + this.realname + " " + this.password + " " + this.email;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof UserJsonObject)) {
            return false;
        }
        UserJsonObject user = (UserJsonObject) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(realname, user.realname) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, realname, password, email);
    }
}
