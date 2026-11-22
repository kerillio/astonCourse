package Step2.Module2;

import Step2.Module2.DTO.UserCreateDto;
import Step2.Module2.DTO.UserUpdateDto;
import Step2.Module2.DTO.UserViewDto;
import Step2.Module2.Models.User;
import Step2.Module2.Repository.UserRepository;
import Step2.Module2.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void add_ShouldCreateAndReturnDto() {
        //Подготовка данных
        UserCreateDto newUserDto = new UserCreateDto("ivan", "ivan@example.com", 26);
        User newUser = newUserDto.toEntity();
        UserViewDto expectedDto = UserViewDto.fromEntity(newUser);

        //мок
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(newUser);

        //Сохраняем пользователя
        UserViewDto savedDto = userService.addUser(newUserDto).get();

        //Проверка
        Assertions.assertEquals(expectedDto, savedDto, "got - " + expectedDto.toString() + " : " + savedDto.toString());
    }

    @Test
    void add_emptyName_ReturnEmptyOptional() {
        //Подготовка данных
        UserCreateDto newUserDto = new UserCreateDto("", "ivan@example.com", 26);

        //Пытаемся сохранить пользователя
        Optional<UserViewDto> savedDto = userService.addUser(newUserDto);

        //Проверка
        Assertions.assertEquals(Optional.empty(), savedDto);
        Mockito.verifyNoInteractions(userRepository);

    }

    @Test
    void add_emptyEmail_ReturnEmptyOptional() {
        //Подготовка данных
        UserCreateDto newUserDto = new UserCreateDto("Ivan", "", 26);

        //Пытаемся сохранить пользователя
        Optional<UserViewDto> savedDto = userService.addUser(newUserDto);

        //Проверка
        Assertions.assertEquals(Optional.empty(), savedDto);
        Mockito.verifyNoInteractions(userRepository);
    }

    @Test
    void add_incorrectEmail_ReturnEmptyOptional() {
        //Подготовка данных
        UserCreateDto newUserDto = new UserCreateDto("Ivan", "ivanexample.com", 26);

        //Пытаемся сохранить пользователя
        Optional<UserViewDto> savedDto = userService.addUser(newUserDto);

        //Проверка
        Assertions.assertEquals(Optional.empty(), savedDto);
        Mockito.verifyNoInteractions(userRepository);
    }

    @Test
    void add_incorrectAge_ReturnEmptyOptional() {
        //Подготовка данных
        UserCreateDto newUserDto = new UserCreateDto("Ivan", "ivanexample.com", -1);

        //Пытаемся сохранить пользователя
        Optional<UserViewDto> savedDto = userService.addUser(newUserDto);

        //Проверка
        Assertions.assertEquals(Optional.empty(), savedDto);
        Mockito.verifyNoInteractions(userRepository);
    }


    @Test
    void update_UpdateNameReturnNewDto() {
        //setUp
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Ivan");
        existingUser.setAge(26);
        existingUser.setEmail("Ivan@example.com");
        UserViewDto oldUserDto = UserViewDto.fromEntity(existingUser);

        User expectedUser = new User(existingUser);
        expectedUser.setName("Alex");
        UserViewDto newUserDto = UserViewDto.fromEntity(expectedUser);

        //Mock
        Mockito.when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(existingUser)).thenAnswer(invocation -> invocation.getArgument(0));

        //Обновляем пользователя
        UserViewDto savedDto = userService.updateUser(new UserUpdateDto(
                existingUser.getId(),
                "Alex",
                existingUser.getEmail(),
                existingUser.getAge(),
                existingUser.getCreated_at()
        )).get();

        //проверка значений
        Assertions.assertEquals(newUserDto, savedDto);
        Assertions.assertNotEquals(oldUserDto, savedDto);

    }

    @Test
    void update_UpdateEmailReturnNewDto() {
        //setUp
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Ivan");
        existingUser.setAge(26);
        existingUser.setEmail("Ivan@example.com");
        UserViewDto oldUserDto = UserViewDto.fromEntity(existingUser);

        User expectedUser = new User(existingUser);
        expectedUser.setEmail("Alex@email.com");
        UserViewDto newUserDto = UserViewDto.fromEntity(expectedUser);

        //Mock
        Mockito.when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(existingUser)).thenAnswer(invocation -> invocation.getArgument(0));

        //Обновляем пользователя
        UserViewDto savedDto = userService.updateUser(new UserUpdateDto(
                existingUser.getId(),
                existingUser.getName(),
                "Alex@email.com",
                existingUser.getAge(),
                existingUser.getCreated_at()
        )).get();

        //проверка значений
        Assertions.assertEquals(newUserDto, savedDto);
        Assertions.assertNotEquals(oldUserDto, savedDto);

    }

    @Test
    void update_EmptyNameException() {
        //setUp
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Ivan");
        existingUser.setAge(26);
        existingUser.setEmail("Ivan@example.com");

        //Обновляем пользователя
        Optional<UserViewDto> savedDto = userService.updateUser(new UserUpdateDto(
                existingUser.getId(),
                "",
                existingUser.getEmail(),
                existingUser.getAge(),
                existingUser.getCreated_at()
        ));

        //проверка значений
        Assertions.assertEquals(Optional.empty(), savedDto);
        Mockito.verifyNoInteractions(userRepository);

    }

    @Test
    void update_EmptyEmailException() {
        //setUp
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Ivan");
        existingUser.setAge(26);
        existingUser.setEmail("Ivan@example.com");

        //Обновляем пользователя
        Optional<UserViewDto> savedDto = userService.updateUser(new UserUpdateDto(
                existingUser.getId(),
                existingUser.getName(),
                "",
                existingUser.getAge(),
                existingUser.getCreated_at()
        ));

        //проверка значений
        Assertions.assertEquals(Optional.empty(), savedDto);
        Mockito.verifyNoInteractions(userRepository);

    }

    @Test
    void update_InvalidEmailException() {
        //setUp
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Ivan");
        existingUser.setAge(26);
        existingUser.setEmail("Ivan@example.com");

        //Обновляем пользователя
        Optional<UserViewDto> savedDto = userService.updateUser(new UserUpdateDto(
                existingUser.getId(),
                existingUser.getName(),
                "Ivanexample.com",
                existingUser.getAge(),
                existingUser.getCreated_at()
        ));

        //проверка значений
        Assertions.assertEquals(Optional.empty(), savedDto);
        Mockito.verifyNoInteractions(userRepository);

    }

    @Test
    void update_InvalidAgeException() {
        //setUp
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Ivan");
        existingUser.setAge(26);
        existingUser.setEmail("Ivan@example.com");

        //Обновляем пользователя
        Optional<UserViewDto> savedDto = userService.updateUser(new UserUpdateDto(
                existingUser.getId(),
                existingUser.getName(),
                existingUser.getEmail(),
                -1,
                existingUser.getCreated_at()
        ));

        //проверка значений
        Assertions.assertEquals(Optional.empty(), savedDto);
        Mockito.verifyNoInteractions(userRepository);
    }

    @Test
    void find_findAll () {
        //setup
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Ivan");
        user1.setEmail("Ivan@example.com");
        user1.setAge(26);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Alex");
        user2.setEmail("Alex@example.com");
        user2.setAge(19);

        List<UserViewDto> expectedList = List.of(UserViewDto.fromEntity(user1), UserViewDto.fromEntity(user2));

        //Mock
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        //Assert
        List<UserViewDto> finalResult = userService.findAll();
        Assertions.assertEquals(expectedList, finalResult);
    }

    @Test
    void find_findByName() {
        //setup
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Ivan");
        user1.setEmail("Ivan@example.com");
        user1.setAge(26);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Ivan");
        user2.setEmail("Alex@example.com");
        user2.setAge(19);

        List<UserViewDto> expectedList = List.of(UserViewDto.fromEntity(user1), UserViewDto.fromEntity(user2));

        //Mock
        Mockito.when(userRepository.findByName("Ivan")).thenReturn(List.of(user1, user2));

        //Assert
        List<UserViewDto> finalResult = userService.findUserByName("Ivan");
        Assertions.assertEquals(expectedList, finalResult);
    }

    @Test
    void find_InvalidNameException() {
        //Assert
        List<UserViewDto> finalResult = userService.findUserByName("");
        Assertions.assertNull(finalResult);
        Mockito.verifyNoInteractions(userRepository);
    }

    @Test
    void find_findById() {
        //setup
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Ivan");
        user1.setEmail("Ivan@example.com");
        user1.setAge(26);

        UserViewDto expectedUser = UserViewDto.fromEntity(user1);


        //Mock
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        //Assert
        UserViewDto finalResult = userService.findUserById(1L);
        Assertions.assertEquals(expectedUser, finalResult);
    }

    @Test
    void find_InvalidIdException() {
        //Assert
        UserViewDto finalResult = userService.findUserById(-1L);
        Assertions.assertNull(finalResult);
        Mockito.verifyNoInteractions(userRepository);
    }


    @Test
    void find_findByEmail() {
        //setup
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Ivan");
        user1.setEmail("Alex@example.com");
        user1.setAge(26);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Ivan");
        user2.setEmail("Alex@example.com");
        user2.setAge(19);

        List<UserViewDto> expectedList = List.of(UserViewDto.fromEntity(user1), UserViewDto.fromEntity(user2));


        //Mock
        Mockito.when(userRepository.findByEmail("Alex@example.com")).thenReturn(List.of(user1, user2));

        //Assert
        List<UserViewDto> finalResult = userService.findUserByEmail("Alex@example.com");
        Assertions.assertEquals(expectedList, finalResult);
    }

    @Test
    void find_InvalidEmailException() {
        //Assert
        List<UserViewDto> finalResult = userService.findUserByEmail("");
        Assertions.assertNull(finalResult);
        Mockito.verifyNoInteractions(userRepository);
    }

    @Test
    void find_IncorrectEmailException() {
        //Assert
        List<UserViewDto> finalResult = userService.findUserByEmail("ivanexample.com");
        Assertions.assertNull(finalResult);
        Mockito.verifyNoInteractions(userRepository);
    }

    @Test
    void find_findByAge() {
        //setup
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Ivan");
        user1.setEmail("Ivan@example.com");
        user1.setAge(26);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Alex");
        user2.setEmail("Alex@example.com");
        user2.setAge(26);

        List<UserViewDto> expectedList = List.of(UserViewDto.fromEntity(user1), UserViewDto.fromEntity(user2));


        //Mock
        Mockito.when(userRepository.findByAge(26)).thenReturn(List.of(user1, user2));

        //Assert
        List<UserViewDto> finalResult = userService.findUserByAge(26);
        Assertions.assertEquals(expectedList, finalResult);
    }

    @Test
    void find_InvalidEageException() {
        //Assert
        List<UserViewDto> finalResult = userService.findUserByAge(0);
        Assertions.assertNull(finalResult);
        Mockito.verifyNoInteractions(userRepository);
    }

    @Test
    void remove_InvalidIdException() {
        //Assert
        userService.removeUserById(0L);
        Mockito.verifyNoInteractions(userRepository);
    }

    @Test
    void remove_NullPointerException() {
        //Assert
        userService.removeUserById(null);
        Mockito.verifyNoInteractions(userRepository);
    }

}
