package com.example.staffapp.api.database.table.interfaces;

import com.example.staffapp.api.database.table.classes.BuyOrderTableClass;
import com.example.staffapp.api.database.table.classes.TimeTableClass;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TimeTableInterface {
    final String TIME_TABLE_URL = "WebApplication1/webresources/package_db.timetable/";

    @Headers("Accept: application/json") //Vi ber om att få tabellen i JSON format
    @GET(TIME_TABLE_URL)
    Call<List<TimeTableClass>> getTimeTablePosts();

    //POST AVNÄNDS FÖR ATT SKAPA NY RECORD I TABELL, I DETTA FALL BUYORDER TABELLEN
    @POST(TIME_TABLE_URL)
    Call<TimeTableClass> createTimeTablePost(@Body TimeTableClass timeTableClass);

    //PATCH ANVÄNDS FÖR ATT ÄNDRA VÄRDET I EN KOLUMN AV EN RECORD I EN TABELL, I DETTA FALL BUYORDER TABELLEN
    @Headers("Accept: application/json") //Vi ber om att få tabellen i JSON format
    @PATCH(TIME_TABLE_URL+"{id}")
    Call<TimeTableClass> modifyTimeTableColumns(@Path("id") int id, @Body TimeTableClass timeTableClass);
}
