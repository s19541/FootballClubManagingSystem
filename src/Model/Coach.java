package Model;

import Enums.CoachRole;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents coach
 */
@Entity(name = "Coach")
public class Coach extends Worker{
    /**
     * Role of the coach
     */
    private CoachRole coachRole;
    /**
     * List of trainings conducted by the coach
     */
    private List<Training> trainings = new ArrayList<>();

    /**
     * Non-parameterized constructor
     */
    public Coach(){}

    /**
     * Parametrized constructor
     * @param person Reference to person object
     * @param salary Salary of the coach
     * @param employmentDate Employment date of the coach
     * @param coachRole Role of the coach
     */
    public Coach(Person person, int salary, LocalDate employmentDate, CoachRole coachRole){
        super(person, salary, employmentDate);
        this.coachRole = coachRole;
    }

    /**
     * Gets coach role
     * @return Coach role
     */
    @Enumerated
    public CoachRole getCoachRole(){
        return coachRole;
    }

    /**
     * Sets coach role
     * @param coachRole
     */
    public void setCoachRole(CoachRole coachRole) {
        this.coachRole = coachRole;
    }

    /**
     * Gets list of trainings conducted by the coach
     * @return list of trainings conducted by the coach
     */
    @ManyToMany(mappedBy = "coaches")
    public List<Training> getTrainings() {
        return trainings;
    }

    /**
     * Sets list of trainings conducted by the coach
     * @return list of trainings conducted by the coach
     */
    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    /**
     * Adds training to list of trainings conducted by the coach
     * @param training Training to add
     * @throws Exception Throws exception if training is conducted by too big amount of coaches
     */
    public void addTraining(Training training) throws Exception{
        if(training.getCoaches().size() >= Training.maxFootballersNumberOnTraining)
            throw new Exception("Too much coaches on training (maximum number is: " + Training.maxCoachesNumberOnTraining + ")");
        if(!trainings.contains(training)) {
            getTrainings().add(training);
            training.getCoaches().add(this);
        }
    }

    /**
     * Removes training from list of trainings conducted by the coach
     * @param training Training to remove
     */
    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.getCoaches().remove(this);
    }

    /**
     * Creates new coach
     * @param person Reference to person object
     * @param salary Salary of the coach
     * @param employmentDate Employment date of the coach
     * @param coachRole Role of the coach
     * @return New Coach
     * @throws Exception Throws exception if given person does not exist
     */
    public static Coach createCoach(Person person, int salary, LocalDate employmentDate, CoachRole coachRole) throws Exception{
        if(person == null)
            throw new Exception("The given person does not exist!");
        Coach newCoach = new Coach(person, salary, employmentDate, coachRole);
        person.setWorker(newCoach);
        return newCoach;
    }
}
