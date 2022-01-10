package com.example.staffapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Api api;
    public static UpdateKitchenButtonStatus updateKitchenButtonStatus;

    private int tableNr;

    private final String STATUS_DONE = "Done";
    private final String STATUS_SERVED = "Served";
    private final String STATUS_PREFIX = "Status: ";

    private Button btnTable1;
    private Button btnTable2;
    private Button btnTable3;
    private Button btnTable4;
    private Button btnTable5;
    private Button btnTable6;
    private Button btnTable7;
    private Button btnTable1Status;
    private Button btnTable2Status;
    private Button btnTable3Status;
    private Button btnTable4Status;
    private Button btnTable5Status;
    private Button btnTable6Status;
    private Button btnTable7Status;
    private Button btnTable1Specifics;
    private Button btnTable2Specifics;
    private Button btnTable3Specifics;
    private Button btnTable4Specifics;
    private Button btnTable5Specifics;
    private Button btnTable6Specifics;
    private Button btnTable7Specifics;
    private Button buttonSchedule;
    private Button buttonReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTable1 = (Button)findViewById(R.id.buttonTable1);
        btnTable2 = (Button)findViewById(R.id.buttonTable2);
        btnTable3 = (Button)findViewById(R.id.buttonTable3);
        btnTable4 = (Button)findViewById(R.id.buttonTable4);
        btnTable5 = (Button)findViewById(R.id.buttonTable5);
        btnTable6 = (Button)findViewById(R.id.buttonTable6);
        btnTable7 = (Button)findViewById(R.id.buttonTable7);
        btnTable1Status = (Button)findViewById(R.id.buttonStatusTable1);
        btnTable2Status = (Button)findViewById(R.id.buttonStatusTable2);
        btnTable3Status = (Button)findViewById(R.id.buttonStatusTable3);
        btnTable4Status = (Button)findViewById(R.id.buttonStatusTable4);
        btnTable5Status = (Button)findViewById(R.id.buttonStatusTable5);
        btnTable6Status = (Button)findViewById(R.id.buttonStatusTable6);
        btnTable7Status = (Button)findViewById(R.id.buttonStatusTable7);
        btnTable1Specifics = (Button)findViewById(R.id.buttonSpecificsTable1);
        btnTable2Specifics = (Button)findViewById(R.id.buttonSpecificsTable2);
        btnTable3Specifics = (Button)findViewById(R.id.buttonSpecificsTable3);
        btnTable4Specifics = (Button)findViewById(R.id.buttonSpecificsTable4);
        btnTable5Specifics = (Button)findViewById(R.id.buttonSpecificsTable5);
        btnTable6Specifics = (Button)findViewById(R.id.buttonSpecificsTable6);
        btnTable7Specifics = (Button)findViewById(R.id.buttonSpecificsTable7);
        buttonSchedule = (Button)findViewById(R.id.buttonSchedule);
        buttonReservation = (Button)findViewById(R.id.buttonReservation);

        /**AKTIVERA TRÅDARNA "api" och "updateKitchenButtonStatus" SÅ DE ÄR SYNKADE
         * När vi vill aktivera trådarna använder vi metoden executeOnExecutor() istället för
         * execute() och sätter in AsyncTask.THREAD_POOL_EXECUTOR som parameter så att api
         * tråden synkar med updateKitchenButtonStatus tråden**/
        api = new Api();
        updateKitchenButtonStatus = new UpdateKitchenButtonStatus();
        updateKitchenButtonStatus.init(btnTable1Status, btnTable2Status, btnTable3Status, btnTable4Status, btnTable5Status, btnTable6Status, btnTable7Status);
        updateKitchenButtonStatus.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        /**LISTENERS*/
        btnTable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 1;
                openOrderActivity();
            }
        });
        btnTable1Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 1;
                if(btnTable1Status.getText().equals(STATUS_PREFIX+STATUS_DONE))
                    changeStatus(tableNr);
            }
        });
        btnTable1Specifics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 1;
                openSpecificsActivity(tableNr);
            }
        });


        btnTable2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 2;
                openOrderActivity();
            }
        });
        btnTable2Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 2;
                if(btnTable2Status.getText().equals(STATUS_PREFIX+STATUS_DONE))
                    changeStatus(tableNr);
            }
        });
        btnTable2Specifics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 2;
                openSpecificsActivity(tableNr);
            }
        });


        btnTable3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 3;
                openOrderActivity();
            }
        });
        btnTable3Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 3;
                if(btnTable3Status.getText().equals(STATUS_PREFIX+STATUS_DONE))
                    changeStatus(tableNr);
            }
        });
        btnTable3Specifics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 3;
                openSpecificsActivity(tableNr);
            }
        });


        btnTable4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 4;
                openOrderActivity();
            }
        });
        btnTable4Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 4;
                if(btnTable4Status.getText().equals(STATUS_PREFIX+STATUS_DONE))
                    changeStatus(tableNr);
            }
        });
        btnTable4Specifics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 4;
                openSpecificsActivity(tableNr);
            }
        });


        btnTable5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 5;
                openOrderActivity();
            }
        });
        btnTable5Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 5;
                if(btnTable5Status.getText().equals(STATUS_PREFIX+STATUS_DONE))
                    changeStatus(tableNr);
            }
        });
        btnTable5Specifics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 5;
                openSpecificsActivity(tableNr);
            }
        });


        btnTable6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 6;
                openOrderActivity();
            }
        });
        btnTable6Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 6;
                if(btnTable6Status.getText().equals(STATUS_PREFIX+STATUS_DONE))
                    changeStatus(tableNr);
            }
        });
        btnTable6Specifics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 6;
                openSpecificsActivity(tableNr);
            }
        });


        btnTable7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 7;
                openOrderActivity();
            }
        });
        btnTable7Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 7;
                if(btnTable7Status.getText().equals(STATUS_PREFIX+STATUS_DONE))
                    changeStatus(tableNr);
            }
        });
        btnTable7Specifics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNr = 7;
                openSpecificsActivity(tableNr);
            }
        });


        buttonReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReservationActivity();
            }
        });


        buttonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScheduleActivity();
            }
        });
    }

    /**METHODS**/
    private void openOrderActivity()
    {
        Intent intent = new Intent(this, Order.class);
        intent.putExtra("tableNr", tableNr);
        startActivity(intent);
    }

    private void openSpecificsActivity(int tableNr)
    {
        Intent intent = new Intent(this, Specifics.class);
        intent.putExtra("tableNr", tableNr);
        startActivity(intent);
    }

    private void openReservationActivity()
    {
        Intent intent = new Intent(this, Reservation.class);
        startActivity(intent);
    }

    private void openScheduleActivity()
    {
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }

    /**
     * Denna metod ändrar status för måltider (kolumn) i en order till "Served" (alltså serverad) när
     * servitören klickar på knappen för ett bort som indikerar att maten serverats.
     * @param tableNr
     */
    private void changeStatus(int tableNr)
    {
        //Vi hämtar värdena i recorden så vi kan sätta in samma värden igen och bara ändra den kolumn vi vill ändra
        ArrayList<String> buyOrderRecord = api.GETApi().getBuyOrderRecordWithDiningTableNr(tableNr);
        int buyOrderId = Integer.parseInt(buyOrderRecord.get(0));
        int diningTableId = Integer.parseInt(buyOrderRecord.get(1));
        int billId = Integer.parseInt(buyOrderRecord.get(2));
        String notes = buyOrderRecord.get(3);
        String menuStatus = buyOrderRecord.get(4);
        String dessertStatus = buyOrderRecord.get(5);
        String appetizerStatus = buyOrderRecord.get(6);

        //Vi stoppar in gamla värdena där vi inte vill ändra, och ändrar bara den kolumn vi vill ändra, i detta fallet är det foodStatus

        if(menuStatus.equals(STATUS_DONE))
        {
            api.POSTApi().modifyBuyOrderPostColumn(buyOrderId, diningTableId, billId, notes, STATUS_SERVED, dessertStatus, appetizerStatus);
            menuStatus = STATUS_SERVED; //Vi ändrar menuStatus i databasen i tabellen, så vi måste ändra den här också, så de raderna under skickar uppdaterade ändringarna.
        }

        if(dessertStatus.equals(STATUS_DONE))
        {
            api.POSTApi().modifyBuyOrderPostColumn(buyOrderId, diningTableId, billId, notes, menuStatus, STATUS_SERVED, appetizerStatus);
            dessertStatus = STATUS_SERVED; //Vi ändrar dessertStatus i databasen i tabellen, så vi måste ändra den här också, så de raderna under skickar uppdaterade ändringarna.
        }

        if(appetizerStatus.equals(STATUS_DONE))
            api.POSTApi().modifyBuyOrderPostColumn(buyOrderId, diningTableId, billId, notes, menuStatus, dessertStatus, STATUS_SERVED);
    }
}


