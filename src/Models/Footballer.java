package Models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Footballer")
public class Footballer extends Worker {
    private String position;
    private int jerseyNumber;
    private List<Dietician> dieticians = new ArrayList<>();
    private List<Training> trainings = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    public Footballer(){}

    public Footballer(String firstName, String lastName, int salary, LocalDate employmentDate, String position, int jerseyNumber) throws Exception{
        super(firstName, lastName, salary, employmentDate);
        this.position = position;
        setJerseyNumber(jerseyNumber);
    }

    @Basic
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) throws Exception{
        if(jerseyNumber < 1 || jerseyNumber > 99)
            throw new Exception("Jersey number should be in range: 1-99");
        this.jerseyNumber = jerseyNumber;
    }

    @ManyToMany(mappedBy = "footballers")
    public List<Dietician> getDieticians() {
        return dieticians;
    }

    public void setDieticians(List<Dietician> dieticians) {
        this.dieticians = dieticians;
    }

    public void addDietician(Dietician dietician) {
        if(!dieticians.contains(dietician)) {
            getDieticians().add(dietician);
            dietician.getFootballers().add(this);
        }
    }

    public void removeDietician(Dietician dietician) {
        getDieticians().remove(dietician);
        dietician.getFootballers().remove(this);
    }

    @ManyToMany(mappedBy = "footballers")
    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public void addTraining(Training training) throws Exception{
        if(training.getFootballers().size() >= Training.maxFootballersNumberOnTraining)
            throw new Exception("Too much footballers on training (maximum number is: " + Training.maxFootballersNumberOnTraining + ")");
        if(!trainings.contains(training)) {
            getTrainings().add(training);
            training.getFootballers().add(this);
        }
    }

    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.getFootballers().remove(this);
    }

    @ManyToMany(mappedBy = "footballers")
    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) throws Exception{
        if(match.getFootballers().size() >= Match.maxFootballersNumberOnMatch)
            throw new Exception("Too much footballers on match (maximum number is: " + Match.maxFootballersNumberOnMatch + ")");
        if(!matches.contains(match)) {
            getMatches().add(match);
            match.getFootballers().add(this);
        }
    }

    public void removeMatch(Match match) {
        getMatches().remove(match);
        match.getFootballers().remove(this);
    }
}
