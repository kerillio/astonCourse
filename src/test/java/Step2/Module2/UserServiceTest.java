package Step2.Module2;


import Step2.Module2.DAO.UserDao;
import Step2.Module2.DAO.UserDaoImpl;
import Step2.Module2.Models.User;
import Step2.Module2.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;
    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User("Anna", "anna@example.com", 25);
        testUser.setId(1);
        userService.addUser(testUser);
    }

    // Тесты на получение пользователя

    @Test
    @DisplayName("Должен вернуть пользователя, если он найден по ID")
    void shouldReturnUserWhenFoundById() {
        // Arrange: настраиваем поведение мока
        when(userDao.findById(testUser.getId())).thenReturn(testUser);

        // Act: вызываем метод сервиса
        User result = userService.findUserById(testUser.getId());

        // Assert: проверяем результат и факт вызова DAO
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(testUser.getId());
        assertThat(result.getEmail()).isEqualTo("anna@example.com");
        verify(userDao, times(1)).findById(testUser.getId());
    }

}
