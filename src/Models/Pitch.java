package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pitch")
public class Pitch {
    private long id;
    private float surface;
    private String address;
    private List<Training> trainings = new ArrayList<>();

    public Pitch(){}

    public Pitch(float surface, String address){
        this.surface = surface;
        this.address = address;
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
    public float getSurface(){
        return  surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(
            mappedBy = "pitch",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public void addTraining(Training training) {
        getTrainings().add(training);
        training.setPitch(this);
    }

    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.setPitch(null);
    }

}
