package com.example.staffapp;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

//Denna klass körs på egen tråd, ansvarig för att hämta senaste data från API:ett varje sekund
public class GetApi extends AsyncTask<Void, Void, Void> {
    /**FETCHED JSON FOR ALL TABLES IN DATABASE*/
    private String jsonDataMenuTable;
    private String jsonDataBillTable;
    private String jsonTimeTable;
    private String jsonBuyOrderTable;
    private String jsonDataEmployeeTable;
    private String jsonDataDrinkTable;
    private String jsonDataReservationTable;
    private String jsonDataDessertTable;
    private String jsonDataAppetizerTable;
    private String jsonDataIngredientTable;
    private String jsonDataInventoryTable;
    private String jsonDataBuyOrderHasDrinkTable;
    private String jsonDataBuyOrderHasLunchAndMenuTable;
    private String jsonDataBuyOrderHasDessertTable;
    private String jsonDataBuyOrderHasAppetizerTable;
    private String jsonDataMenuHasIngredientTable;

    /**URL TO ALL TABLES IN DATABASE*/
    private final String BASE_URL = "http://192.168.1.35:8080/";
    private final String BASE_URL_SUFFIX = "WebApplication1/webresources/package_db.";
    private final String MENU_TABLE_URL = BASE_URL_SUFFIX+"menu/";
    private final String BILL_TABLE_URL = BASE_URL_SUFFIX+"bill/";
    private final String TIME_TABLE_URL = BASE_URL_SUFFIX+"timetable/";
    private final String BUY_ORDER_TABLE_URL = BASE_URL_SUFFIX+"buyorder/";
    private final String EMPLOYEE_TABLE_URL = BASE_URL_SUFFIX+"employee/";
    private final String DRINK_TABLE_URL = BASE_URL_SUFFIX+"drink/";
    private final String RESERVATION_TABLE_URL = BASE_URL_SUFFIX+"reservation/";
    private final String DESSERT_TABLE_URL = BASE_URL_SUFFIX+"dessert/";
    private final String APPETIZER_TABLE_URL = BASE_URL_SUFFIX+"appetizer/";
    private final String INGREDIENT_TABLE_URL = BASE_URL_SUFFIX+"ingredient/";
    private final String INVENTORY_TABLE_URL = BASE_URL_SUFFIX+"inventory/";
    private final String BUY_ORDER_HAS_DRINK_TABLE_URL = BASE_URL_SUFFIX+"buyorderhasdrink/";
    private final String BUY_ORDER_HAS_MENU_TABLE_URL = BASE_URL_SUFFIX+"buyorderhasmenu/";
    private final String BUY_ORDER_HAS_APPETIZER_TABLE_URL = BASE_URL_SUFFIX+"buyorderhasappetizer/";
    private final String BUY_ORDER_HAS_DESSERT_TABLE_URL = BASE_URL_SUFFIX+"buyorderhasdessert/";
    private final String MENU_HAS_INGREDIENT_TABLE_URL =  BASE_URL_SUFFIX+"menuhasingredient/";



    /**URL OBJECTS*/
    private URL menuTableUrl;
    private URL billTableUrl;
    private URL timeTableUrl;
    private URL buyOrderTableUrl;
    private URL employeeTableUrl;
    private URL drinkTableUrl;
    private URL reservationTableUrl;
    private URL dessertTableUrl;
    private URL appetizerTableUrl;
    private URL ingredientTableUrl;
    private URL inventoryTableUrl;
    private URL buyOrderHasDrinkTableUrl;
    private URL buyOrderHasLunchAndMenuTableUrl;
    private URL buyOrderHasAppetizerTableUrl;
    private URL buyOrderHasDessertTableUrl;
    private URL menuHasIngredientTableUrl;

    /**Connect to server*/
    //private ConnectToDatabase connect;

    /**Controll*/
    boolean firstTimeThreadIsDone;

    /**Constructor*/
    public GetApi()
    {
        jsonDataMenuTable = "";
        jsonDataBillTable = "";
        jsonTimeTable = "";
        jsonBuyOrderTable = "";
        jsonDataEmployeeTable = "";
        jsonDataDrinkTable = "";
        jsonDataReservationTable = "";
        jsonDataDessertTable = "";
        jsonDataAppetizerTable = "";
        jsonDataIngredientTable = "";
        jsonDataInventoryTable = "";
        jsonDataBuyOrderHasDrinkTable = "";
        jsonDataBuyOrderHasLunchAndMenuTable = "";
        jsonDataMenuHasIngredientTable = "";
        jsonDataBuyOrderHasAppetizerTable = "";
        jsonDataBuyOrderHasDessertTable = "";
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        try {
            menuTableUrl = new URL(BASE_URL+MENU_TABLE_URL);
            billTableUrl = new URL(BASE_URL+BILL_TABLE_URL);
            timeTableUrl = new URL(BASE_URL+TIME_TABLE_URL);
            buyOrderTableUrl = new URL(BASE_URL+BUY_ORDER_TABLE_URL);
            employeeTableUrl = new URL(BASE_URL+EMPLOYEE_TABLE_URL);
            drinkTableUrl = new URL(BASE_URL+DRINK_TABLE_URL);
            reservationTableUrl = new URL(BASE_URL+RESERVATION_TABLE_URL);
            dessertTableUrl = new URL(BASE_URL+DESSERT_TABLE_URL);
            appetizerTableUrl = new URL(BASE_URL+APPETIZER_TABLE_URL);
            ingredientTableUrl = new URL(BASE_URL+INGREDIENT_TABLE_URL);
            buyOrderHasDrinkTableUrl = new URL(BASE_URL+BUY_ORDER_HAS_DRINK_TABLE_URL);
            buyOrderHasLunchAndMenuTableUrl = new URL(BASE_URL+BUY_ORDER_HAS_MENU_TABLE_URL);
            buyOrderHasAppetizerTableUrl = new URL(BASE_URL+BUY_ORDER_HAS_APPETIZER_TABLE_URL);
            buyOrderHasDessertTableUrl = new URL(BASE_URL+BUY_ORDER_HAS_DESSERT_TABLE_URL);
            menuHasIngredientTableUrl = new URL(BASE_URL+MENU_HAS_INGREDIENT_TABLE_URL);
            inventoryTableUrl = new URL(BASE_URL+INVENTORY_TABLE_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        firstTimeThreadIsDone = true;

        while(true)
        {
            fetchAllTables();
            //Vi pausar tråden 0.5 sekund.
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(firstTimeThreadIsDone == true)
                MainActivity.api.init(); //Aktivera andra trådarna som är beroende av denna när denna tråden kört klart första gången
            firstTimeThreadIsDone = false;
        }
    }

    private void fetchAllTables()
    {
        //Menu Table
        jsonDataMenuTable = getJson(menuTableUrl);
        //System.out.println(jsonDataMenuTable);

        //Bill Table
        jsonDataBillTable = getJson(billTableUrl);
        //System.out.println(jsonDataBillTable);

        //Reservation Table
        jsonDataReservationTable = getJson(reservationTableUrl);
        //System.out.println(jsonDataReservationTable);

        //Dessert Table
        jsonDataDessertTable = getJson(dessertTableUrl);
        //System.out.println(jsonDataReservationTable);

        //Appetizer Table
        jsonDataAppetizerTable = getJson(appetizerTableUrl);
        //System.out.println(jsonDataReservationTable);

        //Ingredient Table
        jsonDataIngredientTable = getJson(ingredientTableUrl);
        //System.out.println(jsonDataReservationTable);

        //Inventory Table
        jsonDataInventoryTable = getJson(inventoryTableUrl);
        //System.out.println(jsonDataInventoryTable);

        //Time Table
        jsonTimeTable = getJson(timeTableUrl);
        //System.out.println(jsonTimeTable);

        //BuyOrder Table
        jsonBuyOrderTable = getJson(buyOrderTableUrl);
        //System.out.println(jsonBuyOrderTable);

        //Employee Table
        jsonDataEmployeeTable = getJson(employeeTableUrl);
        //System.out.println(jsonDataEmployeeTable);

        //Drink Table
        jsonDataDrinkTable = getJson(drinkTableUrl);
        //System.out.println(jsonDataDrinkTable);

        //BuyOrderHasDrinkTableClass Table
        jsonDataBuyOrderHasDrinkTable = getJson(buyOrderHasDrinkTableUrl);
        //System.out.println(jsonDataBuyOrderHasDrinkTable);

        //BuyOrderHasLunchAndMenu Table
        jsonDataBuyOrderHasLunchAndMenuTable = getJson(buyOrderHasLunchAndMenuTableUrl);
        //System.out.println(jsonDataBuyOrderHasLunchAndMenuTable);

        //jsonDataBuyOrderHasAppetizerTable Table
        jsonDataBuyOrderHasAppetizerTable = getJson(buyOrderHasAppetizerTableUrl);
        //System.out.println(jsonDataBuyOrderHasAppetizerTable);

        //jsonDataBuyOrderHasDessertTable Table
        jsonDataBuyOrderHasDessertTable = getJson(buyOrderHasDessertTableUrl);
        //System.out.println(jsonDataBuyOrderHasDessertTable);

        //MenuHasIngredient Table
        jsonDataMenuHasIngredientTable = getJson(menuHasIngredientTableUrl);
        //System.out.println(jsonDataMenuHasIngredientTable);
    }

    private String getJson(URL url)
    {
        String jsonData = "";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //Anslut till API server
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); //Vi gör så att vi får all data i tabellen i JSON format
            httpURLConnection.setRequestProperty("Accept", "application/json"); //Vi gör så att vi får all data i tabellen i JSON format
            //System.out.println("ERROR: " + httpURLConnection.getResponseCode()); //Debug, skriver ut error vi får från servern vi försöker ansluta till
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            jsonData = ""; //Vi måste tömma då uppdaterad data hämtas varje sek.
            while (line != null)
            {
                line = bufferedReader.readLine();//Hämta rad för rad av JSON filen från url:en och lagra i variabeln jsonData.
                jsonData += line;
            }
            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    /**GETTERS**/
    public int getLastBuyOrderRecordId()
    {
        int id = 0;
        try {
            Thread.sleep(1000); //Vi väntar 1 sek så vi låter API:ett först hämta senaste records från bill tabellen i databasen.
            JSONArray jArr = new JSONArray(jsonBuyOrderTable);
            JSONObject obje= jArr.getJSONObject(jArr.length()-1); //Vi hämtar bara senaste skapade recorden som finns i BuyOrder tabellen
            id = Integer.parseInt(obje.getString("buyorderid"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getLastBillRecordId()
    {
        int id = 0;
        try {
            Thread.sleep(1000); //Vi väntar 1 sek så vi låter API:ett först hämta senaste records från bill tabellen i databasen.
            JSONArray jArr = new JSONArray(jsonDataBillTable);
            JSONObject obje= jArr.getJSONObject(jArr.length()-1); //Vi hämtar bara senaste skapade recorden som finns i BuyOrder tabellen
            id = Integer.parseInt(obje.getString("billid"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<String> getBuyOrderRecordWithDiningTableNr(int diningTableNr)
    {
        ArrayList<String> arr = new ArrayList<>();
        int id = 0;
        try {
            JSONArray jArr = new JSONArray(jsonBuyOrderTable);
            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                int diningTableNr_ = Integer.parseInt(obje.getString("diningtableid"));
                String statusDessert = obje.getString("statusdessert");
                String statusAppetizer = obje.getString("statusappetizer");
                String statusMenu = obje.getString("statusmenu");
                if(diningTableNr_ == diningTableNr &&
                        (
                            ( statusDessert.equals("Preparing") || statusDessert.equals("Done") ) ||
                            ( statusAppetizer.equals("Preparing") || statusAppetizer.equals("Done") ) ||
                            ( statusMenu.equals("Preparing") || statusMenu.equals("Done") )
                        )
                )
                {
                    arr.add(obje.getString("buyorderid"));
                    arr.add(obje.getString("diningtableid"));
                    arr.add(obje.getString("billid"));
                    arr.add(obje.getString("notes"));
                    arr.add(obje.getString("statusmenu"));
                    arr.add(obje.getString("statusdessert"));
                    arr.add(obje.getString("statusappetizer"));
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public String getJsonDataMenuTable() { return jsonDataMenuTable; }

    public String getJsonDataBillTable() { return jsonDataBillTable; }

    public String getJsonTimeTable() { return jsonTimeTable; }

    public String getJsonBuyOrderTable() { return jsonBuyOrderTable; }

    public String getJsonDataEmployeeTable() { return jsonDataEmployeeTable; }

    public String getJsonDataDrinkTable() { return jsonDataDrinkTable; }

    public String getJsonDataReservationTable() { return jsonDataReservationTable; }

    public String getJsonDataBuyOrderHasDrinkTable() { return jsonDataBuyOrderHasDrinkTable; }

    public String getJsonDataBuyOrderHasLunchAndMenuTable() { return jsonDataBuyOrderHasLunchAndMenuTable; }

    public String getJsonDataDessertTable() { return jsonDataDessertTable; }

    public String getJsonDataAppetizerTable() { return jsonDataAppetizerTable; }

    public String getJsonDataIngredientTable() { return jsonDataIngredientTable; }

    public String getJsonDataMenuHasIngredientTable() { return jsonDataMenuHasIngredientTable; }

    public String getJsonDataInventoryTable() { return jsonDataInventoryTable; }

    public String getJsonDataBuyOrderHasDessertTable() { return jsonDataBuyOrderHasDessertTable; }

    public String getJsonDataBuyOrderHasAppetizerTable() { return jsonDataBuyOrderHasAppetizerTable; }
}