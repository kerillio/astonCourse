package Step2.Module2.UI;

import Step2.Module2.Models.User;
import Step2.Module2.Readers.UserReader;
import Step2.Module2.Service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

public class CreateUserScreen implements Screen {

    @Override
    public Screen show(AnnotationConfigApplicationContext context) {
        User user = UserReader.readByConsole();
        context.getBean("userService", UserService.class).addUser(user);
        return context.getBean("mainScreen", Screen.class);
    }

}
