package Model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents worker
 */
@Entity(name = "Worker")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Worker{
    /**
     * Unique id of the person
     */
    private long id;
    /**
     * Salary of the worker
     */
    private int salary;
    /**
     * Employment date of the worker
     */
    private LocalDate employmentDate;
    /**
     * Extension of person
     */
    private Person person;

    /**
     * Non-parameterized constructor
     */
    public Worker() {}

    /**
     * Parameterized constructor
     * @param person Extension of person
     * @param salary Salary of the person
     * @param employmentDate Employment date of the person
     */
    protected Worker(Person person, int salary, LocalDate employmentDate){
        this.person = person;
        this.salary = salary;
        this.employmentDate = employmentDate;
    }

    /**
     * Gets id of the person
     * @return Id of the person
     */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    /**
     * Sets id of the person
     * @param id Id of the person
     */
    public void setId(long id){this.id = id;}

    /**
     * Gets salary of the person
     * @return Salary of the person
     */
    @Basic
    public int getSalary() {
        return salary;
    }

    /**
     * Sets salary of the person
     * @param salary Salary of the person
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Gets employment date of the person
     * @return Employment date of the person
     */
    @Basic
    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    /**
     * Sets employment date of the person
     * @param employmentDate Employment date of the person
     */
    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    /**
     * Gets extension of person
     * @return Extension of person
     */
    @OneToOne
    public Person getPerson() {
        return person;
    }

    /**
     * Sets extension of person
     * @param person Extension of person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Creates new worker
     * @param person Extension of person
     * @param salary Salary of the person
     * @param employmentDate Employment date of the person
     * @return New worker
     * @throws Exception Throws Exception if person does not exist
     */
    @Transient
    public static Worker createWorker(Person person, int salary, LocalDate employmentDate) throws Exception{
        if(person == null)
            throw new Exception("The given person does not exist!");
        Worker newWorker = new Worker(person, salary, employmentDate);
        person.setWorker(newWorker);
        return  newWorker;
    }
}
