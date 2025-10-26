package org.spring.tuts.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.spring.tuts.entities.generators.CustomUUIDGenerator;

import java.util.Calendar;
import java.util.Date;

@Entity(name = "employee2")
@Table(name = "employee")
public class Employee1 {

    @Id
    @GenericGenerator(name = "UUIDGenerator", type = CustomUUIDGenerator.class)
//    @GeneratedValue(strategy = GenerationType.UUID)
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(length = 500)
    private String id;
    private String name;
    private String address;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Transient
    private int age;

    public enum Gender {
        MALE,
        FEMALE
    }

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return new Date().getYear() - dateOfBirth.getYear();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + getAge() +
                '}';
    }
}
