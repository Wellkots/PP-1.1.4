package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory = getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
        session.beginTransaction();

        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                        "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)")
                .executeUpdate();
        session.getTransaction().commit();

    } catch (HibernateException e) {
        tr.rollback();
    }

    }

    @Override
    public void dropUsersTable() {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS users")
                    .executeUpdate();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            tr.rollback();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            tr.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            tr.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            users = session.createQuery("From User").getResultList();
            tr.commit();
        } catch (HibernateException e) {
                tr.rollback();
            }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE USER").executeUpdate();
            session.getTransaction().commit();
            } catch (HibernateException e) {
            tr.rollback();
            }
        }
    }

