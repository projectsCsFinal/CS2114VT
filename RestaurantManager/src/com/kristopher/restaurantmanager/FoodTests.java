package com.kristopher.restaurantmanager;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * This is the test case of the Food class. This class must provide test cases
 * of the methods declared in the Food. getName, setName, getPrice, and setPrice
 * methods will be tested in this class.
 *
 * @author Kristopher Ha Jung
 * @version Jun 24, 2015
 */
public class FoodTests
    extends TestCase
{
    // storing food onto the field.
    private Food food;


    /**
     * set up for the testing environment.
     */
    public void setUp()
    {
        food = new Food("Kimchi", 10.50);
    }


    // ----------------------------------------------------------
    /**
     * Testing if the getName method successfully return the name of the food
     */
    public void testGetName()
    {
        String str = food.getName();
        assertEquals(str, "Kimchi");
    }


    // ----------------------------------------------------------
    /**
     * Testing if the setName method successfully set the name of the food as
     * the user input
     */
    public void testSetName()
    {
        food.setName("Bulgogi");
        assertEquals(food.getName(), "Bulgogi");
    }


    // ----------------------------------------------------------
    /**
     * Testing if the getPrice method successfully return the price of the food
     */
    public void testGetPrice()
    {
        double d = food.getPrice();
        assertEquals(10.50, d, 0.0);
    }


    // ----------------------------------------------------------
    /**
     * Testing if the setPrice method successfully set the price of the food.
     */
    public void testSetPrice()
    {
        food.setPrice(20.00);
        assertEquals(food.getPrice(), 20.00, 0.0);
    }
}
