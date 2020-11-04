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
import ru.gvg.model.User;

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
    private UserService userService;

    private static final String userEmail = "mail1@mail.ru";
    private static final User user = mock(User.class);

    @BeforeClass
    public static void setup() {
        when(user.getEmail()).thenReturn(userEmail);
    }

    @Test
    public void validateShouldAcceptUserWithNewEmail() throws Exception {
        when(userService.findUserByEmail(userEmail)).thenReturn(null);
        Errors errors = mock(Errors.class);
        userValidator.validate(user, errors);
        verify(errors, never()).rejectValue(eq("email"), any(), any());
    }

    @Test
    public void validateShouldNotAcceptUserWithEmail() throws Exception {
        when(userService.findUserByEmail(userEmail)).thenReturn(user);
        Errors errors = mock(Errors.class);
        userValidator.validate(user, errors);
        verify(errors, atLeastOnce()).rejectValue(eq("email"), any(), any());
    }

}