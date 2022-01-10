package com.example.weatherapp;
//Källa för API som vi hämtar JSON filen innehållande all nödvändig data:
//https://api.met.no/weatherapi/locationforecast/2.0/documentation#!/data/get_complete

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//AsyncTask gör så att denna klass körs på egen tråd och inte mainactivity tråden, så vi kan multi-taska
public class FetchWeatherData extends AsyncTask<Void, Void, Void> {
    private String jsonData;
    private boolean bIsDone;

    /**
     * Constructor
     */
    public FetchWeatherData()
    {
        jsonData = "";
    }

    //Getters
    public String getJsonData() {
        return jsonData;
    }

    public boolean isbIsDone() { return bIsDone; }

    /**
     * Fetches the weatherdata from the API and stores it in the instance variable jsonData.
     * @param voids
     * @return
     */
    @Override
    protected Void doInBackground(Void... voids)
    {
        //Vi vill att denna tråd ska köras hela tiden (i bakgrunden), så senaste data från API:ett hämtas alltid så att appen är uppdaterad
        //därför sätter vi allt i en while-sats.

        URL url = null;
        try {
            url = new URL("https://api.met.no/weatherapi/locationforecast/2.0/complete?lat=62&lon=17");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        bIsDone = false;

        while(true)
        {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //Anslut till API server
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2"); //Anger vår user-agent, annars får vi error 403
                //System.out.println("ERROR: " + httpURLConnection.getResponseCode()); //Debug, skriver ut error vi får från servern vi försöker ansluta till
                InputStream inputSteam = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputSteam));
                String line = "";
                jsonData = "";
                while(line != null)
                {
                    line = bufferedReader.readLine();//Hämta rad för rad av JSON filen från url:en och lagra i variabeln jsonData.
                    jsonData += line;
                }
                bufferedReader.close();
                if(!bIsDone)
                    bIsDone = true; //När denna är true så vet vi att data som behövs har hämtats och är redo att parsas

                //Vi väntar 1 sekund innan vi kör denna tråden igen.
                Thread.sleep(1000);
            } catch (MalformedURLException | InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPostExecute(Void unused) {
       super.onPostExecute(unused);
       MainActivity.textView.setText(MainActivity.weather); ////Skriver ut hela JSON filen till vår TextView objekt (som finns i "MainActivity.java)
    }


}