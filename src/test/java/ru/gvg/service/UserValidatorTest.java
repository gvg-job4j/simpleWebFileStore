package ru.gvg.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.Errors;
import ru.gvg.config.TestConfig;
import ru.gvg.dao.UserDAO;
import ru.gvg.model.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.09.2020
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class
        , loader = AnnotationConfigContextLoader.class)
public class UserValidatorTest {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserDAO userDAO;

    private static final String userEmail = "mail1@mail.ru";
    private static final String newEmail = "new@new.ru";
    private static final User user = mock(User.class);

    @BeforeClass
    public static void setup(){
        when(user.getEmail()).thenReturn(userEmail);
    }

    @Test
    public void validateShouldAcceptUserWithNewEmail() throws Exception {
        when(userDAO.getUser(newEmail)).thenReturn(null);
        Errors errors = mock(Errors.class);
        userValidator.validate(user, errors);
        verify(errors, never()).rejectValue(eq("email"), any(), any());
    }

    @Test
    public void validateShouldNotAcceptUserWithEmail() throws Exception {
        when(userDAO.getUser(userEmail)).thenReturn(user);
        Errors errors = mock(Errors.class);
        userValidator.validate(user, errors);
        verify(errors, atLeastOnce()).rejectValue(eq("email"), any(), any());
    }

}