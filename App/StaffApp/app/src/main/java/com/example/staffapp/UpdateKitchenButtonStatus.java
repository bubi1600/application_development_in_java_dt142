package com.example.staffapp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Button;
import java.util.Collections;

public class UpdateKitchenButtonStatus extends AsyncTask<Void, Void, Void> {
    private Button buttonTableOneStatus;
    private Button buttonTableTwoStatus;
    private Button buttonTableThreeStatus;
    private Button buttonTableFourStatus;
    private Button buttonTableFiveStatus;
    private Button buttonTableSixStatus;
    private Button buttonTableSevenStatus;

    private Api api;

    private final String STATUS_PREFIX = "Status: ";
    private final String STATUS_UPDATING = "Updating Status...";
    private final String STATUS_NONE = "None";
    private final String STATUS_DONE = "Done";
    private final String STATUS_PREPARING = "Preparing";
    private final String STATUS_SERVED = "Served";

    private final int TABLE_ONE_NR = 1;
    private final int TABLE_TWO_NR = 2;
    private final int TABLE_THREE_NR = 3;
    private final int TABLE_FOUR_NR = 4;
    private final int TABLE_FIVE_NR = 5;
    private final int TABLE_SIX_NR = 6;
    private final int TABLE_SEVEN_NR = 7;

    /**Constructor*/
    UpdateKitchenButtonStatus() { }

    public void init(Button buttonTableOneStatus, Button buttonTableTwoStatus, Button buttonTableThreeStatus, Button buttonTableFourStatus,
                     Button buttonTableFiveStatus, Button buttonTableSixStatus, Button buttonTableSevenStatus)
    {
        api = MainActivity.api;
        this.buttonTableOneStatus = buttonTableOneStatus;
        this.buttonTableTwoStatus = buttonTableTwoStatus;
        this.buttonTableThreeStatus = buttonTableThreeStatus;
        this.buttonTableFourStatus = buttonTableFourStatus;
        this.buttonTableFiveStatus = buttonTableFiveStatus;
        this.buttonTableSixStatus = buttonTableSixStatus;
        this.buttonTableSevenStatus = buttonTableSevenStatus;
        if(api.PARSEDApi().getBuyOrderList() != null)
            Collections.reverse(api.PARSEDApi().getBuyOrderList());
    }

    /**Allt som körs i denna function är det tråden kommer köra och upprepa hela tiden. Den uppdaterar köksstatus i appen
     * hela tiden*/
    @Override
    protected Void doInBackground(Void... voids)
    {
        while (true)
        {
            buttonTableOneStatus.setText(updateKitchenStatusBtn(TABLE_ONE_NR));
            buttonTableTwoStatus.setText(updateKitchenStatusBtn(TABLE_TWO_NR));
            buttonTableThreeStatus.setText(updateKitchenStatusBtn(TABLE_THREE_NR));
            buttonTableFourStatus.setText(updateKitchenStatusBtn(TABLE_FOUR_NR));
            buttonTableFiveStatus.setText(updateKitchenStatusBtn(TABLE_FIVE_NR));
            buttonTableSixStatus.setText(updateKitchenStatusBtn(TABLE_SIX_NR));
            buttonTableSevenStatus.setText(updateKitchenStatusBtn(TABLE_SEVEN_NR));

            //Vi pausar tråden 1 sekund.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**Skriver senaste köksstatusen från hämtade API:ett till alla bordknappar */
    String updateKitchenStatusBtn(int tableNr)
    {
        String status = STATUS_PREFIX+STATUS_NONE;

        if(api.PARSEDApi().getBuyOrderList() == null) //Om API:ett inte hunnit hämta in senaste data från databasen skriver vi ut "Updating status", så slipper vi krach pga nullpointer
            return STATUS_UPDATING;

        for (int i=0; i<api.PARSEDApi().getBuyOrderList().size(); i++)
        {
            if(Integer.parseInt(api.PARSEDApi().getBuyOrderList().get(i).diningTableId) == tableNr &&
                    (
                        !api.PARSEDApi().getBuyOrderList().get(i).statusMenu.matches(STATUS_SERVED) ||
                        !api.PARSEDApi().getBuyOrderList().get(i).statusAppetizer.matches(STATUS_SERVED) ||
                        !api.PARSEDApi().getBuyOrderList().get(i).statusDessert.matches(STATUS_SERVED)
                    )
            )
            {
                String menuStatus = api.PARSEDApi().getBuyOrderList().get(i).statusMenu;
                String appetizerStatus = api.PARSEDApi().getBuyOrderList().get(i).statusAppetizer;
                String dessertStatus = api.PARSEDApi().getBuyOrderList().get(i).statusDessert;
                if(menuStatus.equals(STATUS_DONE) || appetizerStatus.equals(STATUS_DONE) || dessertStatus.equals(STATUS_DONE))
                {
                    status = STATUS_DONE;
                    ChangeStatusTextColor(tableNr, status);
                    return STATUS_PREFIX+status;
                }
                if(menuStatus.equals(STATUS_PREPARING) || appetizerStatus.equals(STATUS_PREPARING) || dessertStatus.equals(STATUS_PREPARING))
                {
                    status = STATUS_PREPARING;
                    ChangeStatusTextColor(tableNr, status);
                    return STATUS_PREFIX+status;
                }
            }
        }
        ChangeStatusTextColor(tableNr, status);
        return status;
    }

    /**Ändrar färgen på köksstatusen beroende på om köksstatusen för bordet */
    void ChangeStatusTextColor(int tableNr, String kitchenStatus)
    {
        Button buttonTableStatus = null;
        if(tableNr == 1)
            buttonTableStatus = buttonTableOneStatus;
        else if(tableNr == 2)
            buttonTableStatus = buttonTableTwoStatus;
        else if(tableNr == 3)
            buttonTableStatus = buttonTableThreeStatus;
        else if(tableNr == 4)
            buttonTableStatus = buttonTableFourStatus;
        else if(tableNr == 5)
            buttonTableStatus = buttonTableFiveStatus;
        else if(tableNr == 6)
            buttonTableStatus = buttonTableSixStatus;
        else if(tableNr == 7)
            buttonTableStatus = buttonTableSevenStatus;

        if(buttonTableStatus == null)
            return;

        if(kitchenStatus.equals(STATUS_DONE))
        {
            buttonTableStatus.setTextColor(Color.GREEN);
            return;
        }
        if(kitchenStatus.equals(STATUS_PREPARING))
        {
            buttonTableStatus.setTextColor(Color.YELLOW);
            return;
        }
        if(kitchenStatus.equals(STATUS_PREFIX+STATUS_NONE))
        {
            buttonTableStatus.setTextColor(Color.WHITE);
            return;
        }
    }
}