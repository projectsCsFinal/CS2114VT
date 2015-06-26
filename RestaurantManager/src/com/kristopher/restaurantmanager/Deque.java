package com.kristopher.restaurantmanager;

// -------------------------------------------------------------------------
/**
 * Deque class. This is the interface of the TableDequeue class.\ *
 *
 * @author Kristopher Ha Jung
 * @version 6.20.2015
 * @param <E>
 */
public interface Deque<E>
{
    // ----------------------------------------------------------
    /**
     * Insert a new Customer at the front (the head) of the TableDequeue.
     *
     * @param value
     *            the customer to insert.
     */
    public void enqueueCustomerAtFront(Customer value);


    // ----------------------------------------------------------
    /**
     * Remove the customer at the front (the head) of the TableDequeue.
     *
     * @return The customer that was removed
     */
    public Customer dequeueCustomerAtFront();


    // ----------------------------------------------------------
    /**
     * Insert a new customer at the rear (the tail) of the TableDequeue.
     *
     * @param value
     *            the customer to insert.
     */
    public void enqueueCustomerAtRear(Customer value);


    // ----------------------------------------------------------
    /**
     * Remove the customer at the rear (the tail) of the TableDequeue.
     *
     * @return The customer that was removed
     */
    public Customer dequeueCustomerAtRear();


    // ----------------------------------------------------------
    /**
     * Get the Customer at the front (the head) of the TableDequeue. Does not
     * alter the TableDequeue.
     *
     * @return the customer at the front of the TableDequeue.
     */
    public Customer frontCustomer();


    // ----------------------------------------------------------
    /**
     * Get the Customer at the rear (the tail) of the TableDequeue. Does not
     * alter the TableDequeue.
     *
     * @return the customer at the rear of the TableDequeue.
     */
    public Customer rearCustomer();


    // ----------------------------------------------------------
    /**
     * Get the number of customers in this TableDequeue. Does not alter the
     * TableDequeue.
     *
     * @return The number of items this TableDequeue contains.
     */
    public int size();


    // ----------------------------------------------------------
    /**
     * Empty the TableDequeue.
     */
    public void clear();


    // ----------------------------------------------------------
    /**
     * Returns a string representation of this TableDequeue. A TableDequeue's
     * string representation is written as a comma-separated list of its
     * contents (in front-to-rear order) surrounded by square brackets, like
     * this:
     *
     * @return a string representation of the TableDequeue
     */
    public String toString();
}
