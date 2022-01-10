package com.example.staffapp;

import java.util.ArrayList;

/**STRUCT USE TO GATHER INFO TO SUBTRACT QUANTITY OF INGREDIENT FROM INVENTORY*/
public class InventoryModifier
{
    public String menuId;
    public String quantityOfSameMenu;
    public ArrayList<String> ingredientIdList;
    public ArrayList<String> inventoryNameIdList;
    public ArrayList<String> quantityOfIngredient;
    public ArrayList<String> currentQuantityOfIngredientInInventoryList;

    public InventoryModifier(String menuId, String quantityOfSameMenu)
    {
        this.menuId = menuId;
        this.quantityOfSameMenu = quantityOfSameMenu;
        currentQuantityOfIngredientInInventoryList = new ArrayList<>();
        quantityOfIngredient = new ArrayList<>();
        inventoryNameIdList = new ArrayList<>();
        ingredientIdList = new ArrayList<>();
    }
}
