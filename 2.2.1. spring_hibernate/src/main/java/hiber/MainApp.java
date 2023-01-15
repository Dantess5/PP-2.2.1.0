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
        SessionFactory sessionFactory = null;

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        userService.add(new Car("Camry 3.5", 2007));
        userService.add(new Car("Lada 3.5", 2000));
        userService.add(new Car("Vesta 3.5", 1995));
        userService.add(new Car("TOP 3.5", 2022));

        List<User> users = userService.listUsers();
        List<Car> cars = userService.listCars();

        for (int i = 0, j = 0; i < users.size() && j < cars.size(); i++, j++) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = users.get(i);
            user.setCar(cars.get(j));
            session.save(user);
            session.getTransaction().commit();
        }
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        User user = userService.getUserByCar("Camry 3.5", 2007);
        System.out.println(user.toString() + " " + user.getCar());

        context.close();
    }
}
