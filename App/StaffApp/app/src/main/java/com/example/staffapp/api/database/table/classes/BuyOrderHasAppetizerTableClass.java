package com.example.staffapp.api.database.table.classes;

public class BuyOrderHasAppetizerTableClass {
    private String appetizerid;
    private Integer buyorderhasappetizerid;
    private Integer buyorderid;
    private Integer quantity;

    public BuyOrderHasAppetizerTableClass(String appetizerid, Integer buyorderhasappetizerid, Integer buyorderid, Integer quantity)
    {
        this.appetizerid = appetizerid;
        this.buyorderhasappetizerid = buyorderhasappetizerid;
        this.buyorderid = buyorderid;
        this.quantity = quantity;
    }
}
