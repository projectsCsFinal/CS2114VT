package com.kristopher.restaurantmanager;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * Test case for the Menu class. This test case must provide the test methods
 * for addMenu and deleteMenu.
 *
 * @author Kristopher Ha Jung
 * @version Jun 24, 2015
 */
public class MenuTests
    extends TestCase
{
    private Menu menu;
    private Menu menu2;


    /**
     * setUp environment for the test case.
     */
    public void setUp()
    {
        menu = new Menu();
        menu2 = new Menu();
    }


    // ----------------------------------------------------------
    /**
     * Testing if the addMenu method successfully add a menu on the array.
     */
    public void testAddMenu()
    {
        menu.addMenu("kimchi", 10.50, "kimchi");
        Food food = menu.getMenu(0);
        assertEquals(food.getName(), "kimchi");
        assertEquals(food.getPrice(), 10.50, 0.0);
        assertEquals(menu.getImageString(), "kimchi");

        menu.addMenu("bulgogi", 20.00, "bulgogi");
        Food food2 = menu.getMenu(1);
        assertEquals(food2.getName(), "bulgogi");
        assertEquals(food2.getPrice(), 20.00, 0.0);
        assertEquals(menu.getImageString(), "bulgogi");

        menu.addMenu("Udon", 7.50, "udon");
        menu.addMenu("ramen", 23.45, "ramen");

        // testing the case when the array size is already reach to the initial
        // capacity
        Exception thrown = null;
        try
        {
            menu.addMenu("something", 10.00, "something");
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalStateException);
        assertEquals(thrown.getMessage(), "menu array is already full");
    }


    // ----------------------------------------------------------
    /**
     * Testing if the deleteMenu successfuly change the information of the menu
     * as desired.
     */
    public void testDeleteMenu()
    {
        Exception thrown = null;
        try
        {
            menu.deleteMenu(0);
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalStateException);
        assertEquals(thrown.getMessage(), "menu array is empty");

        menu.addMenu("kimchi", 12.75, "kimchi");
        menu.deleteMenu(0);
        Food food = menu.getMenu(0);
        assertEquals(food.getName(), "No longer served");
        assertEquals(menu.getImageString(), "error");
        assertEquals(food.getPrice(), 0.00, 0.0);
    }


    // ----------------------------------------------------------
    /**
     * Initial capacity is set to 4.
     */
    public void testGetMenu()
    {
        menu2.addMenu("kimchi", 12.75, "kimchi");
        menu2.addMenu("kimchi", 12.75, "kimchi");
        menu2.addMenu("kimchi", 12.75, "kimchi");
        menu2.addMenu("kimchi", 12.75, "kimchi");

        Exception thrown = null;
        try
        {
            menu2.getMenu(5);
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IndexOutOfBoundsException);
        assertEquals(
            thrown.getMessage(),
            "your index is outbound from the menu array size");


        Food food = menu2.getMenu(3);
        assertEquals(food.getPrice(), 12.75, 0.0);
        assertEquals(food.getName(), "kimchi");
    }

}
