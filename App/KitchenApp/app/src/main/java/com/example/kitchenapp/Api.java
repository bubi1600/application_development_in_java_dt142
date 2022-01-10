package com.example.kitchenapp;

import android.os.AsyncTask;
import android.widget.Button;

import java.net.MalformedURLException;
import java.net.URL;

//Denna klass körs på egen tråd, ansvarig för att hämta senaste data från API:ett varje sekund
public class Api{
    private GetApi getApi;
    private ParseApi parseApi;
    private PostApi postApi;

    public Api()
    {
        getApi = new GetApi();
        postApi = new PostApi();
        parseApi = new ParseApi();
        getApi.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    //Vi initierar de trådarna parseAPI och postApi när GETApi tråden har kört klart och hämtat info som de andra trådarna är beroende av, så vi inte får nullptr.
    public void init()
    {
        parseApi.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public GetApi GETApi() { return getApi; }

    public ParseApi PARSEDApi() { return parseApi; }

    public PostApi POSTApi() { return postApi; }

}