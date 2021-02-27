package dao;

import model.Contact;
import model.Group;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GroupDAOImpl implements GroupDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Group group) {
        entityManager.persist(group);
    }


    @Override
    public void delete(long[] ids) {
        Group g;
        for (long id : ids) {
            g = entityManager.getReference(Group.class, id);
            entityManager.remove(g);
        }
    }

    @Override
    public Group findOne(long id) {
        return entityManager.getReference(Group.class, id);
    }

    @Override
    public List<Group> list() {
        TypedQuery<Group> query = entityManager.createQuery("SELECT g FROM Group g", Group.class);
        return query.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM Group c", Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<Group> list(Group group,
                            int start,
                            int count) {
        TypedQuery<Group> query;


        query = entityManager.createQuery("SELECT c FROM Group c ORDER BY c.id DESC", Group.class);


        if (start >= 0) {
            query.setFirstResult(start);
            query.setMaxResults(count);
        }

        return query.getResultList();
    }
}
