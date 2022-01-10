package com.example.kitchenapp.api.database.table.classes;

public class BuyOrderHasMenuTableClass {
    private int buyorderhasmenuid;
    private Integer buyorderid;
    private String menuid;
    private Integer quantity;

    public BuyOrderHasMenuTableClass(int buyorderhasmenuid, Integer buyorderid, String menuid, Integer quantity)
    {
        this.buyorderhasmenuid = buyorderhasmenuid;
        this.buyorderid = buyorderid;
        this.menuid = menuid;
        this.quantity = quantity;
    }
}
