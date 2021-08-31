package Model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents supporter
 */
@Entity(name = "Supporter")
public class Supporter{
    /**
     * Unique id of the supporter
     */
    private long id;
    /**
     * If fan have supporter's card
     */
    private boolean haveSupporterCard;
    /**
     * List of matches participated by supporter
     */
    private List<Match> matches = new ArrayList<>();
    /**
     * Extension to person
     */
    private Person person;

    /**
     * Non-parameterized constructor
     */
    public Supporter(){}

    /**
     * Parameterized constructor
     * @param person Extension to person
     * @param haveSupporterCard If fan have supporter's card
     */
    private Supporter(Person person, boolean haveSupporterCard){
        this.person = person;
        this.haveSupporterCard = haveSupporterCard;
    }

    /**
     * Creates new supporter
     * @param person Extension to person
     * @param haveSupporterCard If fan have supporter's card
     * @return New supporter
     * @throws Exception Throws exception if person does not exist
     */
    @Transient
    public static Supporter createSupporter(Person person, boolean haveSupporterCard) throws Exception{
        if(person == null)
            throw new Exception("The given person does not exist!");
        Supporter newSupporter = new Supporter(person, haveSupporterCard);
        person.setSupporter(newSupporter);
        return  newSupporter;
    }

    /**
     * Gets id of the supporter
     * @return Id of the supporter
     */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    /**
     * Sets id of the supporter
     * @param id Id of the supporter
     */
    public void setId(long id){this.id = id;}

    /**
     * Gets if fan have supporter's card
     * @return If fan have supporter's card
     */
    @Basic
    public boolean getHaveSupporterCard(){
        return  haveSupporterCard;
    }

    /**
     * Sets if fan have supporter's card
     * @param haveSupporterCard If fan have supporter's card
     */
    public void setHaveSupporterCard(boolean haveSupporterCard){
        this.haveSupporterCard = haveSupporterCard;
    }

    /**
     * Gets list of matches participated by supporter
     * @return List of matches participated by supporter
     */
    @ManyToMany(mappedBy = "supporters")
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * Sets list of matches participated by supporter
     * @param matches List of matches participated by supporter
     */
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    /**
     * Adds match to list of matches participated by supporter
     * @param match Match to add
     * @throws Exception Throws exception if supporter number is maximum
     */
    public void addMatch(Match match) throws Exception{
        if(match.getSupporters().size() >= Match.maxSupportersNumber)
            throw new Exception("Too much supporters on match (maximum number is: " + Match.maxSupportersNumber + ")");
        if(!matches.contains(match)) {
            getMatches().add(match);
            match.getSupporters().add(this);
        }
    }

    /**
     * removes match from list of matches participated by supporter
     * @param match List of matches participated by supporter
     */
    public void removeMatch(Match match) {
        getMatches().remove(match);
        match.getSupporters().remove(this);
    }

    /**
     * Gets extension of person
     * @return Extension of person
     */
    @OneToOne
    public Person getPerson() {
        return person;
    }

    /**
     * Sets extension of person
     * @param person Extension of person
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
