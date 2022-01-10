package com.example.staffapp;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Reservation extends AppCompatActivity {
    private TextView textViewReservation;
    private Api api;
    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        api = MainActivity.api;
        textViewReservation = (TextView) findViewById(R.id.textViewReservation);
        textViewReservation.setTextSize(25);

        List<TableReservation> reservationListTemp = api.PARSEDApi().getReservationList();

        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        String formattedDate = date.format(now);
        //String formattedTime = time.format(now);
        String formattedData = "Reservation Today " + formattedDate + "\n" +
                                "-----------------------------------------------------------------------------" + "\n";

        int i = 1;
        for(TableReservation reservation : reservationListTemp)
        {
            if( reservation.date.equals(formattedDate))
            {
                formattedData +=    " RESERVATION " + i + ":\n" +
                                    " Time: " + reservation.time +"\n" +
                                    //" Date: " + reservation.date + "\n" +
                                    " Name: " + reservation.fName + " " + reservation.lName + "\n" +
                                    " Email: " + reservation.email + "\n" +
                                    " Phone Number: " + reservation.phoneNr + "\n" +
                                    " Number Of Guests: " + reservation.nrOfGuests + "\n" +
                                    "-----------------------------------------------------------------------------" + "\n";
                i++;
            }
        }
        if(!formattedData.equals(""))
            textViewReservation.setText(formattedData);
        else
            textViewReservation.setText("No Reservations Available");
    }

}
