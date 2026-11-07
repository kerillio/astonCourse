package Step2.Module2.UI;

import Step2.Module2.Models.User;
import Step2.Module2.Readers.UserReader;
import Step2.Module2.Service.UserService;

public class CreateUserScreen implements Screen {

    @Override
    public Screen show() {
        User user = UserReader.readByConsole();
        UserService.addUser(user);
        return new MainScreen();
    }

}
