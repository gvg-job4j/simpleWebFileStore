package ru.gvg.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Valeriy Gyrievskikh
 * @since 12.08.2020
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, message = "Не меньше 3 знаков")

    @Column(name="username")
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @Size(min = 6, message = "Не меньше 6 знаков")
    private String password;
    @Transient
    private String passwordConfirm;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
