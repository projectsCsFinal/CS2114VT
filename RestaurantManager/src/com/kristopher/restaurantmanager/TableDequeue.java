package com.kristopher.restaurantmanager;

import sofia.graphics.RectangleShape;

// -------------------------------------------------------------------------
/**
 * TableDeqeue represents a table in the store. TableDequeue stores customers
 * object in to a node and node will be stored in a dequeue. TableDequeue
 * provides methods to insert or delete at the either end of the node.
 *
 * @author Kristopher Ha Jung
 * @version Jun 20, 2015
 */
public class TableDequeue
    extends RectangleShape
    implements Deque<Customer>

{
    // ~ Instance/static variables ............................................

    private TableDequeue   duplicate;
    private Node<Customer> head;
    private Node<Customer> tail;
    private int            size;
    private double         totalDue;
    private double         totalTip;
    private boolean        authorized;


    /**
     * Constructor of the TableDequeue class.
     */
    public TableDequeue()
    {
        head = new Node<Customer>(null);
        tail = new Node<Customer>(null);
        head.join(tail);
        size = 0;
        this.setImage("table");
    }


    /**
     * Insert a new Customer at the rear (the tail) of the Tabledequeue.
     *
     * @param value
     *            the Customer to insert.
     */
    public void enqueueCustomerAtRear(Customer value)
    {
        Node<Customer> oldRear = tail.previous();
        tail.previous().split();
        oldRear.join((new Node<Customer>(value)).join(tail));
        size++;
    }


    // ----------------------------------------------------------
    /**
     * Remove the Customer at the front (the head) of the deque.
     *
     * @return The Customer that was removed
     */
    public Customer dequeueCustomerAtFront()
    {
        Node<Customer> oldFrontNext = head.next().next();
        Node<Customer> oldFront = head.next();
        head.next().split();
        head.split();
        head.join(oldFrontNext);
        size--;
        return oldFront.data();
    }


    // ----------------------------------------------------------
    /**
     * Insert a new Customer at the front (the head) of the deque.
     *
     * @param value
     *            the Customer to insert.
     */
    public void enqueueCustomerAtFront(Customer value)
    {
        Node<Customer> oldFront = head.next();
        head.split();
        head.join((new Node<Customer>(value)).join(oldFront));
        size++;
    }


    // ----------------------------------------------------------
    /**
     * Remove the Customer at the rear (the tail) of the deque.
     *
     * @return The Customer that was removed
     */
    public Customer dequeueCustomerAtRear()
    {
        Node<Customer> oldRearPrevious = tail.previous().previous();
        Node<Customer> oldRear = tail.previous();
        oldRearPrevious.split();
        oldRear.split();
        oldRearPrevious.join(tail);
        size--;
        return oldRear.data();

    }


    // ----------------------------------------------------------
    /**
     * Get the Customer at the front (the head) of the deque. Does not alter the
     * deque.
     *
     * @return the Customer at the front of the deque.
     */
    public Customer frontCustomer()
    {
        return head.next().data();
    }


    // ----------------------------------------------------------
    /**
     * Get the Customer at the rear (the tail) of the deque. Does not alter the
     * deque.
     *
     * @return the Customer at the rear of the deque.
     */
    public Customer rearCustomer()
    {
        return tail.previous().data();
    }


    // ----------------------------------------------------------
    /**
     * Get the number of items in this deque. Does not alter the deque.
     *
     * @return The number of items this deque contains.
     */
    public int size()
    {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * Empty the deque.
     */
    public void clear()
    {
        while (frontCustomer() != null)
        {
            dequeueCustomerAtRear();
        }
    }


    // ----------------------------------------------------------
    /**
     * Returns a string representation of this deque. A deque's string
     * representation is written as a comma-separated list of its contents (in
     * front-to-rear order) surrounded by square brackets, like this:
     *
     * <pre>
     * [koo, david, karl, tom]
     * </pre>
     * <p>
     * An empty deque is simply <code>[]</code>.
     * </p>
     *
     * @return Customer lists on a signle table.
     */
    public String customerList()
    {
        String s = "[";
        int count = 0;
        Node<Customer> c = head.next();
        while (c != tail)
        {
            if (count < size() - 1)
            {
                s = s + c.getClass().getName() + ", ";
                c = c.next();
            }
            else
            {
                s = s + c.getClass().getName() + "]";
                c = c.next();
            }
            count++;
        }
        return s;
    }


    // -----------------------------------------------------
    // Table money process methods
    // -----------------------------------------------------

    // ----------------------------------------------------------
    /**
     * tableDue calculate the total due amount of the table. This method use
     * duplicated information of the table not to hurt any stored value beside
     * the double value totalDue
     *
     * @return total due on the table.
     */
    public double tableDue()
    {
        duplicate = this;
        while (duplicate.size() != 0)
        {
            totalDue += duplicate.dequeueCustomerAtFront().getDueAmount();
        }
        duplicate = null;
        return totalDue;
    }


    // ----------------------------------------------------------
    /**
     * calculateTotalTipOnTable method calculate the total tip amount that
     * customers gives you
     *
     * @return total tip amount on table
     */
    public double calculateTotalTipOnTable()
    {
        duplicate = this;
        while (duplicate.size() != 0)
        {
            totalTip += duplicate.dequeueCustomerAtFront().totalTipAmount();
        }
        duplicate = null;
        return totalTip;
    }


    // ----------------------------------------------------------
    //메서드 수정 요함. authorized 된 커스터머일 경우만!!! 본 데큐에서 삭제하기 (field 건드리기)
    /**
     * This method will determine if it is okey to remove table based on the
     * bollean value stored in this class, and customer class. This method count
     * the number of customers that is already authorized(means they paid). If
     * counts equal to the size of the deque, this table is authorized and ready
     * to dismiss.
     *
     * @return name of the customer who didn't fully pay yet.
     */
    public String payingProcess()
    {
        duplicate = this;
        int count = 0;
        String str = "";
        while (duplicate.size() != 0)
        {
            if (duplicate.dequeueCustomerAtFront().isPaid())
            {
                count++;
                this.dequeueCustomerAtFront();
            }
            else
            {
                str += duplicate.dequeueCustomerAtFront().getName();
            }
        }
        authorized = (count == this.size());
        duplicate = null;
        return str;
    }


    // ----------------------------------------------------------
    /**
     * return boolean if this table is ready to be dismissed
     *
     * @return boolean authorized
     */
    public boolean isPaid()
    {
        return authorized;
    }
}
