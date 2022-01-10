package com.example.kitchenapp.api.database.table.interfaces;

import com.example.kitchenapp.api.database.table.classes.BuyOrderHasDrinkTableClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BuyOrderHasDrinkTableInterface {
    final String BUY_ORDER_HAS_DRINK_URL = "WebApplication1/webresources/package_db.buyorderhasdrink/";
    @POST(BUY_ORDER_HAS_DRINK_URL)
    Call<BuyOrderHasDrinkTableClass> createBuyOrderHasDrinkPost(@Body BuyOrderHasDrinkTableClass buyOrderHasDrinkTableClass);
}
