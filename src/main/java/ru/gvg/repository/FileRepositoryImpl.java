package ru.gvg.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gvg.model.UserFile;
import ru.gvg.model.User;
import ru.gvg.service.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 11.10.2020
 */
@Repository
public class FileRepositoryImpl implements FileRepository {

    private SessionFactory sessionFactory;

    private UserService userService;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserFile> getByUserId(Long userId) {
        User user = userService.findUserById(userId);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserFile> cq = cb.createQuery(UserFile.class);
        Root<UserFile> root = cq.from(UserFile.class);
        cq.select(root).where(cb.equal(root.get("user"), user));
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void addFile(UserFile userFile) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(userFile);
    }

    @Override
    public UserFile getFile(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        UserFile userFile = currentSession.get(UserFile.class, id);
        return userFile;
    }

    @Override
    public void deleteFile(Long id) {
        Session session = sessionFactory.getCurrentSession();
        UserFile book = session.byId(UserFile.class).load(id);
        session.delete(book);
    }
}
