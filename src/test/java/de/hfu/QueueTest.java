package de.hfu;

import static org.junit.Assert.assertTrue;

import org.junit.Before;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class QueueTest 
{
	Queue queue;
	
	@Before
	public void initQueue() {
		queue = new Queue(3);
	}
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testEnqueue() {
    	queue.enqueue(1);
    	queue.enqueue(2);
    	queue.enqueue(3);
    	assertEquals("should be 1",queue.dequeue(),1);
    	queue.enqueue(4);
    	queue.enqueue(5);
    	queue.enqueue(6);
    	queue.enqueue(7);
    	assertEquals("should be 2",queue.dequeue(), 2);    	
    	assertEquals("should be 3",queue.dequeue(), 3);
    	assertEquals("should be 4",queue.dequeue(), 7);
    	
    }
    
    @Test(expected=IllegalStateException.class)
    public void testDequeueException() {
    	queue.dequeue();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testQueueToSmall() {
    	queue = new Queue(0);
    }
}
