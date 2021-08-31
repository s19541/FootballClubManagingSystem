package Model;

import Controllers.PersonController;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * Represents person
 */
@Entity(name = "Person")
public class Person {
    /**
     * Unique id of the person
     */
    private long id;
    /**
     * First name of the person
     */
    private String firstName;
    /**
     * Last name of the person
     */
    private String lastName;
    /**
     * Reference to supporter (required to overlapping)
     */
    private Supporter supporter;
    /**
     * Reference to worker (required to overlapping)
     */
    private Worker worker;

    /**
     * Non-parameterized constructor
     */
    public Person(){}

    /**
     * Parameterized constructor
     * @param firstName First name of the person
     * @param lastName Last name of the person
     */
    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets id of the person
     * @return Id of the person
     */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    /**
     * Sets id of the person
     * @param id Id of the person
     */
    public void setId(long id){this.id = id;}

    /**
     * Gets first name of the person
     * @return First name of the person
     */
    @Basic
    public String getFirstName(){
        return firstName;
    }

    /**
     * Sets first name of the person
     * @param firstName First name of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name of the person
     * @return Lsat name of the person
     */
    @Basic
    public String getLastName(){
        return lastName;
    }

    /**
     * Sets last name of the person
     * @param lastName Last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets reference to supporter
     * @return Reference to supporter
     */
    @OneToOne
    public Supporter getSupporter(){
        return supporter;
    }

    /**
     * Sets reference to supporter
     * @param supporter Reference to supporter
     */
    public void setSupporter(Supporter supporter) {
        if(this.supporter != null)
            PersonController.deleteSupporterFromDb(supporter);
        this.supporter = supporter;
    }

    /**
     * Gets reference to worker
     * @return Reference to worker
     */
    @OneToOne
    public Worker getWorker() {
        return worker;
    }

    /**
     * Sets reference to worker
     * @param worker Reference to worker
     */
    public void setWorker(Worker worker) {
        if(this.worker != null && worker != null)
            PersonController.deleteWorkerFromDb(this.worker);
        this.worker = worker;
    }

    /**
     * Gets first and last name of person
     * @return First and last name of person
     */
    @Transient
    public String getName() {
        return getFirstName() + " " + getLastName();
    }
}
