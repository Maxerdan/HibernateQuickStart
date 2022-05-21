package ru.javastudy.hibernate.main;

import com.sun.prism.impl.Disposer;
import org.hibernate.Session;
import ru.javastudy.hibernate.dao.Person;
import ru.javastudy.hibernate.dao.RecordBook;
import ru.javastudy.hibernate.dao.Student;
import ru.javastudy.hibernate.utils.HibernateSessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by Nick on 05.09.2015.
 */
public class AppMain {

    @PersistenceUnit
    static EntityManager emf;

    public static void main(String[] args) {
        System.out.println("Hibernate tutorial start");
        Person[] persons = {
                createPerson("Макс", "Соколов"),
                createPerson("Мама", "Мия"),
        createPerson("Папа", "Джонс"),
        createPerson("Сникерс", "Марс"),
        createPerson("Уни", "Вер"),
        createPerson("Виктория", "Секрет"),
        createPerson("Папа", "Дос"),
        createPerson("Тест", "Тестович"),
        createPerson("Джет", "Ли"),
        createPerson("Джеки", "Чан")
        };
        for (Person pers : persons)
        {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(pers);
            session.getTransaction().commit();
            session.close();
        }

        for (int i = 0; i< getRandomNumber(0,10); i++)
        {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            RecordBook recordBook = new RecordBook();
            recordBook.setCode(getRandomNumber(0,100));
            session.save(recordBook);
            session.getTransaction().commit();
            session.close();
        }

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        List<Person> personsBd = getAllPersons(session);
        List<RecordBook> recordBooks = getAllRecordBooks(session);
        for (int i = 0; i< personsBd.size(); i++)
        {
            Person person = personsBd.get(i);
            Student student = new Student();
            student.setGroup(Integer.toString(getRandomNumber(0,5)));
            student.setPerson(person);
            if(i < recordBooks.size())
            {
                student.setRecordBook(recordBooks.get(i));
            }

            session.save(student);
        }

        session.getTransaction().commit();
        session.close();
    }

    private static Person createPerson(String firstName, String lastName)
    {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static List<RecordBook> getAllRecordBooks(Session session) {
        return session.createQuery("SELECT a FROM RecordBook a").list();
    }

    public static List<Person> getAllPersons(Session session) {
        return session.createQuery("SELECT a FROM Person a").list();
    }
}
