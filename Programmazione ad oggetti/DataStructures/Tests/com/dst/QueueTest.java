package com.dst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void queue() {
        Queue<String> queue = new Queue<String>();
        queue.enqueue("A");
        assertEquals(1, (int)queue.size());
        queue.enqueue("B");
        assertEquals(2, (int)queue.size());
        queue.enqueue("C");
        assertEquals(3, (int)queue.size());
        queue.enqueue("D");
        assertEquals(4, (int)queue.size());
        assertEquals("A", queue.dequeue());
        assertEquals(3, (int)queue.size());
        assertEquals("B", queue.dequeue());
        assertEquals(2, (int)queue.size());
        assertEquals("C", queue.dequeue());
        assertEquals(1, (int)queue.size());
        assertEquals("D", queue.dequeue());
        assertEquals(0, (int)queue.size());
        assertNull(queue.dequeue());
    }
}