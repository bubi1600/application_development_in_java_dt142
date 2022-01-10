package com.example.staffapp.api.database.table.interfaces;

import com.example.staffapp.api.database.table.classes.BuyOrderHasAppetizerTableClass;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BuyOrderHasAppetizerTableInterface {
    final String BUY_ORDER_HAS_APPETIZER_URL = "WebApplication1/webresources/package_db.buyorderhasappetizer/";
    @POST(BUY_ORDER_HAS_APPETIZER_URL)
    Call<BuyOrderHasAppetizerTableClass> createBuyOrderHasAppetizerPost(@Body BuyOrderHasAppetizerTableClass buyOrderHasAppetizerTableClass);
}
