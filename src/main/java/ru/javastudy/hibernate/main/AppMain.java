package ru.javastudy.hibernate.main;

import com.sun.prism.impl.Disposer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import ru.javastudy.hibernate.dao.Person;
import ru.javastudy.hibernate.dao.RecordBook;
import ru.javastudy.hibernate.dao.Student;
import ru.javastudy.hibernate.utils.HibernateSessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

        List<Person> personsWithA = getAllPersonsWithA(session);
        listPersons(personsWithA);

        Criteria cr = session.createCriteria(Person.class);
        Criterion firstName = Restrictions.like("firstName", "%а%");
        Criterion lastName = Restrictions.like("lastName", "%а%");
        Criterion middleName = Restrictions.like("middleName", "%а%");
        LogicalExpression orExp1 = Restrictions.or(firstName,lastName);
        LogicalExpression orExp2 = Restrictions.or(orExp1,middleName);
        cr.add( orExp2 );
        List<Person> personsWithACriteria = cr.list();
        listPersons(personsWithACriteria);

        List<Student> students = getAllStudentsWithoutRecordBook(session);
        listStudents(students);

        Criteria cr2 = session.createCriteria(Student.class);
        cr2.add( Restrictions.isNull("recordBook") );
        List<Student> studentsWithoutRecordBook = cr2.list();
        listStudents(studentsWithoutRecordBook);

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

    public static List<Person> getAllPersonsWithA(Session session) {
        return session.createQuery("select a from Person a where first_name like '%а%' or last_name like '%а%' or middle_name like '%а%'").list();
    }

    public static List<Student> getAllStudentsWithoutRecordBook(Session session) {
        return session.createQuery("select a from Student a where recordBook is null").list();
    }

    private static void listPersons(List<Person> persons) {
        System.out.println("ID\tFirstName\tLastName\tMiddleName\tPassportSeria\tPassportNumber");
        for (Person person : persons) {
            System.out.println(person.getId() + "\t" + person.getFirstName() + "\t" + person.getLastName() + "\t" + person.getMiddleName() + "\t" + person.getPassportSeria() + "\t" + person.getPassportNumber());
        }
        System.out.println("-------------------------");
    }

    private static void listStudents(List<Student> students) {
        System.out.println("ID\tGroup\tFirstName\tLastName\tRecordBook");
        for (Student st : students) {
            System.out.println(st.getId() + "\t" + st.getGroup() + "\t" + st.getPerson().getFirstName() + "\t" + st.getPerson().getLastName() + "\t" + st.getRecordBook());
        }
        System.out.println("-------------------------");
    }
}
