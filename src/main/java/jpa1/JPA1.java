
package jpa1;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jpa1.entities.Address;
import jpa1.entities.Division;
import jpa1.entities.Employee;
import jpa1.entities.Task;

public class JPA1 {
        public JPA1() {
        }

        public static void main(String[] args) throws Exception {

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA1PU");
                EntityManager em = emf.createEntityManager();

                // setup
                var empOne = new Employee("Max", "Meier", 1500);
                var empTwo = new Employee("Maria", "Mauer", 1700);
                var emptThree = new Employee("Mario", "Bowser", 2000);

                var addressOne = new Address("Einweg", "1a", 1234);
                var addressTwo = new Address("Zweiweg", "2a", 1234);
                var addressThree = new Address("Dreiweg", "3a", 1235);

                var divisionOne = new Division("Human Ressources");

                var taskOne = new Task("Feed fish", 100);
                var taskTwo = new Task("Groom dog", 200);
                var taskThree = new Task("Brush cat", 150);

                List<Task> taskList = new ArrayList<>();

                taskList.add(taskOne);
                taskList.add(taskTwo);
                taskList.add(taskThree);

                empOne.setAddress(addressOne);
                empTwo.setAddress(addressTwo);
                emptThree.setAddress(addressThree);

                em.getTransaction().begin();

                em.persist(empOne);
                em.persist(empTwo);
                em.persist(emptThree);

                em.persist(divisionOne);

                em.persist(taskOne);
                em.persist(taskTwo);
                em.persist(taskThree);

                em.getTransaction().commit();

                // Alle Mitarbeiter:
                // - die in einer bestimmten Stadt leben (zipCode der Adresse)

                System.out.println("__________________________");
                System.out.println("Employees from city with zipCode 1234");
                var employees = em
                                .createQuery("SELECT e FROM Employee e WHERE e.address.zipCode = 1234", Employee.class)
                                .getResultList();

                for (Employee employee : employees) {
                        System.out.println("__________________________");
                        System.out.println(employee.getLastName());
                        System.out.println(employee.getAddress().getZipCode());
                        System.out.println("__________________________");
                }

                // - mit bestimmten Vor- und Nachnamen, diese sollen als Positional Paramater
                // übergeben werden

                System.out.println("__________________________");
                System.out.println("Positional Parameter");

                var firstname = "Max";
                var lastname = "Meier";

                var employeeMax = em
                                .createQuery("SELECT e FROM Employee e WHERE e.firstName =?1 AND e.lastName = ?2",
                                                Employee.class)
                                .setParameter(1, firstname)
                                .setParameter(2, lastname)
                                .getSingleResult();

                System.out.println(employeeMax.getFirstName() + " " + employeeMax.getLastName());

                System.out.println("__________________________");

                // - alle Mitarbeiter, denen zumindest ein Task zugeordnet wurde
                // - die in einer bestimmten Abteilung arbeiten. Der Query soll die ganze
                // Abteilung übergeben werden (nicht nur deren ID), übergabe als Named
                // Parameter.

                System.out.println("__________________________");
                System.out.println("Mitarbeiter mit min einem Task aus Abteilung 1234");

                // em.clear();
                em.getTransaction().begin();
                // // empOne = em.find(Employee.class, empOne.getId());
                // em.getTransaction().begin();
                // empOne = em.find(Employee.class, empOne.getId());
                // divisionOne = em.find(Division.class, divisionOne.getId());

                empOne.setTasks(taskList);
                empOne.setDivision(divisionOne);
                em.getTransaction().commit();

                System.out.println(empOne.getTasks().getFirst().getName());
                System.out.println(empOne.getDivision().getName());

                var task_emps_divison = em.createQuery(
                                "SELECT e FROM Employee e WHERE Size(e.tasks) >= 1 AND e.division = :division",
                                Employee.class)
                                .setParameter("division", divisionOne).getResultList();

                for (Employee task_emp : task_emps_divison) {
                        System.out.println(task_emp.getFirstName());
                        System.out.println(task_emp.getLastName());

                }

                System.out.println("__________________________");

                // - Alle Mitarbeiter. In einer Abfrage sollen auch gleich die Adresse und die
                // Abteilung mittels fetch join geladen werden

                var emp_ad_div = em.createQuery("SELECT e FROM Employee e LEFT JOIN FETCH e.address LEFT JOIN FETCH  e.division" , Employee.class)
                .getResultList();

                for(Employee em_div : emp_ad_div){
                        System.out.println(em_div.getLastName() + " " + em_div.getFirstName());
                        System.out.println((em_div.getAddress() != null)?em_div.getAddress().getStreet(): "No Address" +" "+ em_div.getAddress().getNumber());
                        System.out.println((em_div.getDivision()!= null)? em_div.getDivision().getName() : "No division registered.");
                       
                }

                // Als NamedQuery:
                // - Alle Abteilungen, die mehr als einen Mitarbeiter haben

                var div_min_emp = em.createNamedQuery("division_with_min_employee", Division.class)
                .getResultList();

                for(Division div : div_min_emp){
                        System.out.println(div.getName());
                        System.out.println(div.getEmployees().size());
                }
                // - alle Tasks eines bestimmten Mitarbeiters.

                var max = em.find(Employee.class, 1);

                var task_by_em = em.createNamedQuery("task_by_emp", Task.class).setParameter("employee", max).getResultList();

                for(Task t: task_by_em){
                        System.out.println(t.getName());
                        System.out.println("Registered Employees: " +t.getEmployees().getFirst().getFirstName());
                }
        }
}
