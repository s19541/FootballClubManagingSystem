package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Worker")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Worker{
    private int salary;
    private LocalDate employmentDate;
    private Person person;
    private long id;

    public Worker() {}

    protected Worker(Person person, int salary, LocalDate employmentDate){
        this.person = person;
        this.salary = salary;
        this.employmentDate = employmentDate;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    public void setId(long id){this.id = id;}

    @Basic
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Basic
    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    @OneToOne
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Transient
    public static Worker createWorker(Person person, int salary, LocalDate employmentDate) throws Exception{
        if(person == null)
            throw new Exception("The given person does not exist!");
        Worker newWorker = new Worker(person, salary, employmentDate);
        person.setWorker(newWorker);
        return  newWorker;
    }
}
