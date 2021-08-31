package Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents dietician
 */
@Entity(name = "Dietician")
public class Dietician extends Worker{
    /**
     * List of footballers under care of the dietician
     */
    private List<Footballer> footballers = new ArrayList<>();

    /**
     * Non-parameterized constructor
     */
    public Dietician(){ }

    /**
     * Parameterized constructor
     * @param person Reference to person object
     * @param salary Salary of the dietician
     * @param employmentDate Employment date of the coach
     */
    private Dietician(Person person, int salary, LocalDate employmentDate){
        super(person, salary, employmentDate);
    }

    /**
     * Gets list of footballers under care of the dietician
     * @return List of footballers under care of the dietician
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Dietician_Footballer",
            joinColumns = { @JoinColumn(name = "dietician_id") },
            inverseJoinColumns = { @JoinColumn(name = "footballer_id") }
    )
    public List<Footballer> getFootballers() {
        return footballers;
    }

    /**
     * Sets list of footballers under care of the dietician
     * @param footballers List of footballers under care of the dietician
     */
    public void setFootballers(List<Footballer> footballers) {
        this.footballers = footballers;
    }

    /**
     * Adds footballer to list of footballers under care of the dietician
     * @param footballer Footballer to add
     */
    public void addFootballer(Footballer footballer) {
        if(!footballers.contains(footballer)) {
            getFootballers().add(footballer);
            footballer.getDieticians().add(this);
        }
    }

    /**
     * Removes footballer from list of footballers under care of the dietician
     * @param footballer Footballer to remove
     */
    public void removeFootballer(Footballer footballer) {
        getFootballers().remove(footballer);
        footballer.getDieticians().remove(this);
    }

    /**
     * Creates new dietician
     * @param person Reference to person object
     * @param salary Salary of the dietician
     * @param employmentDate Employment date of the dietician
     * @return New dietician
     * @throws Exception Throws exception if given person does not exist
     */
    public static Dietician createDietician(Person person, int salary, LocalDate employmentDate) throws Exception{
        if(person == null)
            throw new Exception("The given person does not exist!");
        Dietician newDietician = new Dietician(person, salary, employmentDate);
        person.setWorker(newDietician);
        return  newDietician;
    }
}
