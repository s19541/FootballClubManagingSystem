package Controllers;

import Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing person data
 */
public class PersonController {

    /**
     * method which delete worker from db
     * @param worker worker who is deleted from db
     */
    public static void deleteWorkerFromDb(Worker worker){
        try{
            DbConnectionController.session.beginTransaction();
            Person person = worker.getPerson();
            person.setWorker(null);
            switch (worker.getClass().getName()){
                case "Model.Footballer":
                    deleteFootballerRelations((Footballer) worker);
                    break;
                case "Model.Coach":
                    deleteCoachRelations((Coach) worker);
                    break;
                case "Model.Dietician":
                    deleteDieticianRelations((Dietician) worker);
                    break;
            }
            DbConnectionController.session.update(person);
            DbConnectionController.session.delete(worker);
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * method which delete supporter from db
     * @param supporter supporter who is deleted from db
     */
    public static void deleteSupporterFromDb(Supporter supporter){
        try{
            DbConnectionController.session.beginTransaction();
            Person person = supporter.getPerson();
            person.setWorker(null);
            supporter.getMatches().forEach(o->o.removeSupporter(supporter));
            DbConnectionController.session.update(person);
            DbConnectionController.session.delete(supporter);
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * method which delete footballer relations in db
     * @param footballer footballer whose relations are deleted from db
     */
    public static void deleteFootballerRelations(Footballer footballer){
        footballer.getTrainings().forEach(o -> o.removeFootballer(footballer));
        footballer.getDieticians().forEach(o -> o.removeFootballer(footballer));
        footballer.getMatches().forEach(o -> o.getFootballers().remove(footballer));
    }

    /**
     * method which delete dietician relations in db
     * @param dietician dietician whose relations are deleted from db
     */
    public static void deleteDieticianRelations(Dietician dietician){
        dietician.getFootballers().forEach(o -> o.removeDietician(dietician));
    }

    /**
     * method which delete coach relations in db
     * @param coach coach whose relations are deleted from db
     */
    public static void deleteCoachRelations(Coach coach){
        coach.getTrainings().forEach(o -> o.removeCoach(coach));
    }

    /**
     * Gets all footballers from db
     * @return list of all footballers from db
     */
    public static List<Footballer> getFootballers(){
        List<Footballer> footballers = new ArrayList<>();
        try{
            DbConnectionController.session.beginTransaction();
            footballers = DbConnectionController.session.createQuery("from Footballer").list();
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return footballers;
    }
}
