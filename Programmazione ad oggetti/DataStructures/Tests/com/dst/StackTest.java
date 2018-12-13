package com.dst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void stack() {
        Stack<String> stck = new Stack<String>();
        stck.push("A");
        assertEquals(1, (int)stck.size());
        stck.push("B");
        assertEquals(2, (int)stck.size());
        stck.push("C");
        assertEquals(3, (int)stck.size());
        stck.push("D");
        assertEquals(4, (int)stck.size());
        assertEquals("D", stck.pop());
        assertEquals(3, (int)stck.size());
        assertEquals("C", stck.pop());
        assertEquals(2, (int)stck.size());
        assertEquals("B", stck.pop());
        assertEquals(1, (int)stck.size());
        assertEquals("A", stck.pop());
        assertEquals(0, (int)stck.size());
        assertNull(stck.pop());
    }
}