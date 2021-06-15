package Models;

import Enums.CoachRole;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Coach")
public class Coach extends Worker{
    private CoachRole coachRole;
    private List<Training> trainings = new ArrayList<>();

    public Coach(){}

    public Coach(Person person, int salary, LocalDate employmentDate, CoachRole coachRole){
        super(person, salary, employmentDate);
        this.coachRole = coachRole;
    }
    @Enumerated
    public CoachRole getCoachRole(){
        return coachRole;
    }

    public void setCoachRole(CoachRole coachRole) {
        this.coachRole = coachRole;
    }
    @ManyToMany(mappedBy = "coaches")
    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public void addTraining(Training training) throws Exception{
        if(training.getCoaches().size() >= Training.maxFootballersNumberOnTraining)
            throw new Exception("Too much coaches on training (maximum number is: " + Training.maxCoachesNumberOnTraining + ")");
        if(!trainings.contains(training)) {
            getTrainings().add(training);
            training.getCoaches().add(this);
        }
    }
    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.getCoaches().remove(this);
    }

    public static Coach createCoach(Person person, int salary, LocalDate employmentDate, CoachRole coachRole) throws Exception{
        if(person == null)
            throw new Exception("The given person does not exist!");
        Coach newCoach = new Coach(person, salary, employmentDate, coachRole);
        person.setWorker(newCoach);
        return newCoach;
    }
}
