package hiber.dao;

import hiber.model.*;
import org.hibernate.SessionFactory;
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
        sessionFactory.getCurrentSession().save(user.getCar());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        String qry = "FROM Car c left outer join fetch c.user where c.model=:mdl and c.series=:srs";
        Car thiscar = sessionFactory.getCurrentSession().createQuery(qry, Car.class)
                .setParameter("mdl", model)
                .setParameter("srs", series)
                .uniqueResult();

        if (thiscar == null) {
            return null;
        }
        return thiscar.getUser();
    }
}
