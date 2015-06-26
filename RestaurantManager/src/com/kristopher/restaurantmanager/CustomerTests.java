package com.kristopher.restaurantmanager;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * CustomerTests class is a test case for the customer class. This class should
 * have the tests stubs for all of the implemented methods in the customer
 * class, including getName, setName, getOrderedFood, setOr deredFood,
 * setFoodSpecification, getFoodSpecification, timeCount, getElapsed time,
 * payAmount, cancelRecentOrder, cancelEntireOrder, tipCalculate,
 * tipCalculateAdvanced, and totalTipAmount method.
 *
 * @author Kristopher Ha Jung
 * @version Jun 25, 2015
 */
public class CustomerTests
    extends TestCase
{

    private Customer customer;


    /**
     * setUp environment for the test case.
     */
    public void setUp()
    {
        customer = new Customer("Mike");
    }


    // ----------------------------------------------------------
    /**
     * test if the getName method successfully bring the name of the customer
     */
    public void testGetName()
    {
        assertEquals(customer.getName(), "Mike");
    }


    // ----------------------------------------------------------
    /**
     * test if setName method successfully set the name of the customer as
     * desired
     */
    public void testSetName()
    {
        customer.setName("Zach");
        assertEquals(customer.getName(), "Zach");
    }


    // ----------------------------------------------------------
    /**
     * test if getOrdered method successfully return the strings of ordered
     * foods. This method must test when the food array size equals to 0 and
     * when it is not 0
     */
    public void testGetOrderedFood()
    {
        Exception thrown = null;
        try
        {
            customer.getOrderFood();
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalStateException);
        assertEquals(
            "This customer did not order any foods yet",
            thrown.getMessage());

        Food food = new Food("kimchi", 12.75);
        customer.setOrderedFood(food);
        assertEquals(customer.getOrderFood(), "[kimchi]");

        Food food2 = new Food("bulgogi", 20.10);
        customer.setOrderedFood(food2);
        assertEquals(customer.getOrderFood(), "[kimchi, bulgogi]");

        Food food3 = new Food("bulgogi", 20.10);
        customer.setOrderedFood(food3);
        assertEquals(customer.getOrderFood(), "[kimchi, bulgogi, bulgogi]");
    }


    // ----------------------------------------------------------
    /**
     * testing if setOrderedFood method successfully seting the array. This
     * method also have to increment the pay Amount as the price of the food
     * that is added onto the array. This method also test the getAmount method
     * since setOrderdFood also access to the method.
     */
    public void testSetOrderedFood()
    {
        Food food = new Food("kimchi", 12.75);
        customer.setOrderedFood(food);
        assertEquals(customer.getOrderFood(), "[kimchi]");
        assertEquals(customer.getDueAmount(), 12.75, 0.0);

        Food food2 = new Food("kimchi boggem", 23.00);
        customer.setOrderedFood(food2);
        assertEquals(customer.getDueAmount(), 35.75, 0.0);
    }


    // ----------------------------------------------------------
    /**
     * test if cancelRecentOrder successfully remove the most recent item of the
     * array. if there's no item in the array, it has to throw exception
     */

    public void testCancelRecentOrder()
    {
        Exception thrown = null;
        try
        {
            customer.cancelRecentOrder();
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalStateException);
        assertEquals(thrown.getMessage(), "there is no recent order");

        Food food = new Food("Ice Cream", 5.50);
        customer.setOrderedFood(food);
        assertEquals(5.50, customer.getDueAmount(), 0.0);
        customer.cancelRecentOrder();
        assertEquals(0.0, customer.getDueAmount(), 0.0);

        Exception thrown2 = null;
        try
        {
            customer.getOrderFood();
        }
        catch (Exception e)
        {
            thrown2 = e;
        }
        assertTrue(thrown2 instanceof IllegalStateException);
        assertEquals(
            thrown2.getMessage(),
            "This customer did not order any foods yet");

        Food food2 = new Food("cherry", 2.20);
        customer.setOrderedFood(food);
        customer.setOrderedFood(food2);
        assertEquals(7.70, customer.getDueAmount(), 0.0);
        customer.cancelRecentOrder();
        assertEquals(5.50, customer.getDueAmount(), 0.0);
        assertEquals("[Ice Cream]", customer.getOrderFood());
    }


    // ----------------------------------------------------------
    /**
     * test if cancelEntireOrder method successfully create new array
     */
    public void testCancelEntireOrder()
    {
        Food food = new Food("ice", 2.22);
        Food food2 = new Food("yes", 2.02);
        Food food3 = new Food("yoo", 1.11);
        customer.setOrderedFood(food);
        customer.setOrderedFood(food2);
        customer.setOrderedFood(food3);
        customer.cancelEntireOrder();
        Exception thrown = null;
        try
        {
            customer.getOrderFood();
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalStateException);
        assertEquals(
            thrown.getMessage(),
            "This customer did not order any foods yet");
    }


    // ----------------------------------------------------------
    /**
     * Test if set and get method for food specification works as expected.
     */
    public void testFoodSpecification()
    {
        customer
            .setFoodSpecification("he has serious allergy against vegetables");
        String str = customer.getFoodSpecification();
        assertEquals(str, "he has serious allergy against vegetables");
    }


    // ----------------------------------------------------------
    /**
     * test tipCalculateAdvanced method if this method successfully calculate
     * the amount of tip desired (15% of the total payment in this case). This
     * test class also include the test of tipCalculate since advanced method
     * employ the method inside. advanced method would return the decision based
     * on the amount of tip that customer actually paid before leave
     */
    public void testTipCalculateAdvanced()
    {
        Food food = new Food("kimchi", 20.00);
        Food food2 = new Food("bulgogi", 10.00);
        double d = (20.00 + 10.00) * 0.15;
        customer.setOrderedFood(food);
        customer.setOrderedFood(food2);
        customer.payingProcess(0.0, 10.00);
        String str = customer.tipCalculateAdvanced();
        assertEquals(customer.tipCalculate(), d);
        assertEquals("your Customer Mike give" + (10.00 - d)
            + "more tip than expected!", str);

        customer.payingProcess(0.0, 0.0);
        String str2 = customer.tipCalculateAdvanced();
        assertEquals("your Customer Mike give less tip than expected!", str2);
        assertEquals(customer.totalTipAmount(), 10.00, 0.0);
    }


    // ----------------------------------------------------------
    /**
     * payingProcess utilze tipCalculateAdvanced method. payingProcess modify
     * the boolean value "authorized." Customer is free to leave only when
     * ahtorized value is true. If the amount of money customer gives you is
     * bigger than the amount of money that he has to pay, it makes "authorized"
     * to be true and return the string from tipCalculateAdvanced, reading the
     * amount of tip customer gives you. If the amount of money customer gives
     * you is less than the amount of money that he has to pay before leave, it
     * makes "authorized" to be false
     */
    public void testPayingProcess()
    {
        Food food = new Food("kimchi", 20.00);
        Food food2 = new Food("bulgogi", 10.00);
        customer.setOrderedFood(food);
        customer.setOrderedFood(food2);

        customer.payingProcess(30.00, 15);
        assertTrue(customer.isPaid());
        assertEquals(customer.getDueAmount(), 0.0, 0.0);

        customer.cancelEntireOrder();
        // ------------------------------------------------

        customer.setOrderedFood(food);
        customer.setOrderedFood(food2);

        Exception thrown = null;
        try
        {
            customer.payingProcess(-1, 0);
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalStateException);
        assertEquals(thrown.getMessage(), "negative value of payment");

        customer.cancelEntireOrder();
        // -------------------------------------------------

        customer.setOrderedFood(food);
        customer.setOrderedFood(food2);

        customer.payingProcess(40.00, 15);
        assertTrue(customer.isPaid());
        assertEquals(customer.getDueAmount(), 0, 0.0);

        customer.cancelEntireOrder();

        // --------------------------------------------------

        customer.setOrderedFood(food);
        customer.setOrderedFood(food2);

        customer.payingProcess(10.00, 15);
        assertFalse(customer.isPaid());
        assertEquals(customer.getDueAmount(), 20.00, 0.0);

        customer.cancelEntireOrder();
        // --------------------------------------------------

        customer.setOrderedFood(food);
        customer.setOrderedFood(food2);

        double x = customer.payingProcess(60, 15);
        assertEquals(30.00, x, 0.0);

        customer.tipCalculateAdvanced();

    }


    // -----------------------------------------------------------
    // Timer methods testing
    // ----------------------------------------------------------
    /**
     * This test method tests the methods: timeCount, startTimer, and getElapsed
     * time timeCount is a helper method to increment time one by one,
     * startTimer method should initiate the timer and call the helper method
     * every 1 second, and getElapsed time must calculate elapsed time
     */
    public void testTimer()
    {
        // customer.startTimer();

    }

}
