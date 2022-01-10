package com.example.staffapp.api.database.table.classes;

public class BuyOrderHasDrinkTableClass {
    private int buyorderhasdrinkid;
    private Integer buyorderid;
    private String drinkid;
    private Integer quantity;

    public BuyOrderHasDrinkTableClass(int buyorderhasdrinkid, Integer buyorderid, String drinkid, Integer quantity)
    {
        this.buyorderhasdrinkid = buyorderhasdrinkid;
        this.buyorderid = buyorderid;
        this.drinkid = drinkid;
        this.quantity = quantity;
    }
}
