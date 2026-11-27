package Step2.Module2.UI;

import Step2.Module2.DTO.UserCreateDto;
import Step2.Module2.DTO.UserViewDto;
import Step2.Module2.Models.User;
import Step2.Module2.Readers.UserReader;
import Step2.Module2.Service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class CreateUserScreen implements Screen {

    @Override
    public Screen show(ConfigurableApplicationContext context) {
        UserCreateDto dto = UserReader.readByConsole();
        Optional<UserViewDto> savedUser = context.getBean("userService", UserService.class).addUser(dto);
        if (savedUser.isPresent()) {
            System.out.println("Пользователь " + savedUser.get().name() + " сохранён");
        } else {
            System.out.printf("Не удалось сохранить пользователя");
        }
        return context.getBean("mainScreen", Screen.class);
    }

}
