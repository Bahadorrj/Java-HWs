package ir.ac.kntu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    public Profile(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public Profile() {

    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    String getPassword() {
        return this.password;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    String getEmail() {
        return this.email;
    }
    @Override
    public String toString() {
        return "Profile(" + username + ", " + password + ", " + phoneNumber + ", " + email + ")";
    }
}
