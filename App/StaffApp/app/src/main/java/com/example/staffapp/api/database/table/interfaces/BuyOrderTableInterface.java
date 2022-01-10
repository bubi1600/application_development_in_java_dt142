package com.example.staffapp.api.database.table.interfaces;

import com.example.staffapp.api.database.table.classes.BuyOrderTableClass;
import com.example.staffapp.api.database.table.classes.MenuTableClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BuyOrderTableInterface {
    final String BUY_ORDER_URL = "WebApplication1/webresources/package_db.buyorder/";

    //GET ANVÄNDS FÖR ATT HÄMTA, DENNA ÄR INAKTUELL DOCK, ANVÄNDS INTE LÄNGRE
    @Headers("Accept: application/json") //Vi ber om att få tabellen i JSON format
    @GET(BUY_ORDER_URL)
    Call<List<BuyOrderTableClass>> getBuyOrderPosts();

    //POST AVNÄNDS FÖR ATT SKAPA NY RECORD I TABELL, I DETTA FALL BUYORDER TABELLEN
    @POST(BUY_ORDER_URL)
    Call<BuyOrderTableClass> createBuyOrderPost(@Body BuyOrderTableClass buyOrderTableClass);

    //PUT ANVÄNDS FÖR ATT ÄNDRA VÄRDENA I EN HEL RECORD I EN TABELL, I DETTA FALL BUYORDER TABELLEN
    @PUT(BUY_ORDER_URL+"{id}")
    Call<BuyOrderTableClass> modifyBuyOrderPosts(@Path("id") int id, @Body BuyOrderTableClass buyOrderTableClass);

    //PATCH ANVÄNDS FÖR ATT ÄNDRA VÄRDET I EN KOLUMN AV EN RECORD I EN TABELL, I DETTA FALL BUYORDER TABELLEN
    @Headers("Accept: application/json") //Vi ber om att få tabellen i JSON format
    @PATCH(BUY_ORDER_URL+"{id}")
    Call<BuyOrderTableClass> modifyBuyOrderColumns(@Path("id") int id, @Body BuyOrderTableClass buyOrderTableClass);
}
