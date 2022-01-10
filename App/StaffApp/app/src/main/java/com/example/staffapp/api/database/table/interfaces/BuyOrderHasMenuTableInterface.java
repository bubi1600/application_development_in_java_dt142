package com.example.staffapp.api.database.table.interfaces;

import com.example.staffapp.api.database.table.classes.BuyOrderHasMenuTableClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BuyOrderHasMenuTableInterface {
    final String BUY_ORDER_HAS_MENU_URL = "WebApplication1/webresources/package_db.buyorderhasmenu/";
    @POST(BUY_ORDER_HAS_MENU_URL)
    Call<BuyOrderHasMenuTableClass> createBuyOrderHasLunchAndMenuPost(@Body BuyOrderHasMenuTableClass buyOrderHasMenuTableClass);
}
