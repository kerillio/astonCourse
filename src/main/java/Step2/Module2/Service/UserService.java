package Step2.Module2.Service;

import Step2.Module2.DTO.UserCreateDto;
import Step2.Module2.DTO.UserUpdateDto;
import Step2.Module2.DTO.UserViewDto;
import Step2.Module2.Models.User;
import Step2.Module2.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    public User getUser() {
//        return new User();
//    }

    @Transactional
    public Optional<UserViewDto> addUser(UserCreateDto dto) {
        if (dto.name().isEmpty()) {
            log.warn("user.name is empty");
        } else if (dto.age() < 1) {
            log.warn("Age is incorrect");
        } else if (dto.email().isEmpty() || !dto.email().contains("@")) {
            log.warn("Email is incorrect");
        } else {
            User savedUser = dto.toEntity();
            userRepository.save(savedUser);
            return Optional.of(UserViewDto.fromEntity(savedUser));
        }
        return Optional.empty();

    }

    @Transactional
    public Optional<UserViewDto> updateUser (UserUpdateDto dto) {
        if (dto.name().isEmpty()) {
            log.warn("Name is empty");
        } else if (dto.age() < 0) {
            log.warn("Age is incorrect");
        } else if (dto.email().isEmpty() || !dto.email().contains("@")) {
            log.warn("Email is incorrect");
        } else {
            Optional<User> existingUser = userRepository.findById(dto.id().longValue());
            if (existingUser.isPresent()) {
                dto.applyTo(existingUser.get());
                return Optional.of(UserViewDto.fromEntity(userRepository.save(existingUser.get())));
            }
        }
        return Optional.empty();
    }

    @Transactional
    public void removeUserById(Long id) {
        if (id > 0) {
            userRepository.deleteById(id);
        }
    }

    public List<UserViewDto> findAll() {
        return userRepository.findAll().stream().map(UserViewDto::fromEntity).toList();
    }

    public UserViewDto findUserById(Long id) {
        if (id > 0) {
            Optional<User> foundUser = userRepository.findById(id);
            return foundUser.map(UserViewDto::fromEntity).orElse(null);
        } else return null;
    }

    public List<UserViewDto> findUserByName(String name) {
        if (name.isEmpty()) {
            log.warn("name is empty");
            return null;
        } else return userRepository.findByName(name).stream().map(UserViewDto::fromEntity).toList();
    }

    public List<UserViewDto> findUserByEmail(String email) {
        if (email.isEmpty()) {
            log.warn("email is empty");
            return null;
        } else return userRepository.findByEmail(email).stream().map(UserViewDto::fromEntity).toList();
    }

    public List<UserViewDto> findUsersByAge(int age) {
        if (age < 1) {
            log.warn("Age is incorrect");
            return null;
        } else return userRepository.findByAge(age).stream().map(UserViewDto::fromEntity).toList();
    }

}
