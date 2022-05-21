package ru.javastudy.hibernate.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recordBook")//, schema = "", catalog = "javastudy")
public class RecordBook implements Serializable{
    private long id;
    private int code;

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
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 60)
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
