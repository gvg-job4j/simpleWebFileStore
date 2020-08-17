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
     * User passworc.
     */
    private String password;
    /**
     * User email (must be unique).
     */
    private String email;

    public User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
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
}
