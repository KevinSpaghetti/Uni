package com.dst;

public class Stack<T> {

    private Node<T> head = null;

    public Stack(){
        head = null;
    }

    public Stack(T element){
        head = new Node(element);
        head.next = null;
    }

    public void push(T element){
        if(head == null){
            head = new Node<T>(element);
            return ;
        }
        Node<T> pointer = new Node<T>(element);
        pointer.next = head;
        head = pointer;
    }

    public T pop(){
        if(head == null) return null;
        T value = head.value;
        head = head.next;
        return value;
    }

    public Integer size(){
        Integer size = 0;
        if(head == null) { return size; }
        Node pointer = head;
        while(pointer != null){
            size++;
            pointer = pointer.next;
        }
        return size;
    }

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
        Stack<String> stck = new Stack<String>();
        stck.push("A");
        System.out.println(stck);
        stck.push("B");
        System.out.println(stck);
        stck.push("C");
        System.out.println(stck);
        stck.push("D");
        System.out.println(stck);
        stck.pop();
        System.out.println(stck);
        stck.pop();
        System.out.println(stck);
        stck.pop();
        System.out.println(stck);
        stck.pop();
    }
}
