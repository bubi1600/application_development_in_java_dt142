package com.example.staffapp.api.database.table.classes;

public class BuyOrderHasDessertTableClass {
    private Integer buyorderhasdessertid;
    private Integer buyorderid;
    private String dessertid;
    private Integer quantity;

    public BuyOrderHasDessertTableClass(Integer buyorderhasdessertid, Integer buyorderid, String dessertid, Integer quantity)
    {
        this.buyorderhasdessertid = buyorderhasdessertid;
        this.buyorderid = buyorderid;
        this.dessertid = dessertid;
        this.quantity = quantity;
    }
}
