package com.kristopher.restaurantmanager;

import sofia.graphics.RectangleShape;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * Menu, Menu class store the object Food. Menu class using arrayList to store
 * foods. Developer always can modify the list of the foods and the initial
 * capacity of the array.
 *
 * @author Kristopher Ha Jung
 * @version Jun 21, 2015
 */
public class Menu
    extends RectangleShape
{
    private ArrayList<Food>  menu;
    private Food             food;
    private final static int INITIAL_CAPACITY = 4;
    private int              size;
    private String           foodImage;


    // ----------------------------------------------------------
    /**
     * Create a new Menu object. Constructor of the menu class. Constructor
     * initiate the menu array that has initial capacity of 10.
     */
    public Menu()
    {
        menu = new ArrayList<Food>(INITIAL_CAPACITY);
        size = 0;
    }


    // ----------------------------------------------------------
    /**
     * this method should set the profile of the food that will be added onto
     * the menu array. In addition to that, this method provide a way to set the
     * image of the food through the parameter. This method store the name of
     * the image to the field for the purpose of testing.
     *
     * @param name
     *            of the food
     * @param price
     *            of the food
     * @param image
     *            of the food
     */
    public void addMenu(String name, double price, String image)
    {
        if (size < INITIAL_CAPACITY)
        {
            food = new Food(name, price);
            food.setImage(image);
            menu.add(food);
            foodImage = image;
            size++;
        }
        else
        {
            throw new IllegalStateException("menu array is already full");
        }
    }


    // ----------------------------------------------------------
    /**
     * getImageString is a helper method for the test case. It brings the name
     * of the image.
     *
     * @return name of the image file
     */
    public String getImageString()
    {
        return foodImage;
    }


    // ----------------------------------------------------------
    /**
     * Instead of deleting a food from the menu list, this method set the info-
     * rmation of the food at specific index. Price of the food will be set to
     * $0, name of the food will be set as "No longer served", and the image of
     * the food will be set as "error" I chose this way because I want the user
     * have no choice to arbitrary change the menu.(Since I don't know how to
     * save the information that user input permanantly even if the app is
     * closed) I would add pre-determined array of menu onto the screen.
     *
     * @param index
     *            of the menu.
     */
    public void deleteMenu(int index)
    {
        if (size >= 0)
        {
            menu.get(index).setPrice(0.00);
            menu.get(index).setName("No longer served");
            menu.get(index).setImage("error");
            foodImage = "error";
        }
        else
        {
            throw new IllegalStateException("menu array is empty");
        }
    }


    // ----------------------------------------------------------
    /**
     * getMenu method will return the menu of specific index.
     * Index of the menu begin from zero, which is tricky and be careful when
     * Manufacturer(me) implement this method on the screen class.
     * @param index
     *            of the menu.
     * @return the menu
     */
    public Food getMenu(int index)
    {
        if (index < INITIAL_CAPACITY)
        {
            return menu.get(index);
        }
        else
        {
            throw new IndexOutOfBoundsException(
                "your index is outbound from the menu array size");
        }
    }
}
