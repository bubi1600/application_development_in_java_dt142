package com.example.staffapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectToDatabase {
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor httpLoggingInterceptor;
    private Retrofit retrofit;

    /**
     * Denna konstruktor initierar instans variabler och ansluter till angiven databas
     * och förbereder retrofit till den databasen, som är instansvariabeln "retrofit".
     * @param BASE_URL Denna parameter är adressen till databasen vi vill ansluta till.
     *
     * Retrofit är det som behövs för att skriva till en tabell i en databas eller läsa
     * en tabell i en databas.
     */
    public ConnectToDatabase(String BASE_URL)
    {
        httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**GETTER**/
    public Retrofit getRetrofit(){ return retrofit; }
    public OkHttpClient getOkHttpClient(){ return okHttpClient; }
}
