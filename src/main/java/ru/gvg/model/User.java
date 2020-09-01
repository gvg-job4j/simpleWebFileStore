package ru.gvg.model;

/**
 * @author Valeriy Gyrievskikh
 * @since 12.08.2020
 */

public class User {
    /**
     * User name;
     */
    private String name;
    /**
     * User password.
     */
    private String password;
    /**
     * User email (must be unique).
     */
    private String email;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
