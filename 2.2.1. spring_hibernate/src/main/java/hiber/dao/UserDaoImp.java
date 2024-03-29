package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
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

    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car ");
        return query.getResultList();
    }


    public List<Car> checkUser(String model, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Car where series =:series and model =:model");
        query.setParameter("model",model);
        query.setParameter("series",series);
        return query.getResultList();
    }

}
