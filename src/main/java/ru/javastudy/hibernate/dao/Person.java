package ru.javastudy.hibernate.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")//, schema = "", catalog = "javastudy")
public class Person implements Serializable{
    private long id;
    private int passportSeria;
    private int passportNumber;
    private String firstName;
    private String lastName;
    private String middleName = "middle";

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
    @Column(name = "passport_seria", nullable = false, insertable = true, updatable = true, length = 60)
    public int getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(int passportSeria) {
        this.passportSeria = passportSeria;
    }

    @Basic
    @Column(name = "passport_number", nullable = false, insertable = true, updatable = true, length = 60)
    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Basic
    @Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 60)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 40)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "middle_name", nullable = false, insertable = true, updatable = true, length = 40)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
