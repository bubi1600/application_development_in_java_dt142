package com.example.staffapp.api.database.table.interfaces;
import com.example.staffapp.api.database.table.classes.MenuTableClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**POST requests är det som används när man vill skicka data till en tabell i databasen**/

/**
 * Denna klassen sköter POST och GET requests, alltså för skicka ny data in tabellen
 * "menu" i databasen och även för att hämta data från tabellen "menu" i databasen.
 */
public interface MenuTableInterface {
    final String MENU_URL = "WebApplication1/webresources/package_db.menu/";

    /**POST REQUEST*/
    /**
     * Denna metod skapar ny record i tabellen menu i databsen, vi skickar en MenuTableClass objekt till den.
     *
     * @param menuTableClass är en menuTableClass objekt av klassen MenuTableClass.java
     * @return Den returnerar server-respons koden, alltså om det misslyckades eller lyckades.
     * Dessutom returneras de värdena som skickades till tabellen.
     * <p>
     * "webbsite/webresources/packagedb.menu/" <--Denna delen är specifika vägen till menu tabellen
     * som vi kan se om vi går in på kör en "Test RESTful Web Service" i Netbeans och väljer
     * menu tabellen. Alla tabeller har sina egna specifika vägar.
     */
    @POST(MENU_URL)
    Call<MenuTableClass> createMenuPost(@Body MenuTableClass menuTableClass);
}
