
package Repository;


import Modell.Database;
import Modell.Players;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class PlayersRepo {
    
    public static boolean addNewTeam(Players p){
    
    try{
        EntityManager em = Database.getDbCon();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewPlayer");
        
        spq.registerStoredProcedureParameter("nameIn", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("positionIn", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("birthIn", Date.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("teamIdIn", Integer.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("statusIn", Integer.class, ParameterMode.IN);
        
        spq.setParameter("nameIn", p.getName());
        spq.setParameter("positionIn", p.getPosition());
        spq.setParameter("birthIn", p.getBirth());
        spq.setParameter("teamIdIn", p.getTeamId());
        spq.setParameter("statusIn", p.getStatus());
        
        spq.execute();
        
        return true;
    }
    catch(Exception ex){
        System.out.println(ex.getMessage());
        return false;
    }
    
    
    }
    
    
    public static List<Players> getAllActicePlayers(){
        List<Players> result = new ArrayList();
    try{
        EntityManager em = Database.getDbCon();
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActivePlayers");
        
        List<Object[]> players = spq.getResultList();
        for(Object[] player : players){
            int id = Integer.parseInt(player[0].toString());
            Players p = em.find(Players.class, id);
            result.add(p);
        }
        
    }
    catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    finally{
        return result;
    }
    }
    
    
    public static boolean logdeletePlayer(Players p){
        try{
            EntityManager em = Database.getDbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeletePlayerById");
            spq.registerStoredProcedureParameter("idIn", Integer.class, ParameterMode.IN);
            spq.setParameter("idIn", p.getId());
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean updatePlayer(Players p){
        
        try{
            EntityManager em = Database.getDbCon();
            em.getTransaction().begin();
            
            Players uj = em.find(Players.class, p.getId());
            uj.setPosition(p.getPosition());
            
            em.getTransaction().commit();
            return true;
        }
        
        catch(Exception ex){
            
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static boolean changePlayerTeam(Players p){
        
        try{
            EntityManager em = Database.getDbCon();
            em.getTransaction().begin();
            
            Players uj = em.find(Players.class, p.getId());
            uj.setTeamId(p.getTeamId());
            
            em.getTransaction().commit();
            return true;
        }
        
        catch(Exception ex){
            
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
    
    
}
