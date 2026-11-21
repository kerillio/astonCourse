package Step2.Module2.UI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface Screen {
    public Screen show(ConfigurableApplicationContext context);
}
