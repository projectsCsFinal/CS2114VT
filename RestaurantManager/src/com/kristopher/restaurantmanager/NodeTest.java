package com.kristopher.restaurantmanager;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the {@link Node} class.
 *
 * @author Kristopher Ha Jung(khjung94)
 * @version 2015.06.17
 */
public class NodeTest
    extends TestCase
{
// ~ Fields ................................................................

    private Node<String> node1;
    private Node<String> node2;
    private Node<String> node3;


// ~ Public methods ........................................................

// ----------------------------------------------------------
    /**
     * Create some new nodes for each test method.
     */
    public void setUp()
    {
        node1 = new Node<String>("node1");
        node2 = new Node<String>("node2");
        node3 = new Node<String>("node3");
    }


// ----------------------------------------------------------
    /**
     * tests the join() method
     */
    public void testJoin()
    {

        Exception thrown = null;
        try
        {
            node1.join(node2);
            node1.join(node2);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertTrue(thrown instanceof IllegalStateException);
        assertEquals("illegal", thrown.getMessage());
        assertEquals(node1.next(), node2);
        assertEquals(node2.previous(), node1);

        thrown = null;
        try
        {
            node1.join(node3);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertTrue(thrown instanceof IllegalStateException);
        assertEquals("illegal", thrown.getMessage());

        thrown = null;
        try
        {
            node3.join(node2);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertTrue(thrown instanceof IllegalStateException);
        assertEquals("illegal", thrown.getMessage());

    }


// ----------------------------------------------------------
    /**
     * tests the split method
     */
    public void testSplit()
    {
        node3.join(node1.join(node2));

        node1.setData("something");
        assertEquals("something", node1.data());

        node1.split();
        assertNull(node1.next());
        assertNull(node2.previous());

        assertNull(node2.next());
        assertNull(node2.split());

    }


    /**
     * tests the split method2
     */
    public void testSplit2()
    {
        node1.join(node2.join(node3));

        node1.setData("some");
        assertEquals("some", node1.data());

        node1.split();
        assertNull(node1.next());
        assertNull(node2.previous());

        assertEquals(node2.next(), node3);
        assertEquals(node3.previous(), node2);
        assertNull(node3.next());

        assertNull(node3.split());
    }

}