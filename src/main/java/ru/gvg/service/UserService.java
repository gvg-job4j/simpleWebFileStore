package ru.gvg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gvg.model.User;
import ru.gvg.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 30.09.2020
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> allUsers() {
        return userRepository.getAll();
    }

    @Transactional
    public User findUserById(Long userId) {
        User userFromDb = userRepository.findById(userId);
        return userFromDb;
    }

    @Transactional
    public User findUserByEmail(String email) {
        User userFromDb = userRepository.findByEmail(email);
        return userFromDb;
    }

    @Transactional
    public boolean saveUser(User user) {
        boolean saved = false;
        User userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB == null) {
            userRepository.addUser(user);
            saved = true;
        }
        return saved;
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        boolean deleted = false;
        if (userRepository.findById(userId) != null) {
            userRepository.deleteUser(userId);
            deleted = true;
        }
        return deleted;
    }
}
