package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static Session session;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("CREATE TABLE Users (id BIGINT NOT NULL AUTO_INCREMENT, Name varchar(255), LastName varchar(255), Age TINYINT, PRIMARY KEY (id))");
            query.executeUpdate();
            tx1.commit();
        } catch (Exception sqlException) {
            Util.RollbackQuietly(session.getTransaction());
        } finally {
            Util.CloseQuietly(session);
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("DROP TABLE Users");
            query.executeUpdate();
            tx1.commit();
        } catch (Exception sqlException) {
            Util.RollbackQuietly(session.getTransaction());
        } finally {
            Util.CloseQuietly(session);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx1.commit();
        } catch (Exception sqlException) {
            Util.RollbackQuietly(session.getTransaction());
        } finally {
            Util.CloseQuietly(session);
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(getUserById(id));
            transaction.commit();
            System.out.println("\n>>> Student with id = " + id + " is successfully deleted!\n");
        } catch (Exception sqlException) {
            Util.RollbackQuietly(session.getTransaction());
        } finally {
            Util.CloseQuietly(session);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = null;
        try {
            session = Util.getSessionFactory().openSession();
            result = (List<User>) session.createQuery("From User").list();
        } catch (Exception sqlException) {
            Util.RollbackQuietly(session.getTransaction());
        } finally {
            Util.CloseQuietly(session);
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("TRUNCATE TABLE Users");
            query.executeUpdate();
            tx1.commit();
        } catch (Exception sqlException) {
            Util.RollbackQuietly(session.getTransaction());
        } finally {
            Util.CloseQuietly(session);
        }
    }

    public User getUserById(long id) {
        User user = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception sqlException) {
            Util.RollbackQuietly(session.getTransaction());
        } finally {
            Util.CloseQuietly(session);
        }
        return user;
    }
}
