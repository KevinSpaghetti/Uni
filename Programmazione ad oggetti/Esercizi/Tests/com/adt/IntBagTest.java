package com.adt;

import com.adt.exceptions.EmptyIntBagException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntBagTest {

    @Test
    void insert() {
        IntBag bag = new IntBag();
        Integer element = 2;
        bag.insert(element);
        element = 3;

        //Check if the IntBag contains the number 2
        assert(bag.contains(2));
        //Check that the modified reference to element
        //did not corrupt the value in the bag
        assert(!(bag.contains(3)));
    }

    @Test
    void remove() {
        IntBag bag = new IntBag();
        for (int i = 1; i <= 10; i++) {
            Integer element = i;
            bag.insert(element);
            element = 0;
        }
        assert(bag.contains(5));
        assert(bag.remove(5));
        assert(!(bag.contains(5)));
    }

    @Test
    void contains() {
        IntBag bag = this.createMockBag(1, 10);

        assert(bag.contains(5));
        assert(!(bag.contains(15)));
    }

    @Test
    void sizeOf() {
        IntBag bag = this.createMockBag(1, 10);

        assert(bag.sizeOf() == 10);
    }

    @Test
    void pick() {
        final IntBag emptyBag = new IntBag();
        assertThrows(EmptyIntBagException.class,
                () -> {
                    emptyBag.pick();
                });

        IntBag bag = this.createMockBag(1, 10);

        try {
            Integer element = bag.pick();
            assert(bag.contains(element));
        }catch(EmptyIntBagException e) {
            assert(false);
        }

    }

    @Test
    void sameValue() {
        IntBag same1 = createMockBag(1, 10);
        IntBag same2 = createMockBag(1, 10);
        IntBag diff  = createMockBag(5, 15);

        assert(same1.sameValue(same2));
        assert(!(same1.sameValue(diff)));
    }

    private static IntBag createMockBag(Integer from, Integer to){
        IntBag bag = new IntBag();
        for (int i = from; i <= to; i++) {
            bag.insert(i);
        }
        return bag;
    }
}