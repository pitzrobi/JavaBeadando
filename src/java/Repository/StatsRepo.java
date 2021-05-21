
package Repository;

import Modell.Database;
import Modell.Stats;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;


public class StatsRepo {
    public static boolean addNewStat(Stats s){
    
        try{
            EntityManager em = Database.getDbCon();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewStat");

            spq.registerStoredProcedureParameter("playerIdIn", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("pointIn", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passIn", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("blockIn", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("statusIn", Integer.class, ParameterMode.IN);


            spq.setParameter("playerIdIn", s.getPlayerId());
            spq.setParameter("pointIn", s.getPoints());
            spq.setParameter("passIn", s.getPass());
            spq.setParameter("blockIn", s.getBlock());
            spq.setParameter("statusIn", s.getStatus());

            spq.execute();

            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        
        
        
    }
    
    public static List<Stats> getAllActiceStats(){
        List<Stats> result = new ArrayList();
    try{
        EntityManager em = Database.getDbCon();
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveStats");
        
        List<Object[]> stats = spq.getResultList();
        for(Object[] stat : stats){
            int id = Integer.parseInt(stat[0].toString());
            Stats s = em.find(Stats.class, id);
            result.add(s);
        }
        
    }
    catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    finally{
        return result;
    }
    }
    
    public static boolean logdeleteStat(Stats s){
        try{
            EntityManager em = Database.getDbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteStatById");
            spq.registerStoredProcedureParameter("idIn", Integer.class, ParameterMode.IN);
            spq.setParameter("idIn", s.getId());
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
    public static boolean updateStat(Stats s){
        
        try{
            EntityManager em = Database.getDbCon();
            em.getTransaction().begin();
            
            Stats uj = em.find(Stats.class, s.getId());
            uj.setPoints(s.getPoints());
            uj.setPass(s.getPass());
            uj.setBlock(s.getBlock());
            
            
            em.getTransaction().commit();
            return true;
        }
        
        catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return false;
        }
        
        
    }
    
    public static boolean getscore(Stats s){
        
        try{
            EntityManager em = Database.getDbCon();
            
            
            Stats uj = em.find(Stats.class, s.getId());
            
            return true;
        }
        
        catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return false;
        }
    
    
    
    }
}