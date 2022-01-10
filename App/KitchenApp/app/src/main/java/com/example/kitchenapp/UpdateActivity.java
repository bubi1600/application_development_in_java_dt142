package com.example.kitchenapp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.BoringLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class UpdateActivity extends AsyncTask<Void, Void, Void>
{
    private TableLayout tableLayout;
    private Api api;
    private List<ProccessedOrder> ordersList;
    private List<String> diningTableList;

    /**
     * Constructor
     */
    UpdateActivity(TableLayout tableLayout) {
        this.tableLayout = tableLayout;
        this.api = MainActivity.api;
        ordersList = new ArrayList<>();
        diningTableList = new ArrayList<>();
        if (api.PARSEDApi().getBuyOrderList() != null)
            Collections.reverse(MainActivity.api.PARSEDApi().getBuyOrderList());
    }

    /**
     * Allt som körs i denna function är det tråden kommer köra och upprepa hela tiden. Den uppdaterar köksstatus i appen
     * hela tiden
     */
    @Override
    protected Void doInBackground(Void... voids) {
        while (true)
        {
            processOrders();
            //Detta används så vi kan ändra tableLayout som tillhör MainActivity och inte denna klassen
            new Handler(Looper.getMainLooper()).post(new Runnable()
            {
                @Override
                public void run()
                {
                    tableLayout.removeAllViews();
                }
            });

            /**DiningTableNr, egen rad och bara en av dessa (med status Preparing och Done)*/
            TextView textViewDiningTableNrAll = new TextView(tableLayout.getContext());
            if(diningTableList.size() > 0)
            {
                String text = "Tables: ";
                for(int h=0; h<diningTableList.size(); h++)
                    text += " | " + diningTableList.get(h) + " | ";
                textViewDiningTableNrAll.setText(text);
                textViewDiningTableNrAll.setTextSize(22);
            }

            new Handler(Looper.getMainLooper()).post(new Runnable()
            {
                @Override
                public void run()
                {
                    tableLayout.addView(textViewDiningTableNrAll);
                }
            });

            if(ordersList.size() > 0)
            {
                /**AUTO GENERERING*/
                for (int i = 0; i < ordersList.size(); i++)
                {
                    /**ÖVRE OCH NEDRE HRISONTELLA LINJEN FÖR EN ORDER*/
                    View horizontalLineTop = new View(tableLayout.getContext());
                    LinearLayout linearLayoutTop = new LinearLayout(tableLayout.getContext());
                    View horizontalLineBottom = new View(tableLayout.getContext());
                    LinearLayout linearLayoutBottom = new LinearLayout(tableLayout.getContext());
                    horizontalLineTop.setLayoutParams(new LinearLayout.LayoutParams(tableLayout.getWidth(),2));
                    horizontalLineTop.setBackgroundColor(Color.parseColor("#000000"));
                    linearLayoutTop.addView(horizontalLineTop);
                    horizontalLineBottom.setLayoutParams(new LinearLayout.LayoutParams(tableLayout.getWidth(),2));
                    horizontalLineBottom.setBackgroundColor(Color.parseColor("#000000"));
                    linearLayoutBottom.addView(horizontalLineBottom);

                    /**DiningTableNr, egen rad (innehåller diningTableNr för nuvarande order vi jobbar med)*/
                    TextView textViewDiningTableNr = new TextView(tableLayout.getContext());
                    textViewDiningTableNr.setText("Table: " + ordersList.get(i).diningTableNr);
                    textViewDiningTableNr.setTextSize(24);

                    /**RAD 1. Skapa rad 1 innehållande Appetizer (om det finns i en order) och knapp som kallar på personalen*/
                    TableRow row1 = new TableRow(tableLayout.getContext());
                    if(ordersList.get(i).appetizers.size()>0)
                    {
                        TextView textViewAppertizerQuantity = new TextView(tableLayout.getContext()); //Column 1
                        TextView textViewAppertizerName = new TextView(tableLayout.getContext()); //Column 2
                        TextView textViewAppertizerTime = new TextView(tableLayout.getContext()); //Column 3
                        Button btnCallStaffAppertizer = new Button(tableLayout.getContext());

                        //Column 1, Row 1
                        textViewAppertizerQuantity.setTextSize(30);
                        textViewAppertizerQuantity.setText("");

                        //Column 2, Row 1
                        textViewAppertizerName.setTextSize(30);
                        textViewAppertizerName.setText("");
                        textViewAppertizerName.setWidth(600);

                        //Column 3, Row 1
                        textViewAppertizerTime.setText("");
                        textViewAppertizerTime.setTextSize(30);

                        //Column 4, Row 1
                        btnCallStaffAppertizer.setText("Done");
                        btnCallStaffAppertizer.setTextSize(20);
                        btnCallStaffAppertizer.setId(Integer.parseInt(ordersList.get(i).diningTableNr));
                        btnCallStaffAppertizer.setTextColor(btnCallStaffAppertizer.getContext().getResources().getColor(R.color.black));

                        if (ordersList.get(i) != null)
                        {
                            for (int j = 0; j < ordersList.get(i).appetizers.size(); j++)
                            {
                                textViewAppertizerQuantity.append(ordersList.get(i).appetizers.get(j).quantity + " st    \n");
                                textViewAppertizerName.append(ordersList.get(i).appetizers.get(j).foodId + "\n");
                                textViewAppertizerTime.append("Time: " + ordersList.get(i).appetizers.get(j).cookingTime + " min \n");
                            }
                        }

                        //Ordningen vi sätter in i row spelar roll:
                        row1.addView(textViewAppertizerQuantity); //Column 1. Då vi stoppar in denna först så blir detta kolumn 1
                        row1.addView(textViewAppertizerName); //Column 2
                        row1.addView(textViewAppertizerTime); //Column 3
                        row1.addView(btnCallStaffAppertizer); //Column 4

                        //VI LÄGGER TILL KALLA PÅ PERSONAL KNAPP OCH DESS DEFINITION
                        btnCallStaffAppertizer.setOnClickListener(new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                ArrayList<String> record = api.GETApi().getBuyOrderRecordWithDiningTableNr(btnCallStaffAppertizer.getId());
                                Integer buyOrderId = Integer.parseInt(record.get(0));
                                Integer diningTableId = Integer.parseInt(record.get(1));
                                Integer billId = Integer.parseInt(record.get(2));
                                String notes = record.get(3);
                                String statusMenu = record.get(4);
                                String statusDessert = record.get(5);
                                String statusAppetizer = record.get(6);
                                api.POSTApi().modifyBuyOrderPostColumn(buyOrderId, diningTableId, billId, notes,statusMenu, statusDessert, "Done");
                                row1.removeAllViews();
                                statusAppetizer = "Done";

                                for(ProccessedOrder order : ordersList)
                                {
                                    if(order.buyOrderId.equals(buyOrderId) && ( statusMenu.equals("Done") && statusDessert.equals("Done") && statusAppetizer.equals("Done")) )
                                    {
                                        linearLayoutTop.removeAllViews();
                                        linearLayoutBottom.removeAllViews();
                                        textViewDiningTableNr.setText("");
                                        textViewDiningTableNrAll.setText("");
                                    }
                                }

                            }
                        });
                    }

                    /**RAD 2. Skapa rad 2 innehållande Menu (om det finns i en order) och knapp som kallar på personalen*/
                    TableRow row2 = new TableRow(tableLayout.getContext());
                    if(ordersList.get(i).menus.size()>0)
                    {
                        TextView textViewMenuQuantity = new TextView(tableLayout.getContext()); //Column 1
                        TextView textViewMenuName = new TextView(tableLayout.getContext()); //Column 2
                        TextView textViewMenuTime = new TextView(tableLayout.getContext()); //Column 3
                        Button btnCallStaffMenu = new Button(tableLayout.getContext()); //Column 4


                        //Column 1, Row 2
                        textViewMenuQuantity.setTextSize(30);
                        textViewMenuQuantity.setText("");

                        //Column 2, Row 2
                        textViewMenuName.setTextSize(30);
                        textViewMenuName.setText("");
                        textViewMenuName.setWidth(600);

                        //Column 3, Row 2
                        textViewMenuTime.setText("");
                        textViewMenuTime.setTextSize(30);

                        //Column 4, Row 2
                        btnCallStaffMenu.setText("Done");
                        btnCallStaffMenu.setTextSize(20);
                        btnCallStaffMenu.setId(Integer.parseInt(ordersList.get(i).diningTableNr));
                        btnCallStaffMenu.setTextColor(btnCallStaffMenu.getContext().getResources().getColor(R.color.black));

                        if (ordersList.get(i) != null)
                        {
                            for (int j = 0; j < ordersList.get(i).menus.size(); j++)
                            {
                                textViewMenuQuantity.append(ordersList.get(i).menus.get(j).quantity + " st    \n");
                                textViewMenuName.append(ordersList.get(i).menus.get(j).foodId + "\n");
                                textViewMenuTime.append("Time: " + ordersList.get(i).menus.get(j).cookingTime + " min \n");
                            }
                        }

                        //Ordningen vi sätter in i row spelar roll:
                        row2.addView(textViewMenuQuantity); //Column 1. Då vi stoppar in denna först så blir detta kolumn 1
                        row2.addView(textViewMenuName); //Column 2
                        row2.addView(textViewMenuTime); //Column 3
                        row2.addView(btnCallStaffMenu); //Column 4

                        //VI LÄGGER TILL KALLA PÅ PERSONAL KNAPP OCH DESS DEFINITION
                        btnCallStaffMenu.setOnClickListener(new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                ArrayList<String> record = api.GETApi().getBuyOrderRecordWithDiningTableNr(btnCallStaffMenu.getId());
                                Integer buyOrderId = Integer.parseInt(record.get(0));
                                Integer diningTableId = Integer.parseInt(record.get(1));
                                Integer billId = Integer.parseInt(record.get(2));
                                String notes = record.get(3);
                                String statusMenu = record.get(4);
                                String statusDessert = record.get(5);
                                String statusAppetizer = record.get(6);
                                api.POSTApi().modifyBuyOrderPostColumn(buyOrderId, diningTableId, billId, notes,"Done", statusDessert, statusAppetizer);
                                row2.removeAllViews();
                                statusMenu = "Done";

                                for(ProccessedOrder order : ordersList)
                                {
                                    if(order.buyOrderId.equals(buyOrderId) && ( statusMenu.equals("Done") && statusDessert.equals("Done") && statusAppetizer.equals("Done")) )
                                    {
                                        linearLayoutTop.removeAllViews();
                                        linearLayoutBottom.removeAllViews();
                                        textViewDiningTableNr.setText("");
                                        textViewDiningTableNrAll.setText("");
                                    }
                                }

                            }
                        });
                    }

                    /**RAD 3. Skapa rad 1 innehållande Dessert (om det finns i en order) och knapp som kallar på personalen*/
                    TableRow row3 = new TableRow(tableLayout.getContext());
                    if(ordersList.get(i).desserts.size()>0)
                    {
                        TextView textViewDessertQuantity = new TextView(tableLayout.getContext()); //Column 1
                        TextView textViewDessertName = new TextView(tableLayout.getContext()); //Column 2
                        TextView textViewDessertTime = new TextView(tableLayout.getContext()); //Column 3
                        Button btnCallStaffDessert = new Button(tableLayout.getContext()); //Column 4

                        //Column 1, Row 3
                        textViewDessertQuantity.setTextSize(30);
                        textViewDessertQuantity.setText("");

                        //Column 2, Row 3
                        textViewDessertName.setTextSize(30);
                        textViewDessertName.setText("");
                        textViewDessertName.setWidth(600);

                        //Column 3, Row 3
                        textViewDessertTime.setText("");
                        textViewDessertTime.setTextSize(30);

                        //Column 4, Row 3
                        btnCallStaffDessert.setText("Done");
                        btnCallStaffDessert.setTextSize(20);
                        btnCallStaffDessert.setId(Integer.parseInt(ordersList.get(i).diningTableNr));
                        btnCallStaffDessert.setTextColor(btnCallStaffDessert.getContext().getResources().getColor(R.color.black));

                        if (ordersList.get(i) != null)
                        {
                            for (int j = 0; j < ordersList.get(i).desserts.size(); j++)
                            {
                                textViewDessertQuantity.append(ordersList.get(i).desserts.get(j).quantity + " st    \n");
                                textViewDessertName.append(ordersList.get(i).desserts.get(j).foodId + "\n");
                                textViewDessertTime.append("Time: " + ordersList.get(i).desserts.get(j).cookingTime + " min \n");
                            }
                        }

                        //Ordningen vi sätter in i row spelar roll:
                        row3.addView(textViewDessertQuantity); //Column 1. Då vi stoppar in denna först så blir detta kolumn 1
                        row3.addView(textViewDessertName); //Column 2
                        row3.addView(textViewDessertTime); //Column 3
                        row3.addView(btnCallStaffDessert); //Column 4

                        //VI LÄGGER TILL KALLA PÅ PERSONAL KNAPP OCH DESS DEFINITION
                        btnCallStaffDessert.setOnClickListener(new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                ArrayList<String> record = api.GETApi().getBuyOrderRecordWithDiningTableNr(btnCallStaffDessert.getId());
                                Integer buyOrderId = Integer.parseInt(record.get(0));
                                Integer diningTableId = Integer.parseInt(record.get(1));
                                Integer billId = Integer.parseInt(record.get(2));
                                String notes = record.get(3);
                                String statusMenu = record.get(4);
                                String statusDessert = record.get(5);
                                String statusAppetizer = record.get(6);
                                api.POSTApi().modifyBuyOrderPostColumn(buyOrderId, diningTableId, billId, notes,statusMenu, "Done", statusAppetizer);
                                row3.removeAllViews();
                                statusDessert = "Done";

                                for(ProccessedOrder order : ordersList)
                                {
                                    if(order.buyOrderId.equals(buyOrderId) && ( statusMenu.equals("Done") && statusDessert.equals("Done") && statusAppetizer.equals("Done")) )
                                    {
                                        linearLayoutTop.removeAllViews();
                                        linearLayoutBottom.removeAllViews();
                                        textViewDiningTableNr.setText("");
                                        textViewDiningTableNrAll.setText("");
                                    }
                                }

                            }
                        });

                    }

                    /**VI lägger till order notes, om det finns någon*/
                    TextView textViewOrderNotes = new TextView(tableLayout.getContext());
                    if(!ordersList.get(i).notes.equals("None")) //Om det inte finns notes, så visa inte denna kolumn
                        textViewOrderNotes.setText("Notes: " + ordersList.get(i).notes);
                    textViewOrderNotes.setTextSize(24);
                    textViewOrderNotes.setTextColor(Color.RED);
                    textViewOrderNotes.setWidth(600);


                    //Detta används så vi kan ändra tableLayout som tillhör MainActivity och inte denna klassen
                    new Handler(Looper.getMainLooper()).post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            tableLayout.addView(linearLayoutTop, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)); //Rad 1
                            tableLayout.addView(textViewDiningTableNr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)); //Rad 2
                            tableLayout.addView(textViewOrderNotes, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                            tableLayout.addView(row1, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)); //Rad 3
                            tableLayout.addView(row2, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)); //Rad 4
                            tableLayout.addView(row3, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)); //Rad 5
                            tableLayout.addView(linearLayoutBottom, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)); //Rad 6
                        }
                    });
                }
            }
            else
            {
                TableRow row = new TableRow(tableLayout.getContext());
                TextView textViewNoOrders = new TextView(tableLayout.getContext());
                textViewNoOrders.setTextSize(30);
                textViewNoOrders.setText("No Orders Has Been Made");

                //Detta används så vi kan ändra tableLayout som tillhör MainActivity och inte denna klassen
                new Handler(Looper.getMainLooper()).post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    }
                });
            }

            //Vi pausar tråden 2 sekund.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void processOrders()
    {
        if (api.PARSEDApi().getBuyOrderHasLunchAndMenuList() == null)
            return;
        if (api.PARSEDApi().getBuyOrderList() == null)
            return;
        if(api.PARSEDApi().getMenuList() == null)
            return;
        ordersList.clear();
        diningTableList.clear();

        /**Hämta alla måltider men status "Preparing" och lagra dess info i lista*/
        int iterate = 0;
        for (int i = 0; i < api.PARSEDApi().getBuyOrderList().size(); i++)
        {
            if(api.PARSEDApi().getBuyOrderList().get(i).statusMenu.equals("Preparing") ||
                    api.PARSEDApi().getBuyOrderList().get(i).statusAppetizer.equals("Preparing") ||
                    api.PARSEDApi().getBuyOrderList().get(i).statusDessert.equals("Preparing"))
            {
                ordersList.add(new ProccessedOrder());
                ordersList.get(iterate).notes = api.PARSEDApi().getBuyOrderList().get(i).notes;
                ordersList.get(iterate).diningTableNr = api.PARSEDApi().getBuyOrderList().get(i).diningTableId;
                ordersList.get(iterate).buyOrderId = api.PARSEDApi().getBuyOrderList().get(i).buyOrderId;

                if(api.PARSEDApi().getBuyOrderList().get(i).statusMenu.equals("Preparing"))
                {
                    //Hämta alla menyer som ska tillagas och antalet av dem
                    if(api.PARSEDApi().getBuyOrderHasLunchAndMenuList().size()>0)
                    {
                        for(int j=0; j<api.PARSEDApi().getBuyOrderHasLunchAndMenuList().size(); j++)
                        {
                            if(api.PARSEDApi().getBuyOrderList().get(i).buyOrderId.equals(api.PARSEDApi().getBuyOrderHasLunchAndMenuList().get(j).buyOrderId))
                            {
                                String quantity = api.PARSEDApi().getBuyOrderHasLunchAndMenuList().get(j).quantity;
                                String foodId = api.PARSEDApi().getBuyOrderHasLunchAndMenuList().get(j).foodId;
                                ordersList.get(iterate).menus.add(new Menu(foodId, quantity));
                            }
                        }
                    }
                }

                if(api.PARSEDApi().getBuyOrderList().get(i).statusAppetizer.equals("Preparing"))
                {
                    //Hämta alla Appetizer som ska tillagas och antalet av dem
                    if(api.PARSEDApi().getBuyOrderHasAppetizerList().size()>0)
                    {
                        for(int j=0; j<api.PARSEDApi().getBuyOrderHasAppetizerList().size(); j++)
                        {
                            if(api.PARSEDApi().getBuyOrderList().get(i).buyOrderId.equals(api.PARSEDApi().getBuyOrderHasAppetizerList().get(j).buyOrderId))
                            {
                                String quantity = api.PARSEDApi().getBuyOrderHasAppetizerList().get(j).quantity;
                                String foodId = api.PARSEDApi().getBuyOrderHasAppetizerList().get(j).appetizerId;
                                ordersList.get(iterate).appetizers.add(new Menu(foodId, quantity));
                            }
                        }
                    }
                }

                if(api.PARSEDApi().getBuyOrderList().get(i).statusDessert.equals("Preparing"))
                {
                    if(api.PARSEDApi().getBuyOrderHasDessertList().size()>0)
                    {
                        //Hämta alla Dessert som ska tillagas och antalet av dem
                        for(int j=0; j<api.PARSEDApi().getBuyOrderHasDessertList().size(); j++)
                        {
                            if(api.PARSEDApi().getBuyOrderList().get(i).buyOrderId.equals(api.PARSEDApi().getBuyOrderHasDessertList().get(j).buyOrderId))
                            {
                                String quantity = api.PARSEDApi().getBuyOrderHasDessertList().get(j).quantity;
                                String foodId = api.PARSEDApi().getBuyOrderHasDessertList().get(j).dessertId;
                                ordersList.get(iterate).desserts.add(new Menu(foodId, quantity));
                            }
                        }
                    }
                }
                iterate++;
            }
        }

        /**Lagra alla bord till de order som har statusen "Preparing" eller "Done" så kocken kan se borden en order ska till även personal är kallade på och ordern
         * inte finns kvar i listan, så länge servitören har menyer att hämta hos kocken så kan kocken se vilka menyer som tillhörde vilka bord*/
        for (int i = 0; i < api.PARSEDApi().getBuyOrderList().size(); i++)
        {
            if( (api.PARSEDApi().getBuyOrderList().get(i).statusMenu.equals("Preparing") || api.PARSEDApi().getBuyOrderList().get(i).statusMenu.equals("Done") ) ||
                    (api.PARSEDApi().getBuyOrderList().get(i).statusAppetizer.equals("Preparing") || api.PARSEDApi().getBuyOrderList().get(i).statusAppetizer.equals("Done") ) ||
                    (api.PARSEDApi().getBuyOrderList().get(i).statusDessert.equals("Preparing") || api.PARSEDApi().getBuyOrderList().get(i).statusDessert.equals("Done") )
            )
            {
                diningTableList.add(api.PARSEDApi().getBuyOrderList().get(i).diningTableId);
            }
        }

        /**Lagra tiden det tar att tillaga en meny för alla menyer*/
        for (int i = 0; i < ordersList.size(); i++)
        {
            //Lagra tiden för alla menyer som finns i ordern
            for(int j=0; j<ordersList.get(i).menus.size(); j++)
            {
                for(int h=0; h<api.PARSEDApi().getMenuList().size(); h++)
                {
                    if(ordersList.get(i).menus.get(j).foodId.equals(api.PARSEDApi().getMenuList().get(h).menuName))
                    {
                        ordersList.get(i).menus.get(j).cookingTime = api.PARSEDApi().getMenuList().get(h).cookingTime;
                        break;
                    }
                }
            }
            //Lagra tiden för alla appetizer som finns i ordern
            for(int j=0; j<ordersList.get(i).appetizers.size(); j++)
            {
                for(int h=0; h<api.PARSEDApi().getAppetizerList().size(); h++)
                {
                    if(ordersList.get(i).appetizers.get(j).foodId.equals(api.PARSEDApi().getAppetizerList().get(h).appetizerId))
                    {
                        ordersList.get(i).appetizers.get(j).cookingTime = api.PARSEDApi().getAppetizerList().get(h).cookingTime;
                        break;
                    }
                }
            }
            //Lagra tiden för alla dessert som finns i ordern
            for(int j=0; j<ordersList.get(i).desserts.size(); j++)
            {
                for(int h=0; h<api.PARSEDApi().getDessertList().size(); h++)
                {
                    if(ordersList.get(i).desserts.get(j).foodId.equals(api.PARSEDApi().getDessertList().get(h).dessertId))
                    {
                        ordersList.get(i).desserts.get(j).cookingTime = api.PARSEDApi().getDessertList().get(h).cookingTime;
                        break;
                    }
                }
            }

        }

        /**Sortera efter måltider som tar längst tid att tillaga*/
        for (ProccessedOrder orders : ordersList)
        {
            //Sortera menyer
            Collections.sort(orders.menus, new Comparator<Menu>() {
                @Override
                public int compare(Menu o1, Menu o2) {
                    return o1.cookingTime.compareTo(o2.cookingTime);
                }
            });
            //Sortera dessert
            Collections.sort(orders.desserts, new Comparator<Menu>() {
                @Override
                public int compare(Menu o1, Menu o2) {
                    return o1.cookingTime.compareTo(o2.cookingTime);
                }
            });
            //Sortera appetizer
            Collections.sort(orders.appetizers, new Comparator<Menu>() {
                @Override
                public int compare(Menu o1, Menu o2) {
                    return o1.cookingTime.compareTo(o2.cookingTime);
                }
            });
        }

        for (ProccessedOrder order : ordersList)
        {
            Collections.reverse(order.menus);
            Collections.reverse(order.appetizers);
            Collections.reverse(order.desserts);
        }
    }
}

class ProccessedOrder
{
    public String notes;
    public String diningTableNr;
    public String buyOrderId;
    List<Menu> menus = new ArrayList<>();
    List<Menu> desserts = new ArrayList<>();
    List<Menu> appetizers = new ArrayList<>();
}

class Menu
{
    public String foodId;
    public String quantity;
    public String cookingTime;
    Menu(String foodId, String quantity)
    {
        this.foodId = foodId;
        this.quantity = quantity;
    }
}
