/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package_db.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author omarm
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(package_db.service.AppetizerFacadeREST.class);
        resources.add(package_db.service.BillFacadeREST.class);
        resources.add(package_db.service.BuyorderFacadeREST.class);
        resources.add(package_db.service.BuyorderhasappetizerFacadeREST.class);
        resources.add(package_db.service.BuyorderhasdessertFacadeREST.class);
        resources.add(package_db.service.BuyorderhasdrinkFacadeREST.class);
        resources.add(package_db.service.BuyorderhasmenuFacadeREST.class);
        resources.add(package_db.service.DessertFacadeREST.class);
        resources.add(package_db.service.DiningtableFacadeREST.class);
        resources.add(package_db.service.DrinkFacadeREST.class);
        resources.add(package_db.service.EmployeeFacadeREST.class);
        resources.add(package_db.service.EventFacadeREST.class);
        resources.add(package_db.service.IngredientFacadeREST.class);
        resources.add(package_db.service.InventoryFacadeREST.class);
        resources.add(package_db.service.LunchFacadeREST.class);
        resources.add(package_db.service.LunchhasingredientFacadeREST.class);
        resources.add(package_db.service.MenuFacadeREST.class);
        resources.add(package_db.service.MenuhasingredientFacadeREST.class);
        resources.add(package_db.service.ReservationFacadeREST.class);
        resources.add(package_db.service.TimetableFacadeREST.class);
    }
    
}
