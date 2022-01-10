package com.example.staffapp.api.database.table.interfaces;

import com.example.staffapp.api.database.table.classes.InventoryTableClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface InventoryTableInterface {
    final String INVENTORY_URL = "WebApplication1/webresources/package_db.inventory/";

    //PATCH ANVÄNDS FÖR ATT ÄNDRA VÄRDET I EN KOLUMN AV EN RECORD I EN TABELL, I DETTA FALL INVENTORY TABELLEN
    @Headers("Accept: application/json") //Vi ber om att få tabellen i JSON format
    @PATCH(INVENTORY_URL+"{id}")
    Call<InventoryTableClass> modifyInventoryColumns(@Path("id") String id, @Body InventoryTableClass inventoryTableClass);
}
