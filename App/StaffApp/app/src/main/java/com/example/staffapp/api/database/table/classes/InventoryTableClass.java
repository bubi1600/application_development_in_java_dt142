package com.example.staffapp.api.database.table.classes;

public class InventoryTableClass {
    private String inventorynameid;
    private Integer quantity;

    public InventoryTableClass(String inventorynameid, Integer quantity)
    {
        this.inventorynameid = inventorynameid;
        this.quantity = quantity;
    }
}
