package jpa1.entities;

import java.sql.Date;

import jakarta.persistence.Entity;

@Entity
public class Intern extends Employee {

    // fields
    Date internshipEnd;

    // constructor

    public Intern(Date date) {
        this.internshipEnd = date;
    }

    // getter & setter
    public Date getInternshipEnd() {
        return internshipEnd;
    }

    public void setInternshipEnd(Date internshipEnd) {
        this.internshipEnd = internshipEnd;
    }

}
