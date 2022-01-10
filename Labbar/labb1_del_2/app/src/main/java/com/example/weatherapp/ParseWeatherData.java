package com.example.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseWeatherData {
    private String time;
    private String temperature;
    private String windSpeed;
    private String windDirectionDeg;
    private String cloudiness;
    private String precipitation_Amount_Max;
    private String precipitation_Amount_Min;
    private String weatherSymbolCode;
    private String windDirection;

    /**
     * Constructor
     */
    ParseWeatherData()
    {
        time = "";
        temperature = "";
        windSpeed = "";
        windDirectionDeg = "";
        cloudiness = "";
        precipitation_Amount_Max = "";
        precipitation_Amount_Min = "";
        weatherSymbolCode = "";
        windDirection = "";
    }

    //GETTERS
    /**
     * Get time of weather info update
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * Get the current temperature in Celsius
     * @return
     */
    public String getCurrentTemperature() {
        return temperature;
    }

    /**
     * Get the current windspeed in m/s
     * @return
     */
    public String getCurrentWindSpeed() {
        return windSpeed;
    }

    /**
     * get the winddirection in degrees (north=0 deg, east=90 deg, south=180 deg, west=270 deg)
     * @return
     */
    public String getCurrentWindDirectionDeg() {
        return windDirectionDeg;
    }

    /**
     * get current cloudiness in procent
     * @return
     */
    public String getCurrentCloudiness() {
        return cloudiness;
    }

    /**
     * get current max precipitation in mm
     * @return
     */
    public String getCurrentPrecipitation_Amount_Max() {
        return precipitation_Amount_Max;
    }

    /**
     * get current min precipitation in mm
     * @return
     */
    public String getCurrentPrecipitation_Amount_Min() {
        return precipitation_Amount_Min;
    }

    /**
     * get current weather symbol (ie cloudy, rainy, clear sky)
     * @return
     */
    public String getCurrentWeatherSymbolCode() {
        return weatherSymbolCode;
    }
    //GETTERS END

    /**
     * get wind directions (NE, SE, SW, NW, N, E, S, W)
     * @return
     */
    public String getCurrentWindDirection() {
        return windDirection;
    }

    /**
     * This method parses the fetched weather data from the API and stores them in the instance variables.
     * @param jsonData
     * @throws JSONException
     */
    void parse(String jsonData) throws JSONException {
        //Hämta rätt data i vår Json text som vi fick från URL:en
        JSONObject jObj = new JSONObject(jsonData);//Skapar nytt JSON objekt av vår variabel data, som innehåller JSON vi fick från URL:en (ovan)
        JSONObject properties = jObj.getJSONObject("properties"); //I Json objektet öppnar vi json objektet "properties"
        JSONArray timeseries = properties.getJSONArray("timeseries"); //I Json objektet hämtar vi arrayen med namnet "timeseries"
        JSONObject arr = timeseries.getJSONObject(1); //Vi hämtar index 1, som är data för nuvarande timme, från arrayer "timeseries"

        //Hämta time
        time = arr.getString("time");

        JSONObject dat = arr.getJSONObject("data"); //Vi hämtar json objektet med namnet "data"
        JSONObject next_1_hours = dat.getJSONObject("next_1_hours");

        //Hämta symbol code (alltså om det är  moligt eller regnit eller snöit osv)
        JSONObject summary = next_1_hours.getJSONObject("summary");
        weatherSymbolCode = summary.getString("symbol_code");

        //Hämta max och min precipitation.
        JSONObject detailsNext_1_hours = next_1_hours.getJSONObject("details");
        precipitation_Amount_Max = detailsNext_1_hours.getString("precipitation_amount_max");
        precipitation_Amount_Min = detailsNext_1_hours.getString("precipitation_amount_min");

        //Hämta temperature, windspeed, winddirection cloudiness
        JSONObject instant = dat.getJSONObject("instant"); //Vi hämtar json objektet med namnet "instant"
        JSONObject details = instant.getJSONObject("details"); //Vi hämtar json objektet med namnet "details"
        //I json objektet detail kan vi nu hämta det mesta vi behöver:
        temperature = details.getString("air_temperature");
        windSpeed = details.getString("wind_speed");
        windDirectionDeg = details.getString("wind_from_direction");
        cloudiness = details.getString("cloud_area_fraction");

        windDirection = calculateWindDirection();
    }

    /**
     * Calculate wind directions (NE, SE, SW, NW, N, E, S, W)
     * @return
     */
    public String calculateWindDirection()
    {
        float deg = Float.parseFloat(windDirectionDeg); //Konvertera string till float
        if(deg>0 && deg<90)
            return "NE";
        else if(deg>90 && deg<180)
            return "SE";
        else if(deg>180 && deg<270)
            return "SW";
        else if(deg>270 && deg<360)
            return "NW";
        else if(deg == 0)
            return "N";
        else if(deg == 90)
            return "E";
        else if(deg == 180)
            return "S";
        else if(deg == 360)
            return "W";
        return "error";
    }
}
