package jpa1.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQuery(
    name="division_with_min_employee",
    query="SELECT d FROM Division d JOIN FETCH d.employees where SIZE(d.employees) >= 1"
)
public class Division {

    // fields
    @Id
    @GeneratedValue
    int Id;

    @Column(length = 512)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "division")
    private List<Employee> employees = new ArrayList<>();
   


    // constructor
    public Division() {
    }

    public Division(String name){
        this.name = name;
    }
    public Division(String name, List<Employee> emps){
        this(name);
        this.employees = emps;        
    }

    // getters & setters
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        for( Employee e : employees){
            e.setDivision(this);
        }
    }
}
