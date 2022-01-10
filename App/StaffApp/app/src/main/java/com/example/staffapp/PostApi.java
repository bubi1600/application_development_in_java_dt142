package com.example.staffapp;

import android.os.AsyncTask;
import com.example.staffapp.api.database.table.classes.*;
import com.example.staffapp.api.database.table.interfaces.*;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostApi extends AsyncTask<Void, Void, Void> {
    private final String BASE_URL = "http://192.168.1.35:8080";
    private ConnectToDatabase connect;
    private Boolean sendOrders;
    private Api api;
    private HashMap<String, Integer> savedMenuAmount;
    private HashMap<String, Integer> savedDrinksAmount;
    private HashMap<String, Integer> savedAppetizerAmount;
    private HashMap<String, Integer> savedDessertAmount;
    private List<InventoryModifier> inventoryModifierList;
    private int buyOrderPostId;

    public PostApi()
    {
        sendOrders = false;
        api = MainActivity.api;
        connect = new ConnectToDatabase(BASE_URL);
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        while(true)
        {
            if(sendOrders)
            {
                if(!savedMenuAmount.isEmpty() || savedMenuAmount != null)
                {
                    connect = new ConnectToDatabase(BASE_URL);
                    for (String key : savedMenuAmount.keySet())
                    {
                        createBuyOrderHasMenuPost(buyOrderPostId, key, savedMenuAmount.get(key));
                    }

                    connect = new ConnectToDatabase(BASE_URL);
                    for(InventoryModifier key : inventoryModifierList)
                    {
                        for(int i=0; i<key.ingredientIdList.size(); i++)
                        {
                            String inventoryNameId = key.inventoryNameIdList.get(i);
                            Integer currentIngredientQuantityInTable = Integer.parseInt(key.currentQuantityOfIngredientInInventoryList.get(i));
                            Integer quantityOfIngredientToSubtract = ( Integer.parseInt(key.quantityOfSameMenu)*Integer.parseInt(key.quantityOfIngredient.get(i)) );
                            Integer newSum = currentIngredientQuantityInTable-quantityOfIngredientToSubtract;
                            modifyInventoryPostColumn(inventoryNameId, newSum);
                        }
                    }
                }
                connect = new ConnectToDatabase(BASE_URL);
                if(!savedDrinksAmount.isEmpty() || savedDrinksAmount != null)
                {
                    for (String key : savedDrinksAmount.keySet())
                    {
                        createBuyOrderHasDrinkPost(buyOrderPostId, key, savedDrinksAmount.get(key));
                    }
                }
                connect = new ConnectToDatabase(BASE_URL);
                if(!savedAppetizerAmount.isEmpty() || savedAppetizerAmount != null)
                {
                    for (String key : savedAppetizerAmount.keySet())
                    {
                        createBuyOrderHasAppetizerPost(buyOrderPostId, key, savedAppetizerAmount.get(key));
                    }
                }
                connect = new ConnectToDatabase(BASE_URL);
                if(!savedDessertAmount.isEmpty() || savedDessertAmount != null)
                {
                    for (String key : savedDessertAmount.keySet())
                    {
                        createBuyOrderHasDessertPost(buyOrderPostId, key, savedDessertAmount.get(key));
                    }
                }
                sendOrders =false;
            }
            //Vi pausar tråden 0.5 sekund.
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSendItems(HashMap savedMenuAmount, HashMap savedDrinksAmount, HashMap savedAppetizerAmount, HashMap savedDessertAmount, List<InventoryModifier> inventoryModifierList, int buyOrderPostId)
    {
        this.savedMenuAmount = savedMenuAmount;
        this.savedDrinksAmount = savedDrinksAmount;
        this.savedAppetizerAmount = savedAppetizerAmount;
        this.savedDessertAmount = savedDessertAmount;
        this.inventoryModifierList = inventoryModifierList;
        this.buyOrderPostId = buyOrderPostId;
        sendOrders = true;
    }

    public void createMenuPost(String menuId, int price, int timeToCook)
    {
        MenuTableInterface menuTableInterface = connect.getRetrofit().create(MenuTableInterface.class);

        MenuTableClass menuTableClass = new MenuTableClass(menuId, price, timeToCook);

        Call<MenuTableClass> call = menuTableInterface.createMenuPost(menuTableClass);

        call.enqueue(new Callback<MenuTableClass>() {
            @Override
            public void onResponse(Call<MenuTableClass> call, Response<MenuTableClass> response) {

                if(response.isSuccessful())
                {
                    System.out.println("Post Succeeded Code: " + response.code());
                    //Toast.makeText(,"Post Successful: " + response.code(), Toast.LENGTH_LONG).show();
                }
                else
                    System.out.println("Post Failed Code: " + response.code());
                    //Toast.makeText(MainActivity.this,"Post Failed: " + response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MenuTableClass> call, Throwable t) {
                System.out.println("Post Failed Code: " + t.getMessage());
                //Toast.makeText(MainActivity.this,"Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**POST Methods*/
    public void createBuyOrderPost(Integer diningTableId, Integer billId, String notes, String statusMenu, String statusDessert, String statusAppetizer)
    {
        BuyOrderTableInterface buyOrderTableInterface = connect.getRetrofit().create(BuyOrderTableInterface.class);

        BuyOrderTableClass buyOrderTableClass = new BuyOrderTableClass(0,diningTableId,billId, notes, statusMenu, statusDessert, statusAppetizer);

        Call<BuyOrderTableClass> call = buyOrderTableInterface.createBuyOrderPost(buyOrderTableClass);

        call.enqueue(new Callback<BuyOrderTableClass>() {
            @Override
            public void onResponse(Call<BuyOrderTableClass> call, Response<BuyOrderTableClass> response) {

                if(response.isSuccessful())
                {
                    System.out.println("Post Succeeded Code: " + response.code());
                    //Toast.makeText(,"Post Successful: " + response.code(), Toast.LENGTH_LONG).show();
                }
                else
                    System.out.println("Post Failed Code: " + response.code());
                //Toast.makeText(MainActivity.this,"Post Failed: " + response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<BuyOrderTableClass> call, Throwable t) {
                System.out.println("Post Failed Code: " + t.getMessage());
                //Toast.makeText(MainActivity.this,"Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createBillPost(String PayStatus, double TotalCost)
    {
        BillTableInterface billTableInterface = connect.getRetrofit().create(BillTableInterface.class);

        BillTableClass buyOrderTableClass = new BillTableClass(0, PayStatus, TotalCost);

        Call<BillTableClass> call = billTableInterface.createBillPost(buyOrderTableClass);

        call.enqueue(new Callback<BillTableClass>() {
            @Override
            public void onResponse(Call<BillTableClass> call, Response<BillTableClass> response) {

                if(response.isSuccessful())
                {
                    System.out.println("Post Succeeded Code: " + response.code());
                    //Toast.makeText(,"Post Successful: " + response.code(), Toast.LENGTH_LONG).show();
                }
                else
                System.out.println("Post Failed Code: " + response.code());
                    //Toast.makeText(MainActivity.this,"Post Failed: " + response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<BillTableClass> call, Throwable t) {
                System.out.println("Post Failed Code: " + t.getMessage());
                //Toast.makeText(MainActivity.this,"Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createBuyOrderHasMenuPost(int buyOrderId, String menuId, int quantity)
    {
        BuyOrderHasMenuTableInterface buyOrderHasLunchAndMenuTableInterface = connect.getRetrofit().create(BuyOrderHasMenuTableInterface.class);

        BuyOrderHasMenuTableClass buyOrderHasMenuTableClass = new BuyOrderHasMenuTableClass(0, buyOrderId, menuId, quantity);

        Call<BuyOrderHasMenuTableClass> call = buyOrderHasLunchAndMenuTableInterface.createBuyOrderHasLunchAndMenuPost(buyOrderHasMenuTableClass);

        call.enqueue(new Callback<BuyOrderHasMenuTableClass>() {
            @Override
            public void onResponse(Call<BuyOrderHasMenuTableClass> call, Response<BuyOrderHasMenuTableClass> response) {

                if(response.isSuccessful())
                {
                    System.out.println("Post Succeeded Code: " + response.code());
                    //Toast.makeText(,"Post Successful: " + response.code(), Toast.LENGTH_LONG).show();
                }
                else
                    System.out.println("Post Failed Code: " + response.code());
                //Toast.makeText(MainActivity.this,"Post Failed: " + response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<BuyOrderHasMenuTableClass> call, Throwable t) {
                System.out.println("Post Failed Code: " + t.getMessage());
                //Toast.makeText(MainActivity.this,"Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createBuyOrderHasDrinkPost(int buyOrderId, String drinkid, int quantity)
    {
        BuyOrderHasDrinkTableInterface buyOrderHasDrinkTableInterface = connect.getRetrofit().create(BuyOrderHasDrinkTableInterface.class);

        BuyOrderHasDrinkTableClass buyOrderHasDrinkTableClass = new BuyOrderHasDrinkTableClass(0, buyOrderId, drinkid, quantity);

        Call<BuyOrderHasDrinkTableClass> call = buyOrderHasDrinkTableInterface.createBuyOrderHasDrinkPost(buyOrderHasDrinkTableClass);

        call.enqueue(new Callback<BuyOrderHasDrinkTableClass>() {
            @Override
            public void onResponse(Call<BuyOrderHasDrinkTableClass> call, Response<BuyOrderHasDrinkTableClass> response) {
                if(response.isSuccessful())
                {
                    System.out.println("Post Succeeded Code: " + response.code());
                    //Toast.makeText(,"Post Successful: " + response.code(), Toast.LENGTH_LONG).show();
                }
                else
                    System.out.println("Post Failed Code: " + response.code());
                //Toast.makeText(MainActivity.this,"Post Failed: " + response.code(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<BuyOrderHasDrinkTableClass> call, Throwable t) {
                System.out.println("Post Failed Code: " + t.getMessage());
                //Toast.makeText(MainActivity.this,"Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createBuyOrderHasDessertPost(int buyOrderId, String dessertid, int quantity)
    {
        BuyOrderHasDessertTableInterface buyOrderHasDessertTableInterface = connect.getRetrofit().create(BuyOrderHasDessertTableInterface.class);

        BuyOrderHasDessertTableClass buyOrderHasDessertTableClass = new BuyOrderHasDessertTableClass(0, buyOrderId, dessertid, quantity);

        Call<BuyOrderHasDessertTableClass> call = buyOrderHasDessertTableInterface.createBuyOrderHasDessertPost(buyOrderHasDessertTableClass);

        call.enqueue(new Callback<BuyOrderHasDessertTableClass>() {
            @Override
            public void onResponse(Call<BuyOrderHasDessertTableClass> call, Response<BuyOrderHasDessertTableClass> response) {
                if(response.isSuccessful())
                {
                    System.out.println("Post Succeeded Code: " + response.code());
                    //Toast.makeText(,"Post Successful: " + response.code(), Toast.LENGTH_LONG).show();
                }
                else
                    System.out.println("Post Failed Code: " + response.code());
                //Toast.makeText(MainActivity.this,"Post Failed: " + response.code(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<BuyOrderHasDessertTableClass> call, Throwable t) {
                System.out.println("Post Failed Code: " + t.getMessage());
                //Toast.makeText(MainActivity.this,"Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createBuyOrderHasAppetizerPost(int buyOrderId, String appetizertid, int quantity)
    {
        BuyOrderHasAppetizerTableInterface buyOrderHasAppetizerTableInterface = connect.getRetrofit().create(BuyOrderHasAppetizerTableInterface.class);

        BuyOrderHasAppetizerTableClass buyOrderHasAppetizerTableClass = new BuyOrderHasAppetizerTableClass(appetizertid, 0, buyOrderId, quantity);

        Call<BuyOrderHasAppetizerTableClass> call = buyOrderHasAppetizerTableInterface.createBuyOrderHasAppetizerPost(buyOrderHasAppetizerTableClass);

        call.enqueue(new Callback<BuyOrderHasAppetizerTableClass>() {
            @Override
            public void onResponse(Call<BuyOrderHasAppetizerTableClass> call, Response<BuyOrderHasAppetizerTableClass> response) {
                if(response.isSuccessful())
                {
                    System.out.println("Post Succeeded Code: " + response.code());
                    //Toast.makeText(,"Post Successful: " + response.code(), Toast.LENGTH_LONG).show();
                }
                else
                    System.out.println("Post Failed Code: " + response.code());
                //Toast.makeText(MainActivity.this,"Post Failed: " + response.code(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<BuyOrderHasAppetizerTableClass> call, Throwable t) {
                System.out.println("Post Failed Code: " + t.getMessage());
                //Toast.makeText(MainActivity.this,"Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    /**PUT Methods (MODYFI ALL COLUMNS IN A RECORD IN A RECORD IN BUYORDER TABLE)*/
    public void modifyBuyOrderPost(Integer diningTableId, Integer billId, String notes, String statusMenu, String statusDessert, String statusAppetizer)
    {
        BuyOrderTableInterface buyOrderTableInterface = connect.getRetrofit().create(BuyOrderTableInterface.class);

        BuyOrderTableClass buyOrderTableClass = new BuyOrderTableClass(0,diningTableId,billId, notes, statusMenu, statusDessert, statusAppetizer); //Vi ska bara ändra foodstatus

        Call<BuyOrderTableClass> call = buyOrderTableInterface.modifyBuyOrderPosts(0, buyOrderTableClass); //Detta är vad vi skickar in, vi vill skicka in vår nya objek där vi ändrat foodstatus till "Serverd"
        call.enqueue(new Callback<BuyOrderTableClass>() {
            @Override
            public void onResponse(Call<BuyOrderTableClass> call, Response<BuyOrderTableClass> response) {
                if(response.isSuccessful())
                {
                    System.out.println("Modify Record Succeeded Code: " + response.code());
                }
                else
                    System.out.println("Modify Record Failed Code: " + response.code());
            }
            @Override
            public void onFailure(Call<BuyOrderTableClass> call, Throwable t) {
                System.out.println("Modify Record Failed Code: " + t.getMessage());
            }
        });
    }

    /**PATCH Methods (MODIFY SINGLE COLUMNS IN A RECORD IN BUYORDER TABLE)*/
    public void modifyBuyOrderPostColumn(Integer buyOrderPostId, Integer diningTableId, Integer billId, String notes, String statusMenu, String statusDessert, String statusAppetizer)
    {
        BuyOrderTableInterface buyOrderTableInterface = connect.getRetrofit().create(BuyOrderTableInterface.class);

        BuyOrderTableClass buyOrderTableClass = new BuyOrderTableClass(buyOrderPostId,diningTableId,billId, notes, statusMenu, statusDessert, statusAppetizer); //Vi ska bara ändra foodstatus

        Call<BuyOrderTableClass> call = buyOrderTableInterface.modifyBuyOrderColumns(buyOrderPostId, buyOrderTableClass); //Detta är vad vi skickar in, vi vill skicka in vår nya objek där vi ändrat foodstatus till "Serverd". VI anger vilken record vi vill ändra genom parametern "buyOrderPostId"
        call.enqueue(new Callback<BuyOrderTableClass>() {
            @Override
            public void onResponse(Call<BuyOrderTableClass> call, Response<BuyOrderTableClass> response) {
                if(response.isSuccessful())
                    System.out.println("Modify Column Succeeded Code: " + response.code());
                else
                    System.out.println("Modify Column Failed Code: " + response.code());
            }
            @Override
            public void onFailure(Call<BuyOrderTableClass> call, Throwable t) {
                System.out.println("Modify Column Failed Code: " + t.getMessage());
            }
        });
    }

    /**PATCH Methods (MODIFY SINGLE COLUMNS IN A RECORD IN Inventory TABLE)*/
    public void modifyInventoryPostColumn(String inventoryNameId, Integer quantity)
    {
        InventoryTableInterface inventoryTableInterface = connect.getRetrofit().create(InventoryTableInterface.class);

        InventoryTableClass inventoryTableClass = new InventoryTableClass(inventoryNameId, quantity); //Vi ska bara ändra quantity

        Call<InventoryTableClass> call = inventoryTableInterface.modifyInventoryColumns(inventoryNameId, inventoryTableClass); //Detta är vad vi skickar in, vi vill skicka in vår nya objek där vi ändrat foodstatus till "Serverd". VI anger vilken record vi vill ändra genom parametern "inventoryNameId"
        call.enqueue(new Callback<InventoryTableClass>() {
            @Override
            public void onResponse(Call<InventoryTableClass> call, Response<InventoryTableClass> response) {
                if(response.isSuccessful())
                {
                    System.out.println("Modify Column Succeeded Code: " + response.code());
                }
                else
                    System.out.println("Modify Column Failed Code: " + response.code());
            }
            @Override
            public void onFailure(Call<InventoryTableClass> call, Throwable t) {
                System.out.println("Modify Column Failed Code: " + t.getMessage());
            }
        });
    }

    public void createTimeTablePost(Integer timeTableId, Integer employeeId, String working, String date)
    {
        TimeTableInterface timeTableInterface = connect.getRetrofit().create(TimeTableInterface.class);

        TimeTableClass buyOrderHasAppetizerTableClass = new TimeTableClass(timeTableId, employeeId, working, date);

        Call<TimeTableClass> call = timeTableInterface.createTimeTablePost(buyOrderHasAppetizerTableClass);

        call.enqueue(new Callback<TimeTableClass>() {
            @Override
            public void onResponse(Call<TimeTableClass> call, Response<TimeTableClass> response) {
                if(response.isSuccessful())
                {
                    System.out.println("Post Succeeded Code: " + response.code());
                    //Toast.makeText(,"Post Successful: " + response.code(), Toast.LENGTH_LONG).show();
                }
                else
                    System.out.println("Post Failed Code: " + response.code());
                //Toast.makeText(MainActivity.this,"Post Failed: " + response.code(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<TimeTableClass> call, Throwable t) {
                System.out.println("Post Failed Code: " + t.getMessage());
                //Toast.makeText(MainActivity.this,"Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**PATCH Methods (MODIFY SINGLE COLUMNS IN A RECORD IN TIMETABLE TABLE)*/
    public void modifyTimeTablePostColumn(Integer timeTableId, Integer employeeId, String working, String date)
    {
        TimeTableInterface timeTableInterface = connect.getRetrofit().create(TimeTableInterface.class);

        TimeTableClass timeTableClass = new TimeTableClass(timeTableId, employeeId, working, date); //Vi ska bara ändra quantity

        Call<TimeTableClass> call = timeTableInterface.modifyTimeTableColumns(timeTableId, timeTableClass); //Detta är vad vi skickar in, vi vill skicka in vår nya objek där vi ändrat foodstatus till "Serverd". VI anger vilken record vi vill ändra genom parametern "inventoryNameId"
        call.enqueue(new Callback<TimeTableClass>() {
            @Override
            public void onResponse(Call<TimeTableClass> call, Response<TimeTableClass> response) {
                if(response.isSuccessful())
                {
                    System.out.println("Modify Column Succeeded Code: " + response.code());
                }
                else
                    System.out.println("Modify Column Failed Code: " + response.code());
            }
            @Override
            public void onFailure(Call<TimeTableClass> call, Throwable t) {
                System.out.println("Modify Column Failed Code: " + t.getMessage());
            }
        });
    }
}
