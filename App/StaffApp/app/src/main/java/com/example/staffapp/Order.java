package com.example.staffapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order extends AppCompatActivity {
    private Api api;
    public static String addedNotes;
    private Button btnMenu;
    private Button btnDrinks;
    private Button btnAppetizer;
    private Button btnDessert;
    private TextView tableNrTextView;
    private TextView buyItemSummeryTextView;
    private Button btnAddNotes;
    private Button btnSendOrder;
    private int tableNr;
    private float totalPrice;
    private TableLayout getAllMenuAndDrinksTableLayout;
    private TextView totalPriceTextView;
    private HashMap<String, Integer> savedMenuAmount;
    private HashMap<String, Integer> savedDrinksAmount;
    private HashMap<String, Integer> savedAppetizerAmount;
    private HashMap<String, Integer> savedDessertAmount;
    private final String BILL_STATUS = "Unpaid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        /**INITIALIZE*/
        btnMenu = (Button)findViewById(R.id.buttonMenu);
        btnDrinks = (Button)findViewById(R.id.buttonDrinks);
        btnAppetizer = (Button)findViewById(R.id.buttonAppetizer);
        btnDessert = (Button)findViewById(R.id.buttonDessert);
        tableNrTextView = (TextView)findViewById(R.id.textViewTableNr);
        getAllMenuAndDrinksTableLayout = (TableLayout)findViewById(R.id.TableLayout01);
        buyItemSummeryTextView = (TextView)findViewById(R.id.total);
        btnAddNotes = (Button)findViewById(R.id.buttonAddNotes);
        api = MainActivity.api;
        btnSendOrder = (Button)findViewById(R.id.buttonSendOrder);
        totalPriceTextView = (TextView)findViewById(R.id.totalPrice);
        savedMenuAmount = new HashMap<String, Integer>();
        savedDrinksAmount = new HashMap<String, Integer>();
        savedAppetizerAmount = new HashMap<String, Integer>();
        savedDessertAmount = new HashMap<String, Integer>();
        tableNr = getIntent().getIntExtra("tableNr", 0);;
        addedNotes = "";

        tableNrTextView.setText("Table: " + Integer.toString(tableNr));

        /**AUTO GENERATE*/
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getAllMenuAndDrinksTableLayout.removeAllViews();
                for (int i = 0; i < api.PARSEDApi().getMenuList().size(); i++)
                {
                    TableRow row = new TableRow(Order.this);
                    TextView menuName = new TextView(Order.this);
                    TextView menuPrice = new TextView(Order.this);
                    TextView amount = new TextView(Order.this);
                    Button btnDecrement = new Button(Order.this);
                    Button btnIncrement = new Button(Order.this);

                    //Column 1
                    menuName.setWidth(600);
                    menuName.setTextSize(30);
                    menuName.setText(api.PARSEDApi().getMenuList().get(i).menuName);

                    //Column 2
                    menuPrice.setTextSize(30);
                    menuPrice.setWidth(250);
                    menuPrice.setText(api.PARSEDApi().getMenuList().get(i).price);

                    //Column 3
                    btnDecrement.setText("-");
                    btnDecrement.setTextSize(30);
                    btnDecrement.setTextColor(btnDecrement.getContext().getResources().getColor(R.color.black));

                    //Column 4
                    amount.setTextSize(30);
                    if(null == savedMenuAmount.get(menuName.getText()))
                    {
                        amount.setText("0");
                    }
                    else
                        amount.setText(Integer.toString(savedMenuAmount.get(menuName.getText())));
                    amount.setTextColor(amount.getContext().getResources().getColor(R.color.black));

                    //Column 5
                    btnIncrement.setText("+");
                    btnIncrement.setTextSize(30);
                    btnIncrement.setTextColor(btnIncrement.getContext().getResources().getColor(R.color.black));

                    //Ordningen vi sätter in i row spelar roll:
                    row.addView(menuName); //Column 1
                    row.addView(menuPrice); //Column 2
                    row.addView(btnDecrement); //Column 3
                    row.addView(amount); //Column 4
                    row.addView(btnIncrement); //Column 5

                    //Vi lägger till en listener till nuvarande - knappen i for-loopen
                    btnDecrement.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if(Integer.parseInt((String) amount.getText()) != 0)
                        {
                            amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) -1)));
                            totalPrice -= Float.parseFloat((String) menuPrice.getText());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//Vi tar bort 1 antal av vald meny i hashtable
                                savedMenuAmount.remove(menuName.getText(), 1);
                            }
                            totalPriceTextView.setText( "Sum: " + Float.toString(totalPrice) + " kr" );
                            savedMenuAmount.put((String) menuName.getText(), Integer.parseInt((String) amount.getText()) );
                            print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                        }
                        print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                    }
                    });;

                    //Vi lägger till en listener till nuvarande + knappen i for-loopen
                    btnIncrement.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) +1)));
                            totalPrice += Float.parseFloat((String) menuPrice.getText());
                            savedMenuAmount.put((String) menuName.getText(), Integer.parseInt ((String) amount.getText()));
                            print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                            totalPriceTextView.setText( "Sum: " + Float.toString(totalPrice) + " kr" );
                            savedMenuAmount.put((String) menuName.getText(), Integer.parseInt((String) amount.getText()) );
                        }
                    });;
                    getAllMenuAndDrinksTableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        });

        //AUTO GENERERAR RADER MED ALLA DRYCK NAMN, PRIS, +KNAPP, ANTAL, +KNAPP
        btnDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getAllMenuAndDrinksTableLayout.removeAllViews();
                for (int i = 0; i < api.PARSEDApi().getDrinkList().size(); i++)
                {
                    TableRow row = new TableRow(Order.this);
                    TextView drinkName = new TextView(Order.this);
                    TextView drinkPrice = new TextView(Order.this);
                    TextView amount = new TextView(Order.this);
                    Button btnDecrement = new Button(Order.this);
                    Button btnIncrement = new Button(Order.this);

                    //Column 1
                    drinkName.setWidth(600);
                    drinkName.setTextSize(30);
                    drinkName.setText(api.PARSEDApi().getDrinkList().get(i).drinkName);

                    //Column 2
                    drinkPrice.setTextSize(30);
                    drinkPrice.setWidth(250);
                    drinkPrice.setText(api.PARSEDApi().getDrinkList().get(i).price);

                    //Column 3
                    btnDecrement.setText("-");
                    btnDecrement.setTextSize(30);
                    btnDecrement.setTextColor(btnDecrement.getContext().getResources().getColor(R.color.black));

                    //Column 4
                    amount.setTextSize(30);
                    if(null == savedDrinksAmount.get(drinkName.getText()))
                    {
                        amount.setText("0");
                    }
                    else
                        amount.setText(Integer.toString(savedDrinksAmount.get(drinkName.getText())));
                    amount.setTextColor(amount.getContext().getResources().getColor(R.color.black));

                    //Column 5
                    btnIncrement.setText("+");
                    btnIncrement.setTextSize(30);
                    btnIncrement.setTextColor(btnIncrement.getContext().getResources().getColor(R.color.black));

                    row.addView(drinkName);
                    row.addView(drinkPrice);
                    row.addView(btnDecrement);
                    row.addView(amount);
                    row.addView(btnIncrement);

                    btnDecrement.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if(Integer.parseInt((String) amount.getText()) != 0)
                            {
                                amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) -1)));
                                totalPrice -= Float.parseFloat((String) drinkPrice.getText());
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    savedDrinksAmount.remove(drinkPrice.getText(), 1);
                                }
                                totalPriceTextView.setText( "Sum: " + Float.toString(totalPrice) + " kr" );
                                savedDrinksAmount.put((String) drinkName.getText(), Integer.parseInt((String) amount.getText()) );
                                print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                            }
                            print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                        }
                    });;

                    btnIncrement.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) +1)));
                            totalPrice += Float.parseFloat((String) drinkPrice.getText());
                            savedDrinksAmount.put((String) drinkName.getText(), Integer.parseInt((String) amount.getText()) );
                            print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                            totalPriceTextView.setText( "Sum: " + Float.toString(totalPrice) + " kr" );
                            savedDrinksAmount.put((String) drinkName.getText(), Integer.parseInt((String) amount.getText()) );
                        }
                    });;
                    getAllMenuAndDrinksTableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        });

        btnAppetizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getAllMenuAndDrinksTableLayout.removeAllViews();
                for (int i = 0; i < api.PARSEDApi().getAppetizerList().size(); i++)
                {
                    TableRow row = new TableRow(Order.this);
                    TextView appetizerName = new TextView(Order.this);
                    TextView appetizerPrice = new TextView(Order.this);
                    TextView amount = new TextView(Order.this);
                    Button btnDecrement = new Button(Order.this);
                    Button btnIncrement = new Button(Order.this);

                    //Column 1
                    appetizerName.setWidth(600);
                    appetizerName.setTextSize(30);
                    appetizerName.setText(api.PARSEDApi().getAppetizerList().get(i).appetizerId);

                    //Column 2
                    appetizerPrice.setTextSize(30);
                    appetizerPrice.setWidth(250);
                    appetizerPrice.setText(api.PARSEDApi().getAppetizerList().get(i).price);

                    //Column 3
                    btnDecrement.setText("-");
                    btnDecrement.setTextSize(30);
                    btnDecrement.setTextColor(btnDecrement.getContext().getResources().getColor(R.color.black));

                    //Column 4
                    amount.setTextSize(30);
                    if(null == savedAppetizerAmount.get(appetizerName.getText()))
                    {
                        amount.setText("0");
                    }
                    else
                        amount.setText(Integer.toString(savedAppetizerAmount.get(appetizerName.getText())));
                    amount.setTextColor(amount.getContext().getResources().getColor(R.color.black));

                    //Column 5
                    btnIncrement.setText("+");
                    btnIncrement.setTextSize(30);
                    btnIncrement.setTextColor(btnIncrement.getContext().getResources().getColor(R.color.black));

                    row.addView(appetizerName);
                    row.addView(appetizerPrice);
                    row.addView(btnDecrement);
                    row.addView(amount);
                    row.addView(btnIncrement);

                    btnDecrement.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if(Integer.parseInt((String) amount.getText()) != 0)
                            {
                                amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) -1)));
                                totalPrice -= Float.parseFloat((String) appetizerPrice.getText());
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    savedAppetizerAmount.remove(appetizerPrice.getText(), 1);
                                }
                                totalPriceTextView.setText( "Sum: " + Float.toString(totalPrice) + " kr" );
                                savedAppetizerAmount.put((String) appetizerName.getText(), Integer.parseInt((String) amount.getText()) );
                                print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                            }
                            print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                        }
                    });;

                    btnIncrement.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) +1)));
                            totalPrice += Float.parseFloat((String) appetizerPrice.getText());
                            savedAppetizerAmount.put((String) appetizerName.getText(), Integer.parseInt((String) amount.getText()) );
                            print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                            totalPriceTextView.setText( "Sum: " + Float.toString(totalPrice) + " kr" );
                            savedAppetizerAmount.put((String) appetizerName.getText(), Integer.parseInt((String) amount.getText()) );
                        }
                    });;
                    getAllMenuAndDrinksTableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        });



        btnDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getAllMenuAndDrinksTableLayout.removeAllViews();
                for (int i = 0; i < api.PARSEDApi().getDessertList().size(); i++)
                {
                    TableRow row = new TableRow(Order.this);
                    TextView dessertName = new TextView(Order.this);
                    TextView dessertPrice = new TextView(Order.this);
                    TextView amount = new TextView(Order.this);
                    Button btnDecrement = new Button(Order.this);
                    Button btnIncrement = new Button(Order.this);

                    //Column 1
                    dessertName.setWidth(600);
                    dessertName.setTextSize(30);
                    dessertName.setText(api.PARSEDApi().getDessertList().get(i).dessertId);

                    //Column 2
                    dessertPrice.setTextSize(30);
                    dessertPrice.setWidth(250);
                    dessertPrice.setText(api.PARSEDApi().getDessertList().get(i).price);

                    //Column 3
                    btnDecrement.setText("-");
                    btnDecrement.setTextSize(30);
                    btnDecrement.setTextColor(btnDecrement.getContext().getResources().getColor(R.color.black));

                    //Column 4
                    amount.setTextSize(30);
                    if(null == savedDessertAmount.get(dessertName.getText()))
                    {
                        amount.setText("0");
                    }
                    else
                        amount.setText(Integer.toString(savedDessertAmount.get(dessertName.getText())));
                    amount.setTextColor(amount.getContext().getResources().getColor(R.color.black));

                    //Column 5
                    btnIncrement.setText("+");
                    btnIncrement.setTextSize(30);
                    btnIncrement.setTextColor(btnIncrement.getContext().getResources().getColor(R.color.black));

                    row.addView(dessertName);
                    row.addView(dessertPrice);
                    row.addView(btnDecrement);
                    row.addView(amount);
                    row.addView(btnIncrement);

                    btnDecrement.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if(Integer.parseInt((String) amount.getText()) != 0)
                            {
                                amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) -1)));
                                totalPrice -= Float.parseFloat((String) dessertPrice.getText());
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    savedDessertAmount.remove(dessertPrice.getText(), 1);
                                }
                                totalPriceTextView.setText( "Sum: " + Float.toString(totalPrice) + " kr" );
                                savedDessertAmount.put((String) dessertName.getText(), Integer.parseInt((String) amount.getText()) );
                                print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                            }
                            print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                        }
                    });;

                    btnIncrement.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            amount.setText(Integer.toString((Integer.parseInt((String) amount.getText()) +1)));
                            totalPrice += Float.parseFloat((String) dessertPrice.getText());
                            savedDessertAmount.put((String) dessertName.getText(), Integer.parseInt((String) amount.getText()) );
                            print(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount);
                            totalPriceTextView.setText( "Sum: " + Float.toString(totalPrice) + " kr" );
                            savedDessertAmount.put((String) dessertName.getText(), Integer.parseInt((String) amount.getText()) );
                        }
                    });;
                    getAllMenuAndDrinksTableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        });

        /**Knapp för att lägga till anteckningar*/
        btnAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNotesPopup();
            }
        });

        /**Knapp för att skicka order*/
        btnSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalPrice == 0.0)
                    return;
                if(addedNotes.equals(""))
                    addedNotes = "None";

                String kitchenMenuStatus = "None";
                String kitchenAppetizerStatus = "None";
                String kitchenDessertStatus = "None";

                if(savedMenuAmount.size()>0)
                    kitchenMenuStatus = "Preparing";

                if(savedAppetizerAmount.size()>0)
                    kitchenAppetizerStatus = "Preparing";

                if(savedDessertAmount.size()>0)
                    kitchenDessertStatus = "Preparing";

                api.POSTApi().createBillPost(BILL_STATUS, totalPrice);
                int billPostId = api.GETApi().getLastBillRecordId();

                api.POSTApi().createBuyOrderPost(tableNr, billPostId, addedNotes, kitchenMenuStatus, kitchenDessertStatus, kitchenAppetizerStatus);
                int buyOrderPostId = api.GETApi().getLastBuyOrderRecordId();
                List<InventoryModifier> proccessedList = getProccessedInventoryModifierList();

                api.POSTApi().setSendItems(savedMenuAmount, savedDrinksAmount, savedAppetizerAmount, savedDessertAmount, proccessedList, buyOrderPostId);
            }

        });
    }

    /**
     * Kollar om en HashMap är tom, returnerar true om den är tom
     * @param map
     * @return
     */
    boolean areValuesZero(Map<String,Integer> map)
    {
        Collection<Integer> values = map.values();
        for (int val : values)
        {
            if(val != 0)
                return false;
        }
        return true;
    }

    /**
     * Aktiverar Notes knappen popup
     */
    void btnNotesPopup()
    {
        Intent intent = new Intent(this, OrderPopUp.class);
        startActivity(intent);
    }

    /**
     * Kontrollerar om en map är tom, och då skrivs inte någon i dess innehåll ut i appen.
     * @param menu
     * @param drink
     * @param appetizer
     * @param dessert
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    void print(Map<String,Integer> menu, Map<String,Integer> drink, Map<String,Integer> appetizer, Map<String,Integer> dessert)
    {
        removeKeyIfZero(menu, drink, appetizer, dessert);

        buyItemSummeryTextView.setText("");

        /**LÄGGER TILL DEM SOM INTE ÄR TOMMA*/
        if(!areValuesZero(menu))
        {
            buyItemSummeryTextView.append(printFormatted(menu));
        }

        if(!areValuesZero(drink))
        {
            buyItemSummeryTextView.append(printFormatted(drink));
        }

        if(!areValuesZero(appetizer))
        {
            buyItemSummeryTextView.append(printFormatted(appetizer));
        }

        if(!areValuesZero(dessert))
        {
            buyItemSummeryTextView.append(printFormatted(dessert));
        }
    }


    /**
     * Raderar de keys vars value är 0 från alla våra map, så de inte visas på appen om ingen av de har valts
     * @param menu
     * @param drink
     * @param appetizer
     * @param dessert
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    void removeKeyIfZero(Map<String,Integer> menu, Map<String,Integer> drink, Map<String,Integer> appetizer, Map<String,Integer> dessert)
    {
        menu.values().removeIf(f -> f == 0f);
        drink.values().removeIf(f -> f == 0f);
        appetizer.values().removeIf(f -> f == 0f);
        dessert.values().removeIf(f -> f == 0f);
    }

    /**
     * Skriver ut innehållet av en MAP i korrekt format.
     * @param hashMap
     * @return
     */
    String printFormatted(Map<String,Integer>hashMap)
    {
        String s ="";
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            s += "  " + entry.getValue().toString() + " st    " + entry.getKey() + "\n";
        }
        return s;
    }

    public List<InventoryModifier> getProccessedInventoryModifierList()
    {
        List<InventoryModifier> menuList = new ArrayList<>();
        //Behöver menuId (namn på meny), och antalet av samma meny som ska tillagas.,
        //hämtar dem från saveMenuAmount lista som innehåller ny lagd order
        for (String key : savedMenuAmount.keySet())
            menuList.add(new InventoryModifier(key, Integer.toString(savedMenuAmount.get(key))));
        //key innehåller namnet på menyn
        //savedMenuAmount.get(key) innehåller antalet av denna meny som ska tillagas

        //Behöver IngredientId, så hämtar det genom MenuHasIngredientList och vi behöver antalet av dessa ingredienser det behövs för menyn
        for(TableMenuHasIngredient ingredients : api.PARSEDApi().getMenuHasIngredientList())
        {
            for(int i=0; i<menuList.size(); i++)
            {
                if(ingredients.menuId.equals(menuList.get(i).menuId))
                {
                    menuList.get(i).ingredientIdList.add(ingredients.ingredientId);
                    menuList.get(i).quantityOfIngredient.add(ingredients.quantity);
                }
            }
        }

        //Behöver InventoryNameId, så hämtar det genom relationen mellan dessa två tabellerna från IngredientList
        for(TableIngredient ingredients : api.PARSEDApi().getIngredientList())
        {
            for(int i=0; i<menuList.size(); i++)
            {
                for(int j=0; j<menuList.get(i).ingredientIdList.size(); j++)
                {
                    if(ingredients.ingredientId.equals(menuList.get(i).ingredientIdList.get(j)))
                        menuList.get(i).inventoryNameIdList.add(ingredients.inventoryNameId);
                }
            }
        }

        //Behöver nuvarande mängden av ingredisens som finns i Inventory tabellen
        for(TableInventory inventory : api.PARSEDApi().getInventoryList())
        {
           for(int i=0; i<menuList.size(); i++)
           {
               for(int j=0; j<menuList.get(i).inventoryNameIdList.size(); j++)
               {
                    if(inventory.inventoryNameId.equals(menuList.get(i).inventoryNameIdList.get(j)))
                        menuList.get(i).currentQuantityOfIngredientInInventoryList.add(inventory.quantity);
               }
           }
        }

        return menuList;
    }
}