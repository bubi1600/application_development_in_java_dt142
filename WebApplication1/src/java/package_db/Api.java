/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package package_db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import javax.ws.rs.NotSupportedException;
import org.json.simple.JSONArray;


/**
 *
 * @author omarm
 */
@Named(value = "api")
@Dependent
public class Api {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
   List<Menu> list_m = new ArrayList<>();
   List<Lunch> list_l = new ArrayList<>();
   List<Event> list_e = new ArrayList<>();
   List<Appetizer> list_a = new ArrayList<>();
   List<Dessert> list_d = new ArrayList<>();
   List<Employee> list_em = new ArrayList<>();
    /**
     * Creates a new instance of Api
     */
    public Api() {
    }

public List getMenu()
    {
        JSONArray jArray = new JSONArray();
        TypedQuery<Menu> myQuerry = em.createNamedQuery("Menu.findAll", Menu.class);
        List <Menu> resultList = myQuerry.getResultList();
        for(Menu menus : resultList)
            list_m.add(menus);
        return list_m;
    }
    
    public List getLunch()
    {
        JSONArray jArray = new JSONArray();
        TypedQuery<Lunch> myQuerry = em.createNamedQuery("Lunch.findAll", Lunch.class);
        
        List <Lunch> resultList = myQuerry.getResultList();
        for(Lunch lunchs : resultList)
            list_l.add(lunchs);
        return list_l;
    }
    
    public List getAppetizer()
    {
        JSONArray jArray = new JSONArray();
        TypedQuery<Appetizer> myQuerry = em.createNamedQuery("Appetizer.findAll", Appetizer.class);
        List <Appetizer> resultList = myQuerry.getResultList();
        for(Appetizer appetizer : resultList)
            list_a.add(appetizer);
        return list_a;
    }
    
        public List getDessert()
    {
        JSONArray jArray = new JSONArray();
        TypedQuery<Dessert> myQuerry = em.createNamedQuery("Dessert.findAll", Dessert.class);
        List <Dessert> resultList = myQuerry.getResultList();
        for(Dessert dessert : resultList)
            list_d.add(dessert);
        return list_d;
    }
    
         public List getEmployee()
    {
        JSONArray jArray = new JSONArray();
        TypedQuery<Employee> myQuerry = em.createNamedQuery("Employee.findAll", Employee.class);
        List <Employee> resultList = myQuerry.getResultList();
        for(Employee employee : resultList)
            list_em.add(employee);
        return list_em;
    }
        
    
    //Todays Lunch - START
    public String getDayOfWeekString(){
        int dayOfWeek = DayOfWeekInt();
        return DayToString(dayOfWeek);
    }

    private String DayToString(int dayOfWeek) {

        Vector<String> weekDays = new Vector<>();
        weekDays.add("Måndag");
        weekDays.add("Tisdag");
        weekDays.add("Onsdag");
        weekDays.add("Torsdag");
        weekDays.add("Fredag");
      
        switch(dayOfWeek) {
            // Obs! 0 is sunday and 6 is saturday
            case 2:
                return weekDays.get(0);
            case 3:
                return weekDays.get(1);
            case 4:
                return weekDays.get(2);
            case 5:
                return weekDays.get(3);
            case 6:
                return weekDays.get(4);
        default:
            return "Helg!";
        }
    }

    private int DayOfWeekInt() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_WEEK);
    }
    
    public List<Lunch> getLunchToday()
    {
        // Used to display todays lunch offer
        Calendar c = Calendar.getInstance();
        String day = DayToString(c.get(Calendar.DAY_OF_WEEK));
        List<Lunch> resultList = em.createNamedQuery("Lunch.findByDayid", Lunch.class).setParameter("dayid", day).getResultList();
        return resultList;
    }
    //Todays Lunch - END
        
    public List getEvent()
    {
        JSONArray jArray = new JSONArray();
        TypedQuery<Event> myQuerry = em.createNamedQuery("Event.findAll", Event.class);
        List <Event> resultList = myQuerry.getResultList();
        for(Event event : resultList)
            list_e.add(event);
        return list_e;
    }
     



        
          public String delete_m(String menuid) throws javax.transaction.RollbackException, javax.transaction.NotSupportedException{
     
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Menu.deleteByName", Menu.class).setParameter("menuid", menuid).executeUpdate();
            utx.commit();
            
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/admin.xhtml";
    }
          
         public String delete_l(String dayid ) throws javax.transaction.RollbackException, javax.transaction.NotSupportedException{
     
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Lunch.deleteByName", Lunch.class).setParameter("dayid", dayid ).executeUpdate();
            utx.commit();
            
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/admin.xhtml";
    }
         
       public String delete_e(Integer eventid ) throws javax.transaction.RollbackException, javax.transaction.NotSupportedException{
     
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Event.deleteByName", Event.class).setParameter("eventid", eventid ).executeUpdate();
            utx.commit();
            
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/admin.xhtml";
    }
       
                
       public String delete_a(String appetizerid ) throws javax.transaction.RollbackException, javax.transaction.NotSupportedException{
     
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Appetizer.deleteByName", Appetizer.class).setParameter("appetizerid", appetizerid ).executeUpdate();
            utx.commit();
            
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(Appetizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/admin.xhtml";
    }
       
        public String delete_d(String dessertid ) throws javax.transaction.RollbackException, javax.transaction.NotSupportedException{
     
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Dessert.deleteByName", Dessert.class).setParameter("dessertid", dessertid ).executeUpdate();
            utx.commit();
            
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(Dessert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/admin.xhtml";
    }
        
          public String delete_em(Integer employeeid ) throws javax.transaction.RollbackException, javax.transaction.NotSupportedException{
     
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Employee.deleteByName", Employee.class).setParameter("employeeid", employeeid ).executeUpdate();
            utx.commit();
            
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/admin.xhtml";
    }
       

    
        public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    } 
}
