package Models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;

@Entity(name = "Worker")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Worker extends Person {
    private int salary;
    private LocalDate employmentDate;

    public Worker() {}

    public Worker(String firstName, String lastName, int salary, LocalDate employmentDate){
        super(firstName,lastName);
        this.salary = salary;
        this.employmentDate = employmentDate;
    }

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
}
