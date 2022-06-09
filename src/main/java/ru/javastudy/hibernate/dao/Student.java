package ru.javastudy.hibernate.dao;

import ru.javastudy.hibernate.dao.listeners.ContactEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@EntityListeners(ContactEntityListener.class)
@Entity
@Table(name = "student")//, schema = "", catalog = "javastudy")
public class Student implements Serializable{
    private long id;

    private RecordBook recordBook;

    private Person person;
    private String group;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //generated DataBase auto_increment when insert value
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @ManyToOne(cascade = CascadeType.ALL)
    public RecordBook getRecordBook() {
        return recordBook;
    }

    public void setRecordBook(RecordBook recordBook) {
        this.recordBook = recordBook;
    }

    @Basic
    @ManyToOne(cascade = CascadeType.ALL)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Basic
    @Column(name = "group_gr", nullable = false, insertable = true, updatable = true, length = 60)
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
