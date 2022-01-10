package com.example.staffapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class Specifics extends AppCompatActivity {
    private TextView textViewInformation;
    private int tableNr;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifics);

        api = MainActivity.api;
        textViewInformation = (TextView) findViewById(R.id.textViewInformation);
        textViewInformation.setTextSize(25);
        tableNr = getIntent().getIntExtra("tableNr", 0);


        List<TableBuyOrder> buyOrderListTemp = api.PARSEDApi().getBuyOrderList();

        String formattedData = "";
        String buyOrderId = "";

        Collections.reverse(buyOrderListTemp);
        for(TableBuyOrder order : buyOrderListTemp)
        {
            if(order.diningTableId.equals(Integer.toString(tableNr)))
            {
                buyOrderId = order.buyOrderId;
                break;
            }
        }

        //Hämta alla förrätter som tillhör valda bordet
        Boolean bprintTitle = true;
        if (api.PARSEDApi().getBuyOrderHasAppetizerList().size() > 0)
        {

            for(TableBuyOrderHasAppetizer appetizer : api.PARSEDApi().getBuyOrderHasAppetizerList())
            {
                if(buyOrderId.equals(appetizer.buyOrderId))
                {
                    if(bprintTitle)
                        formattedData += "Appetizer:\n" ;
                    formattedData += appetizer.quantity + " " + appetizer.appetizerId + "\n";
                    bprintTitle = false;
                }
            }
        }

        //Hämta alla menyer som tillhör valda bordet
        bprintTitle = true;
        if (api.PARSEDApi().getBuyOrderHasLunchAndMenuList().size() > 0)
        {
            for(TableBuyOrderHasLunchAndMenu menu : api.PARSEDApi().getBuyOrderHasLunchAndMenuList())
            {
                if(buyOrderId.equals(menu.buyOrderId))
                {
                    if(bprintTitle)
                        formattedData += "\nMenu:\n";
                    formattedData += menu.quantity + " " + menu.foodId + "\n";
                    bprintTitle = false;
                }
            }
        }

        //Hämta alla efterrätter som tillhör valda bordet
        bprintTitle = true;
        if (api.PARSEDApi().getBuyOrderHasDessertList().size() > 0)
        {
            for(TableBuyOrderHasDessert dessert : api.PARSEDApi().getBuyOrderHasDessertList())
            {
                if(buyOrderId.equals(dessert.buyOrderId))
                {
                    if(bprintTitle)
                        formattedData += "\nDessert:\n";
                    formattedData += dessert.quantity + " " + dessert.dessertId + "\n";
                    bprintTitle = false;
                }
            }
        }

        //Hämta alla dryck som tillhör valda bordet
        bprintTitle = true;
        if (api.PARSEDApi().getBuyOrderHasDrinkList().size() > 0)
        {
            for(TableBuyOrderHasDrink drink : api.PARSEDApi().getBuyOrderHasDrinkList())
            {
                if(buyOrderId.equals(drink.buyOrderId))
                {
                    if(bprintTitle)
                        formattedData += "\nDrink:\n" ;
                    formattedData += drink.quantity + " " + drink.drinkId + "\n";
                    bprintTitle = false;
                }
            }
        }

        if(!formattedData.equals(""))
            textViewInformation.setText(formattedData);
        else
            textViewInformation.setText("No orders available for this table");
    }
}
