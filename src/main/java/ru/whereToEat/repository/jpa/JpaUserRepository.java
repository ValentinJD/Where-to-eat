package ru.whereToEat.repository.jpa;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.User;
import ru.whereToEat.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

/*
    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }
*/

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        try {
            if (user.isNew()) {
                em.persist(user);
                return user;
            } else {
                return em.merge(user);
            }
        } catch (RuntimeException e) {
            throw new NotSaveOrUpdateException();
        }

    }

    @Override
    public User get(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {

/*      User ref = em.getReference(User.class, id);
        em.remove(ref);

        Query query = em.createQuery("DELETE FROM User u WHERE u.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
*/
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;

    }

    @Override
    public User getByEmail(String email) {
        List<User> users = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }
}
