import model.Role;
import model.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.UserRepository;
import repository.jdbc.JDBCUserRepository;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        UserRepository userRepository;
//                new JDBCUserRepository();

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));

        userRepository = context.getBean(UserRepository.class);
        List<User> users = userRepository.getAll();
        context.close();
        users.forEach(System.out::println);

        /*System.out.println(userRepository.delete(16));*/
        /*userRepository.save(new User("Вася", "vasya@mail.ru", "password", Role.USER));
        userRepository.save(new User("Петя", "petya@mail.ru", "password", Role.USER));
        userRepository.save(new User("Иван", "ivan@mail.ru", "password", Role.USER));*/
       /* User user1 = userRepository.get(100);
        System.out.println(user1);*/
        //userRepository.save(user1);
        /*List<User> list = userRepository.getAll();
        list.forEach((user)-> System.out.println(user));*/
    }
}
