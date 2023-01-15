package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


//        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Camry 3.5", 2022)));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Lada", 2000)));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Kadilak", 1990)));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("ёМобиль", 2021)));
        User user = new User("User", "Lastname1", "user1@mail.ru");
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname1", "user1@mail.ru");
        User user3 = new User("User3", "Lastname1", "user1@mail.ru");
        user.setCar(new Car("Camry 3.5", 2022));
        user1.setCar(new Car("Lada", 2000));
        user2.setCar(new Car("Kadilak", 1990));
        user3.setCar(new Car("ёМобиль", 2021));
        userService.add(user);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);


        List<User> users = userService.listUsers();

        for (User user4 : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        User user5met = userService.getUserByCar("Lada", 2000);
        System.out.println(user5met.toString());

        context.close();
    }
}
