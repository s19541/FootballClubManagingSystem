package Controllers;

import Models.Worker;

public class PersonController {
    public static void deleteWorkerFromDb(Worker worker){
        try{
            DbConnectionController.session.beginTransaction();
            DbConnectionController.session.delete(worker);
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
