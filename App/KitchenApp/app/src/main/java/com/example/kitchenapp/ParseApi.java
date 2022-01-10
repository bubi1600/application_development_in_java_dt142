package com.example.kitchenapp;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


/**
 * KVAR ATT PARSA:
 * TimeTable_Table
 * Employee_Table
 * Reservation_Table
 * */

public class ParseApi extends AsyncTask<Void, Void, Void> {
    private Api api;
    private List<TableMenu> menuList;
    private List<TableBuyOrder> buyOrderList;
    private List<TableDessert> dessertList;
    private List<TableAppetizer> appetizerList;
    private List<TableBuyOrderHasLunchAndMenu> buyOrderHasLunchAndMenuList;
    private List<TableBuyOrderHasDessert> buyOrderHasDessertList;
    private List<TableBuyOrderHasAppetizer> buyOrderHasAppetizerList;

    /**
     * Constructor
     */
    public ParseApi() { api = MainActivity.api; }

    @Override
    protected Void doInBackground(Void... voids)
    {
        api = MainActivity.api;
        //Då appen måste hela tiden vara uppdaterad med senaste data från databasen så sätter vi allt detta i en while-loop
        //som varje sekund hämtar API:ett på nytt.
        while(true)
        {
            parseMenuJson(api.GETApi().getJsonDataMenuTable());
            parseDessertJson(api.GETApi().getJsonDataDessertTable());
            parseAppetizerJson(api.GETApi().getJsonDataAppetizerTable());
            parseBuyOrderJson(api.GETApi().getJsonBuyOrderTable());
            parseBuyOrderHasMenuJson(api.GETApi().getJsonDataBuyOrderHasLunchAndMenuTable());
            parseBuyOrderHasDessert(api.GETApi().getJsonDataBuyOrderHasDessertTable());
            parseBuyOrderHasAppetizer(api.GETApi().getJsonDataBuyOrderHasAppetizerTable());

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

    private void parseBuyOrderHasMenuJson(String jsonData)
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

    private Boolean isJsonEmpty(String jsonData)
    {
        if(jsonData.equals(""))
            return true;
        return false;
    }

    /**GETTERS*/
    public List<TableMenu> getMenuList() { return menuList; }

    public List<TableBuyOrder> getBuyOrderList() { return buyOrderList; }

    public List<TableBuyOrderHasLunchAndMenu> getBuyOrderHasLunchAndMenuList() { return buyOrderHasLunchAndMenuList; }

    public List<TableDessert> getDessertList() { return dessertList; }

    public List<TableAppetizer> getAppetizerList() { return appetizerList; }

    public List<TableBuyOrderHasDessert> getBuyOrderHasDessertList() { return buyOrderHasDessertList; }

    public List<TableBuyOrderHasAppetizer> getBuyOrderHasAppetizerList() { return buyOrderHasAppetizerList; }
}

/**STRUCTS**/
class TableMenu{
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

class TableBuyOrderHasLunchAndMenu{
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