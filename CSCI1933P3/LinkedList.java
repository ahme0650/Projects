package CSCI1933P3;
//ahme0650, oty00003

public class LinkedList <T extends Comparable<T>> implements List<T>{

    private Node <T> start;
    private int size;
    private boolean isSorted;

    // Constructor !!
    public LinkedList(){
        this.start =  null;
        this.isSorted = true;
    }

    @Override
    //Adds element to the end of the list
    public boolean add(T element) {
        if (element == null){
            return false;
        }
        Node <T> n = new Node(element);
        if (start == null){
            start = n;
            size++;
            return true;
        }else if (start != null){
            Node <T> temp = start;
           while (temp.getNext() != null){
               temp = temp.getNext();
           }if (temp.getData().compareTo(n.getData()) > 0){
               isSorted = false;
            }
           temp.setNext(n);
           size++;
           return true;
        }  return false;
    }

    @Override
    //Adds element to a specific index in the list
    public boolean add(int index, T element) {
        if (element == null || (index < 0 || index >= size)){
            return false;
        }
        Node <T>  n = new Node(element);
        if (index == 0 && start!= null){
            n.setNext(start);
            start = n;
            if (start.getData().compareTo(start.getNext().getData()) > 0){
                isSorted = false;
            }
            size++;
            return true;
        }
        if (start == null){
            start = n;
            size++;
            return true;
            }
        else if(start != null){
            Node <T> temp = start;
            int count = 0;
            while (temp.getNext() != null && count < index -1){
                temp = temp.getNext();
                count++;
            }if (temp.getNext() != null){
                Node <T> temp2 = temp.getNext();
                if (temp.getData().compareTo(n.getData()) > 0){
                    isSorted = false;
                }
                temp.setNext(n);
                if (n.getData().compareTo(temp2.getData()) > 0){
                    isSorted = false;
                }
                n.setNext(temp2);
                size++;
                return true;
            }if (temp.getData().compareTo(n.getData()) > 0){
                isSorted = false;
            }
            temp.setNext(n);
            size++;
            return true;

        }return false;
    }

    @Override
    //Clears the list =(
    public void clear() {
        this.start = null;
        this.isSorted = true;
        this.size = 0;
    }

    @Override
    //Returns the element at the given index
    public T get(int index) {
        if (index >= size || index < 0){
            return null;
        }
        Node <T> ptr = start;
        int count = 0;
        while (ptr.getNext() != null && count != index){
            ptr = ptr.getNext();
            count ++;
        }
        return  ptr.getData();
    }

    @Override
    //Returns the index of the given element
    public int indexOf(T element) {
            Node <T> temp = start;
            int n = 0;
            while (temp != null){
                if (temp.getData().compareTo(element) == 0){
                    return n;
                }
                n++;
                temp = temp.getNext();
            }return -1;
    }

    @Override
    //Checks to see if the list is empty
    public boolean isEmpty() {
        return this.start==null;
    }

    @Override
    //Returns the number of elements in the list
    public int size() {
        return size;
    }

    @Override
    //Sort list using selection sort
    public void sort() {
        if (isSorted){
            return;
        }
        LinkedList <T> list = new LinkedList<>();
        Node <T> ptr = start;
        while (ptr.getNext() != null ) {
            Node <T> temp = ptr.getNext();
            while (temp != null){
                if (ptr.getData().compareTo(temp.getData()) > 0){
                   T val = ptr.getData();
                   ptr.setData(temp.getData());
                   temp.setData(val);
                }
                temp = temp.getNext();
            }
            ptr = ptr.getNext();
        }isSorted = true;
    }

    @Override
    //Pops an element at a given index
    public T remove(int index) {
        Node <T> ptr = start;
        int count = 0;
        T tempVal;
        if (index >= size || index < 0 || isEmpty()){
            return null;
        }else if(index==0 && size != 0){
            if (size == 1){
                tempVal = start.getData();
                clear();
                return tempVal;
            }
            tempVal = ptr.getData();
            start = start.getNext();
            Node <T> temp = start;
            int n = 0;
            size--;
            while (temp.getNext() != null){
                if (temp.getData().compareTo(temp.getNext().getData()) <= 0){
                    n++;
                }
                temp = temp.getNext();
            }if (n == size-1){
                isSorted = true;
            }
            return tempVal;
        }
        while (ptr.getNext() != null && count != index- 1){
            ptr = ptr.getNext();
            count ++;
        }tempVal = ptr.getNext().getData();
        ptr.setNext(ptr.getNext().getNext());
        size--;
        Node <T> temp = start;
        int n = 0;
        while (temp.getNext() != null){
            if (temp.getData().compareTo(temp.getNext().getData()) < 0 || temp.getData().compareTo(temp.getNext().getData()) == 0){
                n++;
            }
            temp = temp.getNext();
        }if (n == size-1){
            isSorted = true;
        }
        return  tempVal;

    }

    @Override
    //Reverses a list in place
    public void reverse(){
        if (isEmpty()){
            return;
        }
        if (isSorted){
            isSorted = false;
        }
        Node <T> last = null;
        Node <T> temp = start;
        while (temp != null){
            Node <T> next = temp.getNext();
            temp.setNext(last);
            last = temp;
            temp = next;
        }start = last;
        Node <T> ptr = start;
        int n = 0;
        while (ptr.getNext() != null){
            if (ptr.getData().compareTo(ptr.getNext().getData()) <= 0){
                n++;
            }
            ptr = ptr.getNext();
        }if (n == size-1){
            isSorted = true;
        }
    }

    @Override
    //Removes duplicate elements + maintains stability
    public void removeDuplicates() {
        LinkedList <T> list = new LinkedList<>();
        Node <T> ptr = start;
        while (ptr != null) {
            Node <T> temp = ptr;
            while (temp.getNext() != null){
                if (ptr.getData().compareTo(temp.getNext().getData()) == 0){
                   temp.setNext(temp.getNext().getNext());
                   size--;
                }
                else {
                    temp = temp.getNext();
                }
            }
            ptr = ptr.getNext();
        }
        Node <T> ptr1 = start;
        int k = 0;
        while (ptr1.getNext() != null){
            if (ptr1.getData().compareTo(ptr1.getNext().getData()) < 0 || ptr1.getData().compareTo(ptr1.getNext().getData()) == 0){
                k++;
            }
            ptr1 = ptr1.getNext();
        }if (k == size-1){
            isSorted = true;
        }
    }

    //Helper function to determine whether an element exists in an array
    public boolean isIn(T e, LinkedList <T> list){
        Node <T> temp = list.start;
        while (temp != null){
            if (temp.getData().compareTo(e) == 0){
                return true;
            }
            temp = temp.getNext();
        }return false;
    }
    @Override
    //Sets the list to be a list of the common elements between the list and another in sorted order
    public void intersect(List<T> otherList) {
        if (otherList == null){
            return;
        }
        LinkedList<T> other = (LinkedList<T>) otherList;
        LinkedList<T> inter = new LinkedList<>();
        sort();
        for (Node <T> first = start; first != null; first = first.getNext()){
            for (Node <T> second = other.start; second != null; second = second.getNext()){
                if (first.getData().compareTo(second.getData())== 0 && !isIn(first.getData(), inter)){
                    inter.add(first.getData());
                }
            }
        }start = inter.start;
        size = inter.size;
        isSorted = true;
    }

    @Override
    //Merges two lists
    public void merge(List<T> list) {
        if (list == null){
            return;
        }
        LinkedList<T> other = (LinkedList<T>) list;
        LinkedList<T> merge = new LinkedList<>();
        sort();
        other.sort();
        Node<T> first = start;
        Node <T> second = ((LinkedList<T>) list).start;
        while (first != null && second != null){
            if (first.getData().compareTo(second.getData()) <= 0){
                merge.add(first.getData());
                first = first.getNext();
            }
            else if (second.getData().compareTo(first.getData()) <= 0){
                merge.add(second.getData());
                second = second.getNext();
            }
        }if (first == null && second != null){
            while (second != null){
                merge.add(second.getData());
                second = second.getNext();
            }
        }
        else if (second == null && first != null){
            while (first != null){
                merge.add(first.getData());
                first = first.getNext();
            }
        }start = merge.start;
        size = merge.size;
        isSorted = true;
    }

    @Override
    //Returns the minimum value
    public T getMin() {
        if (start == null){
            return null;
        }
        Node <T> ptr = start;
        if (isSorted){
            return start.getData();
        }
        Node <T> minNode = start;
        while (ptr !=null){
            if (ptr.getData().compareTo(minNode.getData()) < 0){
                minNode = ptr;
            }
            ptr = ptr.getNext();
        }
        return minNode.getData();
    }

    @Override
    //Returns the maximum value
    public T getMax() {
        if (start == null){
            return null;
        }
        Node <T> ptr = start;
        if (isSorted){
            while (ptr.getNext() != null){
                ptr = ptr.getNext();
            }return ptr.getData();
        }
         Node <T> maxNode = start;
        while (ptr !=null){
            if (ptr.getData().compareTo(maxNode.getData()) > 0){
                maxNode = ptr;
            }
            ptr = ptr.getNext();
        }
        return maxNode.getData();
    }

    public String toString(){
        return "{" +  start + "}";
    }

    @Override
    //Simply returns the attribute isSorted
    public boolean isSorted() {
       return isSorted;
    }

    public boolean rfirst(T e){
        if (isEmpty()){
            return false;
        }
        if (start.getData().compareTo(e) == 0){
            start = start.getNext();
            return true;
        }
        Node <T> temp = start;
        while (temp != null){
            if (temp.getNext().getData().compareTo(e) == 0){
                temp.setNext(temp.getNext().getNext());
                return true;
            }
            temp = temp.getNext();
        }return false;
    }

    public int bt(T first, T second) {
        Node<T> temp = start;
        int n = 0;
        while (temp != null) {
            if (temp.getData().compareTo(first) > 0 && temp.getData().compareTo(second) < 0){
                n++;
            }
            temp = temp.getNext();
        }return n;
    }

    public void padList(){
        if (start == null || start.getNext() == null){
            return;
        }
        Node <T> temp = start;
        while (temp.getNext() != null){
            Node <T> space = new Node<>(null);
            Node <T> end = temp.getNext();
            temp.setNext(space);
            space.setNext(end);
            temp = temp.getNext().getNext();
        }

    }

    public T nthElement(int n){
        int count = 0;
        Node <T> temp = start;
        while (temp != null && count != n-1){
            temp = temp.getNext();
            count++;
        }return temp.getData();
    }


    public void addElement(int n, Node <T> node){
        if (n == 1){

        }
        Node <T> temp = start;
        int count = 1;
        while (temp != null){
            if (count == n-1){
                Node <T> end = temp.getNext();
                temp.setNext(node);
                node.setNext(end);
            }temp = temp.getNext();
            count++;
        }
    }

    public void deleteEveryThird(){
        if (start == null){
            return;
        }
        int count = 1;
        Node <T> temp = start;
        while (temp != null){
            if (count % 2 == 0){
                temp.setNext(temp.getNext().getNext());
            }count++;
            temp = temp.getNext();
        }
    }

    public static void main(String[] args) {
        LinkedList <Integer> l = new LinkedList<>();
        for(int i = 1; i < 5; i++){
            l.add(i);
        }
        System.out.println(l);
        l.padList();
        System.out.println(l);

    }
}
