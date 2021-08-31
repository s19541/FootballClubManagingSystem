package Model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents pitch
 */
@Entity(name = "Pitch")
public class Pitch {
    /**
     * Unique id of the pitch
     */
    private long id;
    /**
     * Surface of the pitch
     */
    private float surface;
    /**
     * Address of the pitch
     */
    private String address;
    /**
     * List of trainings took place on the pitch
     */
    private List<Training> trainings = new ArrayList<>();

    /**
     * Non-parameterized constructor
     */
    public Pitch(){}

    /**
     * Parameterized constructor
     * @param surface Surface of the pitch
     * @param address Address of the pitch
     */
    public Pitch(float surface, String address){
        this.surface = surface;
        this.address = address;
    }

    /**
     * Gets id of the pitch
     * @return Id of the pitch
     */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    /**
     * Sets id of the pitch
     * @param id Id of the pitch
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get surface of the pitch
     * @return Surface of the pitch
     */
    @Basic
    public float getSurface(){
        return  surface;
    }

    /**
     * Sets surface of the pitch
     * @param surface Surface of the pitch
     */
    public void setSurface(float surface) {
        this.surface = surface;
    }

    /**
     * Gets address of the pitch
     * @return Address of the pitch
     */
    @Basic
    public String getAddress() {
        return address;
    }

    /**
     * Sets address of the pitch
     * @param address Address of the pitch
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets list of trainings took place on the pitch
     * @return List of trainings took place on the pitch
     */
    @OneToMany(
            mappedBy = "pitch",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Training> getTrainings() {
        return trainings;
    }

    /**
     * Sets list of trainings took place on the pitch
     * @param trainings List of trainings took place on the pitch
     */
    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    /**
     * Adds training to list of trainings took place on the pitch
     * @param training Training to add
     */
    public void addTraining(Training training) {
        getTrainings().add(training);
        training.setPitch(this);
    }

    /**
     * Removes training from list of trainings took place on the pitch
     * @param training Training to remove
     */
    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.setPitch(null);
    }
}
