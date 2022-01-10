package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    static TextView textView;
    ImageView imageView;
    FetchWeatherData weatherData;
    ParseWeatherData parsedData;
    static String weather;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button); //Gör så att vår Button objekt button "pekare" på button knappen i vår app genom dess id
        textView = (TextView) findViewById(R.id.textView4); //Gör så att vår TextView objekt textView "pekare" på textview i vår app genom dess id
        imageView = (ImageView) findViewById(R.id.imageView); //Gör så att vår ImageView objekt imageView "pekare" på imageview i vår app genom dess id

        weatherData = new FetchWeatherData();
        parsedData = new ParseWeatherData();

        //Aktivera tråd
        weatherData.execute();
        while(!weatherData.isbIsDone())
            System.out.println("Updating...");
        displayWeatherData();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                displayWeatherData();
            }
        });
    }

    /**
     * Prints out the weather on the android display
     */
    void displayWeatherData()
    {
        try {
            parsedData.parse(weatherData.getJsonData());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        weather = "Last updated: " + parsedData.getTime() + "\n" +
                "Temperature: " + parsedData.getCurrentTemperature() + " celsius\n" +
                "WindSpeed: " + parsedData.getCurrentWindSpeed() + " m/s from direction " + parsedData.getCurrentWindDirection() + "\n" +
                "Cloudiness: " + parsedData.getCurrentCloudiness() + "%\n" +
                "Precipitation: Between " + parsedData.getCurrentPrecipitation_Amount_Min() + " and " + parsedData.getCurrentPrecipitation_Amount_Max() + " mm";
        textView.setText(weather);

        String weatherSymbol = parsedData.getCurrentWeatherSymbolCode();
        int weatherImage = getResources().getIdentifier("drawable/" + weatherSymbol,null,getPackageName()); //Vi hämtar rätt fil utifrån aktuell vädersymbolen vi fått från JSON filen
        imageView.setImageResource(weatherImage); //Vi sätter vår imageView till motsvarande bild för vädret
    }
}

