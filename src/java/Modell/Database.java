
package Modell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Database {
    public static EntityManager getDbCon(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaBeadandoPU");
        EntityManager em = emf.createEntityManager();
        
        return em;
    }
    
}
