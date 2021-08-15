package Model;

import Controllers.PersonController;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Person")
public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private Supporter supporter;
    private Worker worker;

    public Person(){}

    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    public void setId(long id){this.id = id;}

    @Basic
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToOne
    public Supporter getSupporter(){
        return supporter;
    }

    public void setSupporter(Supporter supporter) {
        if(this.supporter != null)
            PersonController.deleteSupporterFromDb(supporter);
        this.supporter = supporter;
    }

    @OneToOne
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        if(this.worker != null && worker != null)
            PersonController.deleteWorkerFromDb(this.worker);
        this.worker = worker;
    }

    @Transient
    public String getName() {
        return getFirstName() + " " + getLastName();
    }
}
