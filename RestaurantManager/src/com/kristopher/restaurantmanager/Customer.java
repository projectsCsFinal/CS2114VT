package com.kristopher.restaurantmanager;

import sofia.util.Timer;
import java.util.ArrayList;
import sofia.graphics.RectangleShape;

// -------------------------------------------------------------------------
/**
 * Customer Class. This represent a customer. Customer must store a array list
 * of food that the customer ordered. Customer might order more than one food
 * and that is why I store food on array. User can serach the information about
 * the customer searching the name of the cstomer.
 *
 * @author Kristopher Ha Jung
 * @version Jun 20, 2015
 */
public class Customer
    extends RectangleShape
{

    private Timer           timer;
    private String          name;
    private ArrayList<Food> food;
    private double          tip;
    private int             time        = 0;
    private double          actualTip   = 0;
    private double          totalTip    = 0;
    private double          totalPayDue = 0;
    private double          paidAmount  = 0;
    private String          spec;
    private boolean         authorized;


    // ---------------------------------------------------------
    /**
     * Create a new Customer object. Constructor of the Customer class. Storing
     * name of the customer to the field, and initializing array of the food.
     *
     * @param name
     *            name of the customer
     */
    public Customer(String name)
    {
        this.name = name;
        this.setImage("customer");
        food = new ArrayList<Food>();
    }


    // ----------------------------------------------------------
    /**
     * getName method. return the name of the customer that is stored on the
     * field.
     *
     * @return name of the customer
     */
    public String getName()
    {
        return this.name;
    }


    // ----------------------------------------------------------
    /**
     * setName method. setting the name of the customer on the field. This
     * method will be used to edit the name of the customer in case user want to
     * fix the name of the customer.
     *
     * @param str
     *            name of the custmer
     */
    public void setName(String str)
    {
        this.name = str;
    }


    // ----------------------------------------------------------
    /**
     * getOrderFood method return the string representation of the ordered
     * foods. It basically return the string that the name of the foods appended
     * in order of the arrayList. if this customer did not order any food(the
     * size of the food array is 0), than this method return exception
     *
     * @return food that customer ordered
     */
    public String getOrderFood()
    {
        if (food.size() == 0)
        {
            throw new IllegalStateException(
                "This customer did not order any foods yet");
        }
        else if (food.size() == 1)
        {
            String string = "[";
            string += food.get(0).getName();
            string += "]";
            return string;
        }
        else
        {
            String string = "[";
            for (int i = 0; i < (food.size() - 1); i++)
            {
                string = string + food.get(i).getName() + ", ";
            }
            string = (string + food.get(food.size() - 1).getName() + "]");
            return string;
        }
    }


    // ----------------------------------------------------------
    /**
     * cancelRecentOrder method remove a food that is most recently added onto
     * the array. amount of payment is decreased as the price of the cancelled
     * menu.
     */
    public void cancelRecentOrder()
    {
        if (food.size() > 0)
        {
            totalPayDue -= food.get(food.size() - 1).getPrice();
            food.remove(food.size() - 1);
        }
        else
        {
            throw new IllegalStateException("there is no recent order");
        }
    }


    // ----------------------------------------------------------
    /**
     * cancelEntireOrder method will be called when customer want to cancel
     * appointment and return a new array.
     *
     * @return food array
     */
    public ArrayList<Food> cancelEntireOrder()
    {
        food = new ArrayList<Food>();
        totalPayDue = 0;
        return food;
    }


    // ----------------------------------------------------------
    /**
     * setOrderedFood method. This method add a food that customer want onto the
     * array. This method also have to increment the total payment expected from
     * the customer.
     *
     * @param f
     *            food object
     */
    public void setOrderedFood(Food f)
    {
        food.add(f);
        totalPayDue += f.getPrice();
    }


    // ----------------------------------------------------------
    /**
     * setFoodSpecification. This method is for setting the specification of the
     * foods, something like "Dont put any vegetable on the beef stew!"
     *
     * @param str
     *            string of the specification.
     */
    public void setFoodSpecification(String str)
    {
        spec = str;
    }


    // ----------------------------------------------------------
    /**
     * getFoodSpecification this method return the specification of the food
     * that customer wanted
     *
     * @return specification that user inupt
     */
    public String getFoodSpecification()
    {
        return spec;
    }


    // ----------------------------------------------------------
    // Timer related methods
    // ----------------------------------------------------------
    /**
     * timeCount method. This method is a helper method for the startTimer. this
     * method increment int time by one each call.
     */
    public void timeCount()
    {
        time++;
    }


    // ----------------------------------------------------------
    /**
     * startTimer method basically create a new timer and call "timeCount"
     * method every 1000 milli seconds, which means that int time will be
     * incremented by 1 every 1 second until the timer stop.
     */
    public void startTimer()
    {
        timer = Timer.callRepeatedly(this, "timeCount", 1000);
    }


    // ----------------------------------------------------------
    /**
     * getElapsedTime method stops the timer if the timer is on by setTimer
     * method. If the timer is on, it makes timer stop and dispose of timer. I
     * make int value x to store the time and set field time value to the
     * initial value (zero), then return the time elapsed.
     *
     * @return time elapsed
     */
    public int getElapsedTime()
    {
        if (timer != null)
        {
            timer.stop();
            timer = null;
        }
        int x = time;
        time = 0;
        return x;
    }


    // -----------------------------------------------------------------
    // ----------------------------------------------------------
    /**
     * payAmount method basically return the amount of totalPayDue that customer
     * has to totalPayDue before leave the store. totalPayDue value will higher
     * when user order each food. when setOrderedFood method called, totalPayDue
     * amount will be incremented as the price of the food.
     *
     * @return amount of money that a customer have to totalPayDue before leave.
     */
    public double getDueAmount()
    {
        return totalPayDue;
    }


    // ----------------------------------------------------------
    /**
     * tipCalculate method calculate desirable amount of tip. I set the tip to
     * be 15% of the total. This is a helper method for the advanced version.
     *
     * @return amount of tip desired to have.
     */
    public double tipCalculate()
    {
        tip = totalPayDue * 0.15;
        return tip;
    }


    // -----------------------------------------------------------
    // customer is not going to be deleted from the tableDeque if the boolean
    // authorized is not true.
    // authorized related methods here
    // ----------------------------------------------------------
    /**
     * ispaid method return boolean if customer paied his amount due.
     *
     * @return true or false
     */
    public boolean isPaid()
    {
        return authorized;
    }


    // ----------------------------------------------------------
    /**
     * payingProcess method does the payingprocess that if the amount of payment
     * is bigger or equals to the amount of money that has to be paid, it
     * returns true, if not, then it returns false. negative values of payment
     * is not accepted. Return the change at the end. This method also store the
     * actual tip amount that customer gives you and this value can be used for
     * advancedTipCalculate method.
     *
     * @param d
     *            amount of money paid
     * @param t
     *            amount of tip customer gives you
     * @return the amount of change
     */
    public double payingProcess(double d, double t)
    {
        actualTip = t;
        double x = d - this.getDueAmount();
        if (d >= 0.0)
        {
            authorized = (x >= 0.0);
        }
        else
        {
            throw new IllegalStateException("negative value of payment");
        }

        if (authorized)
        {
            totalPayDue = 0;
            paidAmount = paidAmount + d;
            return x;
        }
        else
        {
            totalPayDue -= d;
            paidAmount = paidAmount + d;
            return Math.abs(x);
        }
    }


    // ----------------------------------------------------------
    /**
     * tipCalculateAdvanced method notify user whether this customer give you
     * proper amount of tip.
     *
     * @return string decision
     */
    public String tipCalculateAdvanced()
    {
        String str;
        if (actualTip - tipCalculate() > 0)
        {
            str =
                "your Customer " + this.getName() + " give" + (actualTip - tip)
                    + "more tip than expected!";
        }
        else
        {
            str =
                "your Customer " + this.getName()
                    + " give less tip than expected!";
        }

        totalTip += actualTip;
        return str;
    }


    // ----------------------------------------------------------
    /**
     * totalTipAmount method return the total tip amount that you were given.
     * This method is designed to be used when customer left the store.
     *
     * @return total tip amount that you've earned this day.
     */
    public double totalTipAmount()
    {
        return totalTip;
    }
}
