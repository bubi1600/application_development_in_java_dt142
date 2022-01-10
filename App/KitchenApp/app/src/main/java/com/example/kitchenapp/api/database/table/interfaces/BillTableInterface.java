package com.example.kitchenapp.api.database.table.interfaces;

import com.example.kitchenapp.api.database.table.classes.BillTableClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BillTableInterface {
    final String BILL_TABLE_URL = "WebApplication1/webresources/package_db.bill/";
    @Headers("Accept: application/json") //Vi ber om att få tabellen i JSON format
    @GET(BILL_TABLE_URL)
    Call<List<BillTableClass>> getBillPosts();

    @POST(BILL_TABLE_URL)
    Call<BillTableClass> createBillPost(@Body BillTableClass billTableClass);
}
