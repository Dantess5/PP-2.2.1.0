package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Query<User> query = session.createQuery("from User user where user.car.model =: model and user.car.series =: series").setParameter("model", model)
                    .setParameter("series", series);
            List<User> list = query.getResultList();
            if (list == null) {
                return new User();
            } else {
                return list.get(0);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }
}
