package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        userService.add(new Car("Toyota", 1));
        userService.add(new Car("Toyota", 2));
        userService.add(new Car("Toyota", 3));
        userService.add(new Car("Toyota", 1));

        System.out.println("ЮЗЕРЫ ДОБАВЛЕНЫ:");
        List<User> users = userService.listUsers();
        users.forEach(x -> System.out.println(x.toString()));
        System.out.println("*******************");

        System.out.println("МАШИНЫ ДОБАВЛЕНЫ:");
        List<Car> cars = userService.listCars();
        cars.forEach(x -> System.out.println(x.toString()));
        System.out.println("*******************");

        System.out.println("ЮЗЕРЫ ПО ЗАПРОСУ МАРКИ И МОДЕЛИ:");
        userService.checkUser("Toyota", 1).forEach(x -> System.out.println(x.getUser()));
        System.out.println("*******************");

        context.close();
    }
}
