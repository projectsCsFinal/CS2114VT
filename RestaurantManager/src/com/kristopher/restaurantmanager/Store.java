package com.kristopher.restaurantmanager;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Kristopher Ha Jung
 * @version Jun 20, 2015
 */
public class Store
    extends TableDequeue
{
    private ArrayList<TableDequeue> list;
    private int                     INITIAL_CAPACITY = 4;


    // ----------------------------------------------------------
    /**
     * Create a new Store object.
     */
    public Store()
    {
        list = new ArrayList<TableDequeue>(INITIAL_CAPACITY);
    }


    // ----------------------------------------------------------
    //수정 필요함, 이 메서드가 table 안에 들어있는 field 정보를 바꾸면 안됨.
    //customerDeque 안에 customerList 메서드 이용하기..
    /**
     * matchingCustomer based on name of the customer
     *
     * @param str
     * @return matched customers informations based on their name
     */
    public Customer matchingCustomer(String str)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).dequeueCustomerAtFront().getName() == str)
            {
                return list.get(i).dequeueCustomerAtFront();
            }
        }
        return null;
    }


    // ----------------------------------------------------------
    /**
     * add a new table on the store.
     *
     * @param index
     *            of the table number
     */
    public void addTable(int index)
    {
        list.set(index, new TableDequeue());
    }


    // ----------------------------------------------------------
    /**
     * remove a table on the store.
     *
     * @param index
     *            of the table number
     */
    public void removeTable(int index)
    {
        list.set(index, null);
    }


    // ----------------------------------------------------------
    /**
     * get accumulative customer information.
     *
     * @param index
     *            table number
     * @return string
     */
    public String information(int index)
    {
        String str = "";
        TableDequeue table = list.get(index);
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        for (int i = 0; i < table.size(); i++)
        {
            customerList.add(table.dequeueCustomerAtFront());
        }

        for (int i = 0; i < customerList.size(); i++)
        {
            str =
                str + "[" + customerList.get(i).getName() + ": "
                    + customerList.get(i).getOrderFood() + "]";
        }

        return str;
    }
}
