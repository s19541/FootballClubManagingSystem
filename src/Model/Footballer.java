package Model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents footballer
 */
@Entity(name = "Footballer")
public class Footballer extends Worker {
    /**
     * Position of the footballer
     */
    private String position;
    /**
     * Jersey number of the footballer
     */
    private int jerseyNumber;
    /**
     * List of dieticians which take care of footballer
     */
    private List<Dietician> dieticians = new ArrayList<>();
    /**
     * List of trainings participated by footballer
     */
    private List<Training> trainings = new ArrayList<>();
    /**
     * List of matches participated by footballer
     */
    private List<Match> matches = new ArrayList<>();

    /**
     * Non-parameterized constructor
     */
    public Footballer(){}

    /**
     * Parameterized constructor
     * @param person Reference to person object
     * @param salary Salary of the footballer
     * @param employmentDate Employment date of the footballer
     * @param position Position of the footballer
     * @param jerseyNumber Jersey number of the footballer
     */
    private Footballer(Person person, int salary, LocalDate employmentDate, String position, int jerseyNumber){
        super(person, salary, employmentDate);
        this.position = position;
        this.jerseyNumber = jerseyNumber;
    }

    /**
     * Gets position of footballer
     * @return Position of footballer
     */
    @Basic
    public String getPosition() {
        return position;
    }

    /**
     * Sets position of footballer
     * @param position Position of footballer
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets jersey number of footballer
     * @return Jersey number of footballer
     */
    @Basic
    public int getJerseyNumber() {
        return jerseyNumber;
    }

    /**
     * Sets jersey number of footballer
     * @param jerseyNumber jersey number of footballer
     */
    public void setJerseyNumber(int jerseyNumber){
        this.jerseyNumber = jerseyNumber;
    }

    /**
     * Gets list of dieticians which take care of footballer
     * @return List of dieticians which take care of footballer
     */
    @ManyToMany(mappedBy = "footballers")
    public List<Dietician> getDieticians() {
        return dieticians;
    }

    /**
     * Sets list of dieticians which take care of footballer
     * @param dieticians List of dieticians which take care of footballer
     */
    public void setDieticians(List<Dietician> dieticians) {
        this.dieticians = dieticians;
    }

    /**
     * Adds dietician to list of dieticians which take care of footballer
     * @param dietician Dietician to add
     */
    public void addDietician(Dietician dietician) {
        if(!dieticians.contains(dietician)) {
            getDieticians().add(dietician);
            dietician.getFootballers().add(this);
        }
    }

    /**
     * Removes dietician from list of dieticians which take care of footballer
     * @param dietician Dietician to delete
     */
    public void removeDietician(Dietician dietician) {
        getDieticians().remove(dietician);
        dietician.getFootballers().remove(this);
    }

    /**
     * Gets list of trainings participated by footballer
     * @return List of trainings participated by footballer
     */
    @ManyToMany(mappedBy = "footballers")
    public List<Training> getTrainings() {
        return trainings;
    }

    /**
     * Sets List of trainings participated by footballer
     * @param trainings List of trainings participated by footballer
     */
    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    /**
     * Add new training to list of trainings participated by footballer
     * @param training Training to add
     * @throws Exception Throws exception if training have maximum number of players
     */
    public void addTraining(Training training) throws Exception{
        if(training.getFootballers().size() >= Training.maxFootballersNumberOnTraining)
            throw new Exception("Too much footballers on training (maximum number is: " + Training.maxFootballersNumberOnTraining + ")");
        if(!trainings.contains(training)) {
            getTrainings().add(training);
            training.getFootballers().add(this);
        }
    }

    /**
     * Removes training from list of trainings participated by footballer
     * @param training Training to remove
     */
    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.getFootballers().remove(this);
    }

    /**
     * Gets list of matches participated by footballer
     * @return List of matches participated by footballer
     */
    @ManyToMany(mappedBy = "footballers")
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * Sets list of matches participated by footballer
     * @param matches List of matches participated by footballer
     */
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    /**
     * Add match to list of matches participated by footballer
     * @param match Match to add
     * @throws Exception Throws exception if match have maximum number of players
     */
    public void addMatch(Match match) throws Exception{
        if(match.getFootballers().size() >= 20)
            throw new Exception("Too much footballers on match (maximum number is: 20)");
        if(!matches.contains(match)) {
            getMatches().add(match);
            match.getFootballers().add(this);
        }
    }

    /**
     * Removes footballer from list of matches participated by footballer
     * @param match Match to remove
     * @throws Exception Throws exception if match have minimum number of players
     */
    public void removeMatch(Match match) throws Exception{
        if(match.getFootballers().size() <= 14)
            throw new Exception("Squad must have at least 14 footballers");
        getMatches().remove(match);
        match.getFootballers().remove(this);
    }

    /**
     * Creates new footballer
     * @param person Reference to person object
     * @param salary Salary of the player
     * @param employmentDate Employment date of the player
     * @param position Position of the player
     * @param jerseyNumber Jersey number of the player
     * @return New footballer
     * @throws Exception Throws exception if given person does not exist or jersey number is not valid
     */
    public static Footballer createFootballer(Person person, int salary, LocalDate employmentDate, String position, int jerseyNumber) throws Exception{
        if(person == null)
            throw new Exception("The given person does not exist!");
        if(jerseyNumber < 1 || jerseyNumber > 99)
            throw new Exception("Jersey number should be in range: 1-99");
        Footballer newFootballer = new Footballer(person, salary, employmentDate, position, jerseyNumber);
        person.setWorker(newFootballer);
        return newFootballer;
    }

    /**
     * Override toString method
     * @return Main information about footballer
     */
    @Override
    public String toString() {
        return getPerson().getFirstName() + " " + getPerson().getLastName() + " " + getPosition();
    }
}
