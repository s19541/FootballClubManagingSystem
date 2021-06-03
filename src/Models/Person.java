package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {
    private long id;
    private String firstName;
    private String lastName;

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

    @Transient
    public String getName() {
        return getFirstName() + " " + getLastName();
    }
}
