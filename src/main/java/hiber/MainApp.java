package hiber;

import hiber.config.AppConfig;
import hiber.model.*;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        System.out.println("\n=======Задаем список User'ов и их машин========");
        userService.add(new User("Петя", "Васечкин", "user1@mail.ru", new Car("Москвич", 412)));
        userService.add(new User("Вася", "Петров", "user2@mail.ru", new Car("Peugeot", 806)));
        userService.add(new User("Иван", "Иванов", "user3@mail.ru", new Car("ВАЗ", 2106)));
        userService.add(new User("Маша", "Распутина", "user4@mail.ru", new Car("ЗИЛ", 131)));
        System.out.println("OK");
        System.out.println("\n=======Достаем из БД список User'ов и их машин========");
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
            System.out.println("-------------------");
        }

        System.out.println("\n=======Получаем User по Марке и Серии автомобиля========");
        System.out.println(userService.getUserByCar("ВАЗ", 2106));

        context.close();
    }
}
