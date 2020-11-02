package ru.gvg.repository;

import ru.gvg.model.User;

import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 30.09.2020
 */
public interface UserRepository {
    List<User> getAll();

    User findByEmail(String email);

    User findById(Long id);

    void addUser(User user);

    void deleteUser(Long id);
}
