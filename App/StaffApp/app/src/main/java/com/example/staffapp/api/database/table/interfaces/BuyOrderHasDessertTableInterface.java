package com.example.staffapp.api.database.table.interfaces;

import com.example.staffapp.api.database.table.classes.BuyOrderHasDessertTableClass;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BuyOrderHasDessertTableInterface {
    final String BUY_ORDER_HAS_APPETIZER_URL = "WebApplication1/webresources/package_db.buyorderhasdessert/";
    @POST(BUY_ORDER_HAS_APPETIZER_URL)
    Call<BuyOrderHasDessertTableClass> createBuyOrderHasDessertPost(@Body BuyOrderHasDessertTableClass buyOrderHasDessertTableClass);
}
