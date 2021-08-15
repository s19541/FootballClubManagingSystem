package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Supporter")
public class Supporter{
    private long id;
    private boolean haveSupporterCard;
    private List<Match> matches = new ArrayList<>();
    private Person person;

    public Supporter(){}

    private Supporter(Person person, boolean haveSupporterCard){
        this.person = person;
        this.haveSupporterCard = haveSupporterCard;
    }
    @Transient
    public static Supporter createSupporter(Person person, boolean haveSupporterCard) throws Exception{
        if(person == null)
            throw new Exception("The given person does not exist!");
        Supporter newSupporter = new Supporter(person, haveSupporterCard);
        person.setSupporter(newSupporter);
        return  newSupporter;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    public void setId(long id){this.id = id;}

    @Basic
    public boolean getHaveSupporterCard(){
        return  haveSupporterCard;
    }

    public void setHaveSupporterCard(boolean haveSupporterCard){
        this.haveSupporterCard = haveSupporterCard;
    }

    @ManyToMany(mappedBy = "supporters")
    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) throws Exception{
        if(match.getSupporters().size() >= Match.maxSupportersNumber)
            throw new Exception("Too much supporters on match (maximum number is: " + Match.maxSupportersNumber + ")");
        if(!matches.contains(match)) {
            getMatches().add(match);
            match.getSupporters().add(this);
        }
    }

    public void removeMatch(Match match) {
        getMatches().remove(match);
        match.getSupporters().remove(this);
    }

    @OneToOne
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
