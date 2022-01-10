package com.example.kitchenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    public static Api api;
    public TableLayout tableLayout;
    public static final String baseURL = "http://192.168.1.36:8080/";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = new Api();
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        UpdateActivity updateActivity = new UpdateActivity(tableLayout);
        updateActivity.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}