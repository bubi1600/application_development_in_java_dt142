package com.example.staffapp.api.database.table.classes;

public class BillTableClass {
    private String paystatus;
    private double totalcost;
    private int billid;

    public BillTableClass(int billid, String paystatus, double totalcost) {
        this.billid = billid;
        this.paystatus = paystatus;
        this.totalcost = totalcost;
    }
}
