package com.dst;

public class Queue<T> {

    Node<T> head = null;

    public Queue(){
        head = null;
    }

    public Queue(T element){
        head = new Node<T>(element);
    }

    public void enqueue(T element){
        if(head == null){
            head = new Node<T>(element);
            return ;
        }
        Node<T> pointer = new Node<T>(element);
        pointer.next = head;
        head = pointer;
    }

    public T dequeue(){
        if(head == null){
            return null;
        }
        if(head.next == null){
            T value = head.value;
            head = null;
            return value;
        }
        Node<T> pointer = head;
        //head.next != null && head.next.next != null does not cause NullPointerException
        //with 2 nodes for the short circuiting of && operator
        //By using the & operator (that does not short circuit)
        //the following code would generate a NullPointerException
        while(pointer != null && pointer.next.next != null){
            pointer = pointer.next;
        }
        T value = pointer.next.value;
        pointer.next = null;
        return value;
    }

    public Integer size(){
        Integer size = 0;
        if(head == null){ return 0; }
        Node<T> pointer = head;
        while (pointer != null){
            size++;
            pointer = pointer.next;
        }
        return size;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        Node<T> pointer = head;
        while (pointer != null){
            result.append(pointer.value + " ");
            pointer = pointer.next;
        }
        return result.toString();
    }

    public static void main(String[] args){
        Queue<String> queue = new Queue<String>();
        queue.enqueue("A");
        System.out.println(queue);
        queue.enqueue("B");
        System.out.println(queue);
        queue.enqueue("C");
        System.out.println(queue);
        queue.enqueue("D");
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
    }

}
