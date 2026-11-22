package Step2.Module2;


import Step2.Module2.DTO.UserViewDto;
import Step2.Module2.Models.User;
import Step2.Module2.Repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class RepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private User user;

    @BeforeEach
    void cleanDatabase() {
        // Очищаем таблицу перед каждым тестом для независимости
        userRepository.deleteAll();
    }


    @Test
    void invalidIdException() {
        //setup
        User user = new User();
        user.setName("Ivan");
        user.setAge(26);
        user.setEmail("Ivan@example.com");

        //add to DB
        UserViewDto existingUserDto = UserViewDto.fromEntity(userRepository.save(user));

        //assert exception
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> userRepository.findById(null));
    }

    @Test
    void nullNameException() {
        //setup
        User user = new User();
        user.setName("Ivan");
        user.setAge(26);
        user.setEmail("Ivan@example.com");

        //add to DB
        UserViewDto existingUserDto = UserViewDto.fromEntity(userRepository.save(user));

        //assert exception
        List<User> users = userRepository.findByName(null);
        Assertions.assertEquals(List.of(), users);
    }

    @Test
    void emptyNameException () {
        //setup
        User user = new User();
        user.setName("Ivan");
        user.setAge(26);
        user.setEmail("Ivan@example.com");

        //add to DB
        UserViewDto existingUserDto = UserViewDto.fromEntity(userRepository.save(user));

        //assert exception
        List<User> users = userRepository.findByName("");
        Assertions.assertEquals(List.of(), users);
    }

    @Transactional
    @Test
    void createAndFindUser() {
        //setup
        User user = new User();
        user.setName("Ivan");
        user.setAge(26);
        user.setEmail("Ivan@example.com");

        //add to DB
        UserViewDto existingUserDto = UserViewDto.fromEntity(userRepository.save(user));

        //find in DB
        UserViewDto savedUserDto = UserViewDto.fromEntity(userRepository.findById(existingUserDto.id()).get());

        //Asserting
        Assertions.assertEquals(existingUserDto, savedUserDto);

    }

    @Transactional
    @Test
    void createAndUpdateName() {
        //setup
        User user = new User();
        user.setName("Ivan");
        user.setAge(26);
        user.setEmail("Ivan@example.com");

        //add to DB
        UserViewDto oldUserDto = UserViewDto.fromEntity(userRepository.save(user));

        //update name
        user.setName("alex");

        //save updated name
        UserViewDto newUserDto = UserViewDto.fromEntity(userRepository.save(user));

        //find actual user
        UserViewDto savedUserDto = UserViewDto.fromEntity(userRepository.findById(user.getId()).get());

        //Asserting
        Assertions.assertNotEquals(oldUserDto, savedUserDto);
        Assertions.assertEquals(newUserDto, savedUserDto);
    }

    @Transactional
    @Test
    void createAndUpdateEmail() {
        //setup
        User user = new User();
        user.setName("Ivan");
        user.setAge(26);
        user.setEmail("Ivan@example.com");

        //add to DB
        UserViewDto oldUserDto = UserViewDto.fromEntity(userRepository.save(user));

        //update name
        user.setEmail("alex@example.com");

        //save updated name
        UserViewDto newUserDto = UserViewDto.fromEntity(userRepository.save(user));

        //find actual user
        UserViewDto savedUserDto = UserViewDto.fromEntity(userRepository.findById(user.getId()).get());

        //Asserting
        Assertions.assertNotEquals(oldUserDto, savedUserDto);
        Assertions.assertEquals(newUserDto, savedUserDto);
    }

    @Transactional
    @Test
    void createAndDeleteUser() {
        //setup
        User user = new User();
        user.setName("Ivan");
        user.setAge(26);
        user.setEmail("Ivan@example.com");

        //add to DB
        UserViewDto existingUserDto = UserViewDto.fromEntity(userRepository.save(user));

        //find in DB
        UserViewDto beforeDeletindDto = UserViewDto.fromEntity(userRepository.findById(existingUserDto.id()).get());

        //delete user
        userRepository.deleteById(user.getId());

        //try to find
        Optional<User> optionalUser = userRepository.findById(user.getId());

        //Asserting
        Assertions.assertEquals(existingUserDto, beforeDeletindDto);
        Assertions.assertTrue(optionalUser.isEmpty());

    }

    @Transactional
    @Test
    void createAndFindManyByName() {
        //setup
        User user1 = new User();
        user1.setName("Ivan");
        user1.setAge(26);
        user1.setEmail("Ivan1@example.com");

        User user2 = new User();
        user2.setName("Ivan");
        user2.setAge(105);
        user2.setEmail("Ivan2@example.com");

        User user3 = new User();
        user3.setName("Ivan");
        user3.setAge(31);
        user3.setEmail("Ivan3@example.com");

        User user4 = new User();
        user4.setName("German");
        user4.setAge(31);
        user4.setEmail("German@example.com");

        //save to DB
        UserViewDto user1Dto = UserViewDto.fromEntity(userRepository.save(user1));
        UserViewDto user2Dto = UserViewDto.fromEntity(userRepository.save(user2));
        UserViewDto user3Dto = UserViewDto.fromEntity(userRepository.save(user3));

        //assert
        List<UserViewDto> existingUsersDtoList = List.of(user1Dto, user2Dto, user3Dto);
        List<UserViewDto> foundUsersDtoList = userRepository.findByName("Ivan").stream().map(UserViewDto::fromEntity).toList();

        Assertions.assertEquals(existingUsersDtoList, foundUsersDtoList);

    }

    @Transactional
    @Test
    void createAndFindManyByAge() {
        //setup
        User user1 = new User();
        user1.setName("Alex");
        user1.setAge(26);
        user1.setEmail("Alex@example.com");

        User user2 = new User();
        user2.setName("Ivan");
        user2.setAge(26);
        user2.setEmail("Ivan@example.com");

        User user3 = new User();
        user3.setName("Kirill");
        user3.setAge(26);
        user3.setEmail("Kirill@example.com");

        User user4 = new User();
        user4.setName("German");
        user4.setAge(105);
        user4.setEmail("German@example.com");

        //save to DB
        UserViewDto user1Dto = UserViewDto.fromEntity(userRepository.save(user1));
        UserViewDto user2Dto = UserViewDto.fromEntity(userRepository.save(user2));
        UserViewDto user3Dto = UserViewDto.fromEntity(userRepository.save(user3));
        userRepository.save(user4);

        //assert
        List<UserViewDto> existingUsersDtoList = List.of(user1Dto, user2Dto, user3Dto);
        List<UserViewDto> foundUsersDtoList = userRepository.findByAge(26).stream().map(UserViewDto::fromEntity).toList();

        Assertions.assertEquals(existingUsersDtoList, foundUsersDtoList);

    }




}
