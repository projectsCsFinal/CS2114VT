package com.kristopher.restaurantmanager;

import sofia.graphics.RectangleShape;

// -------------------------------------------------------------------------
/**
 * Food class. Food class is representing a food object. Food class must provide
 * a constructor that can be used for Menu class and Customer class. Food item
 * will be added onto the list collection to consist as a menu.
 *
 * @author Kristopher Ha Jung
 * @version Jun 20, 2015
 */
public class Food extends RectangleShape
{
    // name of the food.
    private String name;
    // price of the food.
    private double price;


    // ----------------------------------------------------------
    /**
     * Create a new Food object. Constructor of the food class. Food class must
     * take name and price of the food and store its value at the field of the
     * class.
     *
     * @param name
     *            of the food
     * @param price
     *            of the food
     */
    public Food(String name, double price)
    {
        this.name = name;
        this.price = price;
    }




    // ----------------------------------------------------------
    /**
     * getName This method must return the name of the food.
     *
     * @return name of the food
     */
    public String getName()
    {
        return this.name;
    }


    // ----------------------------------------------------------
    /**
     * setName This must must set the name of the food.
     *
     * @param str
     *            name of the food
     */
    public void setName(String str)
    {
        this.name = str;
    }


    // ----------------------------------------------------------
    /**
     * getPrice this method must return price of the food
     *
     * @return price of the food
     */
    public double getPrice()
    {
        return this.price;
    }


    // ----------------------------------------------------------
    /**
     * setPrice this method must set price of the food.
     *
     * @param d
     *            price of the food.
     */
    public void setPrice(double d)
    {
        this.price = d;
    }
}
