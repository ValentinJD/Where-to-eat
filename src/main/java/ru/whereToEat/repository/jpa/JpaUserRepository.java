package ru.whereToEat.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.whereToEat.model.User;
import ru.whereToEat.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
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
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User get(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    public boolean delete(Integer id) {

/*      User ref = em.getReference(User.class, id);
        em.remove(ref);
*/
        Query query = em.createQuery("DELETE FROM User u WHERE u.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
