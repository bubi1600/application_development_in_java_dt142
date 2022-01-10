package com.example.staffapp.api.database.table.classes;



/**
 * Det värderna som skickas in i konstruktorns parametrar när denna klass skapas i main är de värdena
 * som kommer att skickas till tabel "menu" i databasen för att skapa ny record.
 */
//
public class MenuTableClass {
    private String menuId;
    private Integer price;
    private Integer time;

    public MenuTableClass(String menuId , Integer price, Integer time) {
        this.menuId = menuId; //Det som går in här kommer bli värdet för ID:n i nya recorden i tabellen "menu"
        this.price = price; //Det som går in här kommer bli värdet för priset i nya recorden i tabellen "menu"
        this.time = time; //Det som går in här kommer bli värdet för tiden i nya recorden i tabellen "menu"
    }
}
