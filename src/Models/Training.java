package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Training")
public class Training {
    private long id;
    private LocalDateTime startDate;
    private Pitch pitch;
    private List<Coach> coaches = new ArrayList<>();
    private List<Footballer> footballers = new ArrayList<>();
    public static int maxFootballersNumberOnTraining = 20;
    public static int maxCoachesNumberOnTraining = 3;

    public Training(){}

    public Training(LocalDateTime startDate, Pitch pitch){
        this.startDate = startDate;
        setPitch(pitch);
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @ManyToOne
    public Pitch getPitch() {
        return pitch;
    }
    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Training_Coach",
            joinColumns = { @JoinColumn(name = "training_id") },
            inverseJoinColumns = { @JoinColumn(name = "coach_id") }
    )
    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public void addCoach(Coach coach) throws Exception{
        if(coaches.size() >= maxFootballersNumberOnTraining)
            throw new Exception("Too much coaches on training (maximum number is: " + maxCoachesNumberOnTraining + ")");
        if(!coaches.contains(coach)) {
            getCoaches().add(coach);
            coach.getTrainings().add(this);
        }
    }
    public void removeCoach(Coach coach) {
        getCoaches().remove(coach);
        coach.getTrainings().remove(this);
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Training_Footballer",
            joinColumns = { @JoinColumn(name = "training_id") },
            inverseJoinColumns = { @JoinColumn(name = "footballer_id") }
    )
    public List<Footballer> getFootballers() {
        return footballers;
    }

    public void setFootballers(List<Footballer> footballers) {
        this.footballers = footballers;
    }

    public void addFootballer(Footballer footballer) throws Exception{
        if(footballers.size() >= maxFootballersNumberOnTraining)
            throw new Exception("Too much footballers on training (maximum number is: " + maxFootballersNumberOnTraining + ")");
        if(!footballers.contains(footballer)) {
            getFootballers().add(footballer);
            footballer.getTrainings().add(this);
        }
    }
    public void removeFootballer(Footballer footballer) {
        getFootballers().remove(footballer);
        footballer.getTrainings().remove(this);
    }

}
