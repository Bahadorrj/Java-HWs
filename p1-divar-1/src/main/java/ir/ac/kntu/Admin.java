package ir.ac.kntu;

import java.util.Scanner;

public class Admin {
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
