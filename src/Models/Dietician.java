package Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Dietician")
public class Dietician extends Worker{
    private List<Footballer> footballers = new ArrayList<>();


    public Dietician(){ }

    public Dietician(String firstName, String lastName, int salary, LocalDate employmentDate){
        super(firstName, lastName, salary, employmentDate);
    }
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Dietician_Footballer",
            joinColumns = { @JoinColumn(name = "dietician_id") },
            inverseJoinColumns = { @JoinColumn(name = "footballer_id") }
    )
    public List<Footballer> getFootballers() {
        return footballers;
    }

    public void setFootballers(List<Footballer> footballers) {
        this.footballers = footballers;
    }

    public void addFootballer(Footballer footballer) {
        if(!footballers.contains(footballer)) {
            getFootballers().add(footballer);
            footballer.getDieticians().add(this);
        }
    }
    public void removeFootballer(Footballer footballer) {
        getFootballers().remove(footballer);
        footballer.getDieticians().remove(this);
    }

    @Override
    public String toString() {
        return "Dietician{" +
                "footballers=" + footballers +
                '}';
    }
}
