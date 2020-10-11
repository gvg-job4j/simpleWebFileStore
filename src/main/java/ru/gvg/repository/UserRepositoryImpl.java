package ru.gvg.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gvg.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 08.10.2020
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
//        User user = currentSession.get(User.class, email);
//        return user;
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("email"), email));
        Query query = session.createQuery(cq);
        User user = null;
        if(query.getResultList().size() > 0){
            user = (User)query.getSingleResult();
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = currentSession.get(User.class, id);
        return user;
    }

    @Override
    public void addUser(User user) {
        boolean added = false;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User book = session.byId(User.class).load(id);
        session.delete(book);
    }
}
