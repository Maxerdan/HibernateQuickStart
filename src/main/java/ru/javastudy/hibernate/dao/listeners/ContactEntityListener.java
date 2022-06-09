package ru.javastudy.hibernate.dao.listeners;

import ru.javastudy.hibernate.dao.Student;

import javax.persistence.PreRemove;

public class ContactEntityListener {
    @PreRemove
    private void preRemove (Student student) {
        System.out.println("REMOVE STUDENT: " + student.getPerson().getFirstName() + " " + student.getPerson().getLastName());
    }
}
