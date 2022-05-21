package ru.javastudy.hibernate.dao.implementations;

import org.hibernate.Session;
import ru.javastudy.hibernate.dao.Person;
import ru.javastudy.hibernate.dao.interfaces.ContactDAO;

import java.util.List;

/**
 * Created by Nick on 06.09.2015.
 */
public class ContactDAOImpl implements ContactDAO {

    private Session session;

    public List<Person> findAll() {
        return session.createQuery("from ContactEntity c").list();
    }

    public List<Person> findAllWithDetail() {
        return null;
    }

    public Person findById(Long id) {
        return null;
    }

    public Person save(Person contact) {
        return null;
    }

    public void delete(Person contact) {
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
