package ru.javastudy.hibernate.dao.interfaces;

import ru.javastudy.hibernate.dao.Person;

import java.util.List;

/**
 * Created by Nick on 06.09.2015.
 */
public interface ContactDAO {

    // Найти все контакты.
    public List<Person> findAll();

    // Найти все контакты с заданным телефоном и хобби.
    public List<Person> findAllWithDetail();

    // Найти контакт со всеми деталями по идентификатору.
    public Person findById(Long id);

    // Вставить или обновить контакт.
    public Person save(Person contact);

    // Удалить контакт.
    public void delete(Person contact);
}