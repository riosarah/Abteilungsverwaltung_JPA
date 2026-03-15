package jpa1.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;

@Entity
public class Employee {

    //
    @Id
    @GeneratedValue
    int Id;

    @Column(length = 512)
    String firstName;

    @Column(length = 512)
    String lastName;

    int salary;

    @Embedded
    Address address;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    Division division;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Task> tasks = new ArrayList<>();

    // constructor
    public Employee() {
    }

    public Employee(String firstName, String lastName, int salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.salary = salary;
    }

      public Employee(String firstname, String lastname, int salary, Address address) {
        this(firstname, lastname, salary);
        this.address = address;
    }

    public Employee(String firstname, String lastname, int salary, Division division) {
        this(firstname, lastname, salary);
        this.division = division;
    }

    public Employee(String firstname, String lastname, int salary, List<Task> tasks) {
        this(firstname, lastname, salary);
        this.tasks = tasks;
    }

    // getter & setter

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
        division.getEmployees().add(this);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        for(Task t : tasks){
            t.employees.add(this);
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
