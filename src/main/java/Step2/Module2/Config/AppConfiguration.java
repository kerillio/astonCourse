package Step2.Module2.Config;

import Step2.Module2.DAO.UserDao;
import Step2.Module2.DAO.UserDaoImpl;
import Step2.Module2.Models.User;
import Step2.Module2.Readers.UserReader;
import Step2.Module2.Service.UserService;
import Step2.Module2.UI.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean ("userDaoImpl")
    public UserDao<User> UserDaoImpl () {
        return new UserDaoImpl();
    }

    @Bean ("user")
    public User User () {
        return new User();
    }

    @Bean ("userReader")
    public UserReader UserReader() {
        return new UserReader();
    }


    @Bean ("createUserScreen")
    public Screen CreateUserScreen() {
        return new CreateUserScreen();
    }

    @Bean ("exitScreen")
    public Screen ExitScreen() {
        return new ExitScreen();
    }

    @Bean ("mainScreen")
    public Screen MainScreen() {
        return new MainScreen();
    }

    @Bean ("removeUserScreen")
    public Screen RemoveUserScreen() {
        return new RemoveUserScreen();
    }

    @Bean ("userFindScreen")
    public Screen UserFindScreen() {
        return new UserFindScreen();
    }

    @Bean ("userInfoUpdateScreen")
    public Screen UserInfoUpdateScreen() {
        return new UserInfoUpdateScreen();
    }
}
