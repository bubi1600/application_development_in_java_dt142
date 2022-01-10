package com.example.kitchenapp;

import com.example.kitchenapp.api.database.table.classes.BuyOrderTableClass;
import com.example.kitchenapp.api.database.table.interfaces.BuyOrderTableInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostApi {
    private final String BASE_URL = "http://192.168.1.35:8080/";
    private ConnectToDatabase connect;

    public PostApi() { }

    public void modifyBuyOrderPostColumn(Integer buyOrderPostId, Integer diningTableId, Integer billId, String notes, String statusMenu, String statusDessert, String statusAppetizer)
    {
        connect = new ConnectToDatabase(BASE_URL);
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

}
