package com.dst;

public class List<T> {

    private Node<T> head;

    public List(){
        head = null;
    }

    public List(T element){
        head = new Node(element);
    }

    public Integer size(){
        Integer size = 0;
        if(head == null){
            return size;
        }
        Node pointer = head;
        while(pointer != null){
            size++;
            pointer = pointer.next;
        }
        return size;
    }

    public void append(T element){
        if(head == null){
            head = new Node(element);
            return ;
        }
        Node pointer = head;
        while(pointer.next != null) pointer = pointer.next;
        pointer.next = new Node(element);
    }

    public T elementAtIndex(Integer index){
        Node<T> pointer = head;
        Integer listIndex = 0;
        while(pointer != null){
            if(listIndex == index){
                return pointer.value;
            }
            listIndex++;
            pointer = pointer.next;
        }
        return null;
    }

    public void changeElementAtIndex(T element, Integer index){
        Node pointer = head;
        Integer indexPointer = 0;
        while(pointer != null){
            if(index == indexPointer){
                pointer.value = element;
            }
            indexPointer++;
            pointer = pointer.next;
        }

    }

    public void addElementAtIndex(T element, Integer index){
        Node pointer = head;
        Integer indexPointer = 0;
        while(indexPointer < index){
            pointer = pointer.next;
            indexPointer++;
        }
        Node previous = pointer;
        Node next = pointer.next;
        Node middle = new Node(element);

        previous.next = middle;
        middle.next = next;
    }

    @Override
    public String toString(){
        Node pointer = head;
        StringBuilder result = new StringBuilder();
        while (pointer != null){
            result.append(pointer.value + " ");
            pointer = pointer.next;
        }
        return result.toString();
    }

    public static void main(String[] args){
        List<Integer> list = new List<Integer>();
        list.append(1);
        System.out.println(list);
        list.append(2);
        System.out.println(list);
        list.append(3);
        System.out.println(list);
        list.append(4);
        System.out.println(list);

        list.changeElementAtIndex(7, 0);
        System.out.println(list);

        list.addElementAtIndex(15, 0);
        list.addElementAtIndex(16, 1);
        list.addElementAtIndex(17, 2);
    }
}
