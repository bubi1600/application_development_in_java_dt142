package com.example.staffapp;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * KVAR ATT PARSA:
 * TimeTable_Table
 * Employee_Table
 * Reservation_Table
 * BuyOrderHas
 * */

public class ParseApi extends AsyncTask<Void, Void, Void>
{
    private Api api;
    private List<TableMenu> menuList; //Lista som ska innehålla alla menyer och deras prisar, namn osv från tabellen
    private List<TableDrinks> drinkList;
    private List<TableBuyOrder> buyOrderList; //Lista som ska innehålla de ordrar som har status HÄMTA eller TILLAGAS
    private List<TableBill> billList;
    private List<TableBuyOrderHasDrink> buyOrderHasDrinkList;
    private List<TableBuyOrderHasLunchAndMenu> buyOrderHasLunchAndMenuList;
    private List<TableBuyOrderHasDessert> buyOrderHasDessertList;
    private List<TableBuyOrderHasAppetizer> buyOrderHasAppetizerList;
    private List<TableDessert> dessertList;
    private List<TableAppetizer> appetizerList;
    private List<TableIngredient> ingredientList;
    private List<TableReservation> reservationList;
    private List<TableMenuHasIngredient> menuHasIngredientList;
    private List<TableInventory> inventoryList;
    private List<TableEmployee> employeeList;
    private List<TableTimeTable> timeTableList;

    /**
     * Constructor
     */
    public ParseApi() { api = MainActivity.api;}

    @Override
    protected Void doInBackground(Void... voids)
    {
        api = MainActivity.api;
        //Då appen måste hela tiden vara uppdaterad med senaste data från databasen så sätter vi allt detta i en while-loop
        //som varje sekund hämtar API:ett på nytt.
        while(true)
        {
            parseMenuJson(api.GETApi().getJsonDataMenuTable());
            parseDrinksJson(api.GETApi().getJsonDataDrinkTable());
            parseBuyOrderJson(api.GETApi().getJsonBuyOrderTable());
            parseBillJson(api.GETApi().getJsonDataBillTable());
            parseBuyOrderHasDrinksJson(api.GETApi().getJsonDataBuyOrderHasDrinkTable());
            parseBuyOrderHasLunchAndMenuJson(api.GETApi().getJsonDataBuyOrderHasLunchAndMenuTable());
            parseBuyOrderHasDessert(api.GETApi().getJsonDataBuyOrderHasDessertTable());
            parseBuyOrderHasAppetizer(api.GETApi().getJsonDataBuyOrderHasAppetizerTable());
            parseDessertJson(api.GETApi().getJsonDataDessertTable());
            parseAppetizerJson(api.GETApi().getJsonDataAppetizerTable());
            parseIngredientJson(api.GETApi().getJsonDataIngredientTable());
            parseReservationJson(api.GETApi().getJsonDataReservationTable());
            parseMenuHasIngredientJson(api.GETApi().getJsonDataMenuHasIngredientTable());
            parseInventoryJson(api.GETApi().getJsonDataInventoryTable());
            parseTimeTableJson(api.GETApi().getJsonTimeTable());
            parseEmployeeJson(api.GETApi().getJsonDataEmployeeTable());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method parses the given json file and assigns values to instance members.
     * @param jsonData is a raw json file (API)
     */
    private void parseMenuJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            menuList = new ArrayList<TableMenu>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                menuList.add(new TableMenu(
                        obje.getString("menuid"),
                        obje.getString("price"),
                        obje.getString("time")));
            }

        } catch (JSONException e) {
        e.printStackTrace();
        }

        /*for(int i=0; i<menuList.size(); i++)
        {
            System.out.println(menuList.get(i).menuName);
            System.out.println(menuList.get(i).price);
            System.out.println(menuList.get(i).CookingTime);
        }*/
    }

    private void parseBuyOrderHasDrinksJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            buyOrderHasDrinkList = new ArrayList<TableBuyOrderHasDrink>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                buyOrderHasDrinkList.add(new TableBuyOrderHasDrink(
                        obje.getString("buyorderhasdrinkid"),
                        obje.getString("quantity"),
                        obje.getString("drinkid"),
                        obje.getString("buyorderid")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for(int i=0; i<buyOrderHasDrinkList.size(); i++)
        {
            System.out.println(buyOrderHasDrinkList.get(i).tableBuyOrderHasDrinkId);
            System.out.println(buyOrderHasDrinkList.get(i).quantity);
            System.out.println(buyOrderHasDrinkList.get(i).drinkId);
            System.out.println(buyOrderHasDrinkList.get(i).buyOrderId);
        }*/

    }

    private void parseBuyOrderHasLunchAndMenuJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            buyOrderHasLunchAndMenuList = new ArrayList<TableBuyOrderHasLunchAndMenu>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                buyOrderHasLunchAndMenuList.add(new TableBuyOrderHasLunchAndMenu(
                        obje.getString("buyorderhasmenuid"),
                        obje.getString("quantity"),
                        obje.getString("menuid"),
                        obje.getString("buyorderid")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for(int i=0; i<buyOrderHasLunchAndMenuList.size(); i++)
        {
            System.out.println(buyOrderHasLunchAndMenuList.get(i).buyOrderHasLunchAndMenuId);
            System.out.println(buyOrderHasLunchAndMenuList.get(i).quantity);
            System.out.println(buyOrderHasLunchAndMenuList.get(i).foodId);
            System.out.println(buyOrderHasLunchAndMenuList.get(i).buyOrderId);
        }*/

    }

    private void parseDrinksJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            drinkList = new ArrayList<TableDrinks>(jArr.length());

            for (int i = 0; i < jArr.length(); i++) {
                JSONObject obje = jArr.getJSONObject(i);
                drinkList.add(new TableDrinks(
                        obje.getString("drinkid"),
                        obje.getString("price")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for (int i = 0; i < drinkList.size(); i++) {
            System.out.println(drinkList.get(i).drinkName);
            System.out.println(drinkList.get(i).price);
        }*/
    }

    /****/
    private void parseInventoryJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            inventoryList = new ArrayList<TableInventory>(jArr.length());

            for (int i = 0; i < jArr.length(); i++)
            {
                JSONObject obje = jArr.getJSONObject(i);
                inventoryList.add(new TableInventory(
                        obje.getString("inventorynameid"),
                        obje.getString("quantity")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for (int i = 0; i < inventoryList.size(); i++)
        {
            System.out.println(inventoryList.get(i).inventoryNameId);
            System.out.println(inventoryList.get(i).quantity);
        }*/
    }

    private void parseMenuHasIngredientJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            menuHasIngredientList = new ArrayList<TableMenuHasIngredient>(jArr.length());

            //(String ingredientId, String menuHasIngredientId, String menuId, String quantity)
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject obje = jArr.getJSONObject(i);
                menuHasIngredientList.add(new TableMenuHasIngredient(
                        obje.getString("ingredientid"),
                        obje.getString("menuhasingredientid"),
                        obje.getString("menuid"),
                        obje.getString("quantity")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for (int i = 0; i < drinkList.size(); i++) {
            System.out.println(menuHasIngredientList.get(i).ingredientId);
            System.out.println(menuHasIngredientList.get(i).menuHasIngredientId);
            System.out.println(menuHasIngredientList.get(i).menuId);
            System.out.println(menuHasIngredientList.get(i).quantity);
        }*/
    }

    private void parseReservationJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            reservationList = new ArrayList<TableReservation>(jArr.length());

            for (int i = 0; i < jArr.length(); i++) {
                JSONObject obje = jArr.getJSONObject(i);
                reservationList.add(new TableReservation(
                        obje.getString("reservationid"),
                        obje.getString("nrofpeople"),
                        obje.getString("email"),
                        obje.getString("date"),
                        obje.getString("phonenr"),
                        obje.getString("fname"),
                        obje.getString("lname"),
                        obje.getString("time")
                        ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
       /* for (int i = 0; i < reservationList.size(); i++) {
            System.out.println(reservationList.get(i).reservationId);
            System.out.println(reservationList.get(i).diningTableId);
            System.out.println(reservationList.get(i).nrOfGuests);
            System.out.println(reservationList.get(i).email);
            System.out.println(reservationList.get(i).date);
            System.out.println(reservationList.get(i).phoneNr);
            System.out.println(reservationList.get(i).fName);
            System.out.println(reservationList.get(i).lName);
            System.out.println(reservationList.get(i).time);
            System.out.println(reservationList.get(i).cancelReservationCode);
        }*/
    }

    private void parseBuyOrderJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            buyOrderList = new ArrayList<TableBuyOrder>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                buyOrderList.add(new TableBuyOrder(
                        obje.getString("notes"),
                        obje.getString("billid"),
                        obje.getString("buyorderid"),
                        obje.getString("diningtableid"),
                        obje.getString("statusdessert"),
                        obje.getString("statusappetizer"),
                        obje.getString("statusmenu")
                ));
            }
        } catch (JSONException e) {
        e.printStackTrace();
        }

        /*for(int i=0; i<buyOrderList.size(); i++)
        {
            System.out.println(buyOrderList.get(i).foodStatus);
            System.out.println(buyOrderList.get(i).notes);
            System.out.println(buyOrderList.get(i).billId);
            System.out.println(buyOrderList.get(i).buyOrderId);
            System.out.println(buyOrderList.get(i).diningTableId);
        }*/
    }

    private void parseBillJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            billList = new ArrayList<TableBill>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                billList.add(new TableBill(
                        obje.getString("totalcost"),
                        obje.getString("billid"),
                        obje.getString("paystatus")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for(int i=0; i<billList.size(); i++)
        {
            System.out.println(billList.get(i).billId);
            System.out.println(billList.get(i).billStatus);
            System.out.println(billList.get(i).totalCost);
        }*/
    }

    private void parseBuyOrderHasDessert(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            buyOrderHasDessertList = new ArrayList<TableBuyOrderHasDessert>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                buyOrderHasDessertList.add(new TableBuyOrderHasDessert(
                        obje.getString("buyorderhasdessertid"),
                        obje.getString("quantity"),
                        obje.getString("dessertid"),
                        obje.getString("buyorderid")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for(int i=0; i<dessertList.size(); i++)
        {
            System.out.println(dessertList.get(i).dessertId);
            System.out.println(dessertList.get(i).price);
        }*/
    }

    private void parseBuyOrderHasAppetizer(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            buyOrderHasAppetizerList = new ArrayList<TableBuyOrderHasAppetizer>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                buyOrderHasAppetizerList.add(new TableBuyOrderHasAppetizer(
                        obje.getString("buyorderhasappetizerid"),
                        obje.getString("quantity"),
                        obje.getString("appetizerid"),
                        obje.getString("buyorderid")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for(int i=0; i<dessertList.size(); i++)
        {
            System.out.println(dessertList.get(i).dessertId);
            System.out.println(dessertList.get(i).price);
        }*/
    }

    private void parseDessertJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            dessertList = new ArrayList<TableDessert>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                dessertList.add(new TableDessert(
                        obje.getString("dessertid"),
                        obje.getString("price"),
                        obje.getString("time")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for(int i=0; i<dessertList.size(); i++)
        {
            System.out.println(dessertList.get(i).dessertId);
            System.out.println(dessertList.get(i).price);
        }*/
    }

    private void parseAppetizerJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            appetizerList = new ArrayList<TableAppetizer>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                appetizerList.add(new TableAppetizer(
                        obje.getString("appetizerid"),
                        obje.getString("price"),
                        obje.getString("time")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for(int i=0; i<appetizerList.size(); i++)
        {
            System.out.println(appetizerList.get(i).appetizerId);
            System.out.println(appetizerList.get(i).price);
        }*/
    }

    private void parseIngredientJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            ingredientList = new ArrayList<TableIngredient>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                ingredientList.add(new TableIngredient(
                        obje.getString("ingredientid"),
                        obje.getString("inventorynameid")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for(int i=0; i<ingredientList.size(); i++)
        {
            System.out.println(ingredientList.get(i).ingredientId);
            System.out.println(ingredientList.get(i).inventoryNameId);
        }*/
    }

    private void parseTimeTableJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            timeTableList = new ArrayList<TableTimeTable>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                timeTableList.add(new TableTimeTable(
                        obje.getString("timetableid"),
                        obje.getString("employeeid"),
                        obje.getString("date"),
                        obje.getString("working")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for(int i=0; i<timeTableList.size(); i++)
        {
            System.out.println(timeTableList.get(i).employeeId);
            System.out.println(timeTableList.get(i).timeTableId);
            System.out.println(timeTableList.get(i).date);
            System.out.println(timeTableList.get(i).working);
        }*/
    }

    private void parseEmployeeJson(String jsonData)
    {
        if(isJsonEmpty(jsonData))
            return;
        try {
            JSONArray jArr = new JSONArray(jsonData);
            employeeList = new ArrayList<TableEmployee>(jArr.length());

            for(int i=0; i<jArr.length(); i++)
            {
                JSONObject obje= jArr.getJSONObject(i);
                employeeList.add(new TableEmployee(
                        obje.getString("employeeid"),
                        obje.getString("fname"),
                        obje.getString("lname"),
                        obje.getString("phonenr")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for(int i=0; i<employeeList.size(); i++)
        {
            System.out.println(employeeList.get(i).employeeId);
            System.out.println(employeeList.get(i).fName);
            System.out.println(employeeList.get(i).lName);
            System.out.println(employeeList.get(i).phoneNr);
        }*/
    }

    private Boolean isJsonEmpty(String jsonData)
    {
        if(jsonData.equals(""))
            return true;
        return false;
    }

    /**GETTERS*/
    public List<TableMenu> getMenuList() { return menuList; }

    public List<TableDrinks> getDrinkList() { return drinkList; }

    public List<TableBuyOrder> getBuyOrderList() { return buyOrderList; }

    public List<TableBill> getBillList() { return billList; }

    public List<TableBuyOrderHasDrink> getBuyOrderHasDrinkList() { return buyOrderHasDrinkList; }

    public List<TableBuyOrderHasLunchAndMenu> getBuyOrderHasLunchAndMenuList() { return buyOrderHasLunchAndMenuList; }

    public List<TableDessert> getDessertList() { return dessertList; }

    public List<TableAppetizer> getAppetizerList() { return appetizerList; }

    public List<TableIngredient> getIngredientList() { return ingredientList; }

    public List<TableReservation> getReservationList() { return reservationList; }

    public List<TableMenuHasIngredient> getMenuHasIngredientList() { return menuHasIngredientList; }

    public List<TableInventory> getInventoryList() { return inventoryList; }

    public List<TableBuyOrderHasDessert> getBuyOrderHasDessertList() { return buyOrderHasDessertList; }

    public List<TableBuyOrderHasAppetizer> getBuyOrderHasAppetizerList() { return buyOrderHasAppetizerList; }

    public List<TableEmployee> getEmployeeList() { return employeeList; }

    public List<TableTimeTable> getTimeTableList() { return timeTableList; }
}

/**STRUCTS**/
class TableMenu
{
    public String menuName;
    public String price;
    public String cookingTime;

    public TableMenu(String menuName, String price, String cookingTime)
    {
        this.menuName = menuName;
        this.price = price;
        this.cookingTime = cookingTime;
    }
}

//"Struct" för tabellen Drinks, spara en List som är av typen Drinks
class TableDrinks
{
    public String drinkName;
    public String price;

    public TableDrinks(String drinkName, String price)
    {
        this.drinkName = drinkName;
        this.price = price;
    }
}

//"Struct" för tabellen Order, spara en List som är av typen TableOrder
class TableBuyOrder
{
    public String statusDessert;
    public String statusAppetizer;
    public String statusMenu;
    public String notes;
    public String billId;
    public String buyOrderId;
    public String diningTableId;

    public TableBuyOrder(String notes, String billId, String buyOrderId, String diningTableId, String statusDessert, String statusAppetizer, String statusMenu)
    {
        this.statusDessert = statusDessert;
        this.statusAppetizer = statusAppetizer;
        this.statusMenu = statusMenu;
        this.notes = notes;
        this.billId = billId;
        this.buyOrderId = buyOrderId;
        this.diningTableId = diningTableId;
    }
}

class TableBill
{
    public String totalCost;
    public String billId;
    public String billStatus;

    public TableBill(String totalCost, String billId, String billStatus)
    {
        this.totalCost = totalCost;
        this.billId = billId;
        this.billStatus = billStatus;
    }
}

class TableBuyOrderHasDrink
{
    public String tableBuyOrderHasDrinkId;
    public String quantity;
    public String drinkId;
    public String buyOrderId;

    public TableBuyOrderHasDrink(String tableBuyOrderHasDrinkId, String quantity, String drinkId, String buyOrderId)
    {
        this.tableBuyOrderHasDrinkId = tableBuyOrderHasDrinkId;
        this.quantity = quantity;
        this.drinkId = drinkId;
        this.buyOrderId = buyOrderId;
    }
}

class TableBuyOrderHasLunchAndMenu
{
    public String buyOrderHasLunchAndMenuId;
    public String quantity;
    public String foodId;
    public String buyOrderId;

    public TableBuyOrderHasLunchAndMenu(String buyOrderHasLunchAndMenuId, String quantity, String foodId, String buyOrderId)
    {
        this.buyOrderHasLunchAndMenuId = buyOrderHasLunchAndMenuId;
        this.quantity = quantity;
        this.foodId = foodId;
        this.buyOrderId = buyOrderId;
    }
}

class TableBuyOrderHasDessert
{
    public String buyOrderHasDessertId;
    public String quantity;
    public String dessertId;
    public String buyOrderId;

    public TableBuyOrderHasDessert(String buyOrderHasDessertId, String quantity, String dessertId, String buyOrderId)
    {
        this.buyOrderHasDessertId = buyOrderHasDessertId;
        this.quantity = quantity;
        this.dessertId = dessertId;
        this.buyOrderId = buyOrderId;
    }
}

class TableBuyOrderHasAppetizer
{
    public String buyOrderHasAppetizerId;
    public String quantity;
    public String appetizerId;
    public String buyOrderId;

    public TableBuyOrderHasAppetizer(String buyOrderHasAppetizerId, String quantity, String appetizerId, String buyOrderId)
    {
        this.buyOrderHasAppetizerId = buyOrderHasAppetizerId;
        this.quantity = quantity;
        this.appetizerId = appetizerId;
        this.buyOrderId = buyOrderId;
    }
}

class TableLunch
{
    public String lunchId;
    public String price;
    public String cookingTime;

    TableLunch(String lunchId, String price, String cookingTime)
    {
        this.lunchId = lunchId;
        this.price = price;
        this.cookingTime = cookingTime;
    }
}

class TableDessert
{
    public String dessertId;
    public String price;
    public String cookingTime;

    TableDessert(String dessertId, String price, String cookingTime)
    {
        this.dessertId = dessertId;
        this.price = price;
        this.cookingTime = cookingTime;
    }
}

class TableAppetizer
{
    public String appetizerId;
    public String price;
    public String cookingTime;

    TableAppetizer(String appetizerId, String price, String cookingTime)
    {
        this.appetizerId = appetizerId;
        this.price = price;
        this.cookingTime = cookingTime;
    }
}

class TableIngredient
{
    public String ingredientId;
    public String inventoryNameId;

    TableIngredient(String ingredientId, String inventoryNameId)
    {
        this.ingredientId=ingredientId;
        this.inventoryNameId = inventoryNameId;
    }
}

class TableReservation
{
    public String reservationId;
    public String diningTableId;
    public String nrOfGuests;
    public String email;
    public String date;
    public String phoneNr;
    public String fName;
    public String lName;
    public String time;

    TableReservation(String reservationId, String nrOfGuests, String email, String date, String phoneNr,
                     String fName, String lName, String time)
    {
        this.reservationId = reservationId;
        //this.diningTableId = diningTableId;
        this.nrOfGuests = nrOfGuests;
        this.email = email;
        this.date = date;
        this.phoneNr = phoneNr;
        this.fName = fName;
        this.lName = lName;
        this.time = time;
    }
}

class TableMenuHasIngredient
{
    public String ingredientId;
    public String menuHasIngredientId;
    public String menuId;
    public String quantity;

    TableMenuHasIngredient(String ingredientId, String menuHasIngredientId, String menuId, String quantity)
    {
        this.ingredientId = ingredientId;
        this.menuHasIngredientId = menuHasIngredientId;
        this.menuId = menuId;
        this.quantity = quantity;
    }
}

class TableInventory
{
    String inventoryNameId;
    String quantity;

    TableInventory(String inventoryNameId, String quantity)
    {
        this.inventoryNameId = inventoryNameId;
        this.quantity = quantity;
    }
}

class TableTimeTable
{
    public String timeTableId;
    public String employeeId;
    public String date;
    public String working;

    TableTimeTable(String timeTableId, String employeeId, String date, String working)
    {
        this.timeTableId = timeTableId;
        this.employeeId = employeeId;
        this.date = date;
        this.working = working;
    }
}

class TableEmployee
{
    public String employeeId;
    public String fName;
    public String lName;
    public String phoneNr;

    TableEmployee(String employeeId, String fName, String lName, String phoneNr)
    {
        this.employeeId = employeeId;
        this.fName = fName;
        this.lName = lName;
        this.phoneNr = phoneNr;
    }
}