package com.example.student;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Student implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -3678159111925814848L;
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String passportNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_registration_id")
    // @JsonBackReference
    private StudentRegistration studentRegistration;

    public Student() {
        super();
    }

    public Student(Long id, String name, String passportNumber) {
        super();
        this.id = id;
        this.name = name;
        this.passportNumber = passportNumber;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the passportNumber
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * @param passportNumber
     *            the passportNumber to set
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * @return the studentRegistration
     */
    public StudentRegistration getStudentRegistration() {
        return studentRegistration;
    }

    /**
     * @param studentRegistration
     *            the studentRegistration to set
     */
    public void setStudentRegistration(StudentRegistration studentRegistration) {
        this.studentRegistration = studentRegistration;
    }
}
