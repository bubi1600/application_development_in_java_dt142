package com.example.kitchenapp.api.database.table.classes;

public class BuyOrderTableClass {
    private Integer billid;
    private String statusdessert;
    private String statusmenu;
    private String statusappetizer;
    private Integer diningtableid;
    private String notes;
    private int buyorderid;

    //Parameterna måste vara små bokstäver och även instansvariablerna för att man ska kunna hämta, måste match EXAKT som det är i tabellen
    public BuyOrderTableClass(int buyorderid, Integer diningTableId , Integer billid, String notes, String statusmenu, String statusdessert, String statusappetizer) {
        this.buyorderid = buyorderid;
        this.diningtableid = diningTableId; //Det som går in här kommer bli värdet för priset i nya recorden i tabellen "menu"
        this.billid = billid; //Det som går in här kommer bli värdet för ID:n i nya recorden i tabellen "menu"
        this.notes = notes; //Det som går in här kommer bli värdet för tiden i nya recorden i tabellen "menu"
        this.statusmenu = statusmenu; //Det som går in här kommer bli värdet för statusen i nya recorden i tabellen "menu"
        this.statusdessert = statusdessert; //Det som går in här kommer bli värdet för statusen i nya recorden i tabellen "menu"
        this.statusappetizer = statusappetizer; //Det som går in här kommer bli värdet för statusen i nya recorden i tabellen "menu"
    }
}