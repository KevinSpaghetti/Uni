package com.dst;

import com.dst.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    @Test
    public void list(){

        List<Integer> list = new List<Integer>();
        list.append(0);
        list.append(1);
        list.append(2);

        assertEquals(3, (int) list.size());
        assertEquals(3, (int) list.size());

        assertEquals(0, (int) list.elementAtIndex(0));
        assertEquals(1, (int) list.elementAtIndex(1));
        assertEquals(2, (int) list.elementAtIndex(2));
    }

}