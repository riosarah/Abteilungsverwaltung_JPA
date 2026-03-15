package jpa1.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(
    name = "task_by_emp",
    query = "SELECT t FROM Task t JOIN FETCH employees WHERE :employee MEMBER OF t.employees"
)
public class Task {

    // fields
    @Id
    @GeneratedValue
    private int Id;

    @Column(length=512)
    private String name;


    private int bonus;

    @ManyToMany(mappedBy = "tasks", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    List<Employee> employees = new ArrayList<>();

    // constructos
    public Task() {
    }

    public Task(String name, int bonus){
        this.name = name;
        this.bonus = bonus;
    }
    public Task(String name, int bonus, List<Employee> employees){
        this(name, bonus);
        this.employees = employees;
    }

    // getters setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

      public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        for(Employee e: employees){
            e.tasks.add(this);
        }
    }

}
