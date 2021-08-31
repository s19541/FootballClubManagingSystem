package Model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents training
 */
@Entity(name = "Training")
public class Training {
    /**
     * Unique id of the training
     */
    private long id;
    /**
     * Start date and time of training
     */
    private LocalDateTime startDate;
    /**
     * Pitch where training take place
     */
    private Pitch pitch;
    /**
     * List of coaches conducting the training
     */
    private List<Coach> coaches = new ArrayList<>();
    /**
     * List of footballers participating the training
     */
    private List<Footballer> footballers = new ArrayList<>();
    /**
     * Maximum footballers number on training
     */
    public static int maxFootballersNumberOnTraining = 20;
    /**
     * Maximum coach number on training
     */
    public static int maxCoachesNumberOnTraining = 3;

    /**
     * Non-parameterized constructor
     */
    public Training(){}

    /**
     * Parameterized constructor
     * @param startDate Start date and time of training
     * @param pitch Pitch where training take place
     */
    public Training(LocalDateTime startDate, Pitch pitch){
        this.startDate = startDate;
        setPitch(pitch);
    }

    /**
     * Gets id of the training
     * @return Id of the training
     */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    /**
     * Sets id of the training
     * @param id Id of the training
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets start date and time of training
     * @return Start date and time of training
     */
    @Basic
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets start date and time of training
     * @param startDate Start date and time of training
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets pitch where training take place
     * @return Pitch where training take place
     */
    @ManyToOne
    public Pitch getPitch() {
        return pitch;
    }

    /**
     * Sets pitch where training take place
     * @param pitch Pitch where training take place
     */
    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    /**
     * Gets list of coaches conducting the training
     * @return List of coaches conducting the training
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Training_Coach",
            joinColumns = { @JoinColumn(name = "training_id") },
            inverseJoinColumns = { @JoinColumn(name = "coach_id") }
    )
    public List<Coach> getCoaches() {
        return coaches;
    }

    /**
     * Sets list of coaches conducting the training
     * @param coaches List of coaches conducting the training
     */
    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    /**
     * Adds coach to list of coaches conducting the training
     * @param coach Coach to add
     * @throws Exception Throws exception if training have maximum number of coaches
     */
    public void addCoach(Coach coach) throws Exception{
        if(coaches.size() >= maxFootballersNumberOnTraining)
            throw new Exception("Too much coaches on training (maximum number is: " + maxCoachesNumberOnTraining + ")");
        if(!coaches.contains(coach)) {
            getCoaches().add(coach);
            coach.getTrainings().add(this);
        }
    }

    /**
     * Removes coach from list of coaches conducting the training
     * @param coach Coach to remove
     */
    public void removeCoach(Coach coach) {
        getCoaches().remove(coach);
        coach.getTrainings().remove(this);
    }

    /**
     * Gets list of footballers participating the training
     * @return List of footballers participating the training
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Training_Footballer",
            joinColumns = { @JoinColumn(name = "training_id") },
            inverseJoinColumns = { @JoinColumn(name = "footballer_id") }
    )
    public List<Footballer> getFootballers() {
        return footballers;
    }

    /**
     * Sets list of footballers participating the training
     * @param footballers List of footballers participating the training
     */
    public void setFootballers(List<Footballer> footballers) {
        this.footballers = footballers;
    }

    /**
     * Adds footballer to list of footballers participating the training
     * @param footballer Footballer to add
     * @throws Exception Throws exception if training have maximum number of footballers
     */
    public void addFootballer(Footballer footballer) throws Exception{
        if(footballers.size() >= maxFootballersNumberOnTraining)
            throw new Exception("Too much footballers on training (maximum number is: " + maxFootballersNumberOnTraining + ")");
        if(!footballers.contains(footballer)) {
            getFootballers().add(footballer);
            footballer.getTrainings().add(this);
        }
    }

    /**
     * Remove footballer from list of footballers participating the training
     * @param footballer Footballers to remove
     */
    public void removeFootballer(Footballer footballer) {
        getFootballers().remove(footballer);
        footballer.getTrainings().remove(this);
    }
}
