package hiber.dao;

import hiber.model.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().save(user.getCar());
    }

    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User u where u.car.model=:mdl and u.car.series=:srs", User.class)
                .setParameter("mdl", model)
                .setParameter("srs", series)
                .uniqueResult();
    }
}
