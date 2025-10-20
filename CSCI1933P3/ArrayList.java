package CSCI1933P3;
//ahme0650, oty00003
public class ArrayList <T extends Comparable<T>> implements List<T>{
    private int size;
    private T[] data;
    private boolean isSorted;

    // Constructor !!
    public ArrayList(){
        this.size = 0;
        this.data = (T[]) new Comparable[2];
        this.isSorted = true;
    }

    //Helper function to help resize
    public void resize(){
        T[] newArr = (T[]) new Comparable[data.length*2];
        for (int i =0; i< data.length; i++){
            newArr[i] = data[i];
        }
        this.data = newArr;
    }


    @Override
    //Adds element to the end of the list
    public boolean add(T element) {
        if (element == null){
            return false;
        }
        if (size == data.length){
            resize();
        }
      for(int i = 0; i < data.length; i++){
          if (data[i] == null){
              data[i] = element;
              size++;
              if (size > 1 && data[i-1].compareTo(data[i]) > 0){
                  isSorted = false;
              }
              return true;
          }
      }return false;
    }

    @Override
    //Adds element to a specific index in the list
    public boolean add(int index, T element) {
        if (element == null || (index < 0 || index >= size)){
            return false;
        }
        if (size == data.length){
            resize();
        }
        for (int i = size-1; i >= index; i--){
            data[i+1] = data[i];
        }
        data[index] = element;
        if (index-1 >= 0 ) {
            if (data[index].compareTo(data[index + 1]) > 0 || data[index - 1].compareTo(data[index]) > 0) {
                isSorted = false;
            }
        }
        else {
            if (data[index].compareTo(data[index + 1]) > 0) {
                isSorted = false;
            }
        }
        size++;
        return true;
    }

    @Override
    //Clears the list =(
    public void clear() {
        this.size = 0;
        this.data = (T[]) new Comparable[2];
        this.isSorted = true;
    }

    @Override
    //Returns the element at the given index
    public T get(int index) {
        if (index >= size || index < 0){
            return null;
        }
        return data[index];
    }

    @Override
    //Returns the index of the given element
    public int indexOf(T element) {
        if (element == null){
            return -1;
        }
        for (int i = 0; i < data.length; i++){
            if (data[i] == element){
                return i;
            }
        }return -1;
    }

    @Override
    //Checks to see if the list is empty
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    @Override
    //Returns the number of elements in the list
    public int size() {
       return size;
    }

    @Override
    //Sorts list in ascending order
    public void sort() {
        int i, j;
        T temp;
        int counter = 0;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                counter++;
                if (data[i].compareTo(data[j]) < 0) {
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
            isSorted = true;
        }

    }

    @Override
    //Pops an element at a given index
    public T remove(int index) {
        if (index >= size || index < 0 || isEmpty()){
            return null;
        }
        T val = data[index];
        data[index] = null;
        for (int i = 0; i < size; i ++){
            if (data[i] == null && i +1 < size){
                data[i] = data[i +1];
                data[i+1] = null;
            }
        }size--;
        int n = 0;
        for (int i = 0; i < size; i ++){
            if (i+1 < size && !(data[i].compareTo(data[i+1]) > 0)){
                n++;
            }
        }if (n == size-1){
            isSorted = true;
        }
        return val;
    }

    @Override
    //Reverses a list in place
    public void reverse() {
        if (isEmpty()){
            return;
        }
        if (isSorted){
            isSorted = false;
        }
        int front = 0;
        int back = size - 1;
            while (front < back) {
                T val = data[front];
                data[front] = data[back];
                data[back] = val;
                front++;
                back--;
            }
        int n = 0;
        for (int i = 0; i < size; i ++){
            if (i+1 < size && !(data[i].compareTo(data[i+1]) > 0)){
                n++;
            }
        }if (n == size-1){
            isSorted = true;
        }
    }
    //Helper function to determine whether an element exists in an array
    public boolean isIn(T element, T[] a){
        for (T e : a){
            if (e != null && e.compareTo(element) == 0){
                return true;
            }
        }return false;
    }

    @Override
    //Removes duplicate elements + maintains stability
    public void removeDuplicates() {
        T[] dupe = (T[]) new Comparable[size];
        int x = 0;
        for (T e: data){
            if (e != null && !isIn(e, dupe)){
                dupe[x] = e;
                x++;
            }

        }
        data = dupe;
        int n = 0;
        for (int i = 0; i < data.length; i++){
            if (data[i] != null){
                n++;
            }
        } size = n;
        int p = 0;
        for (int i = 0; i < size; i ++){
            if (i+1 < size && !(data[i].compareTo(data[i+1]) > 0)){
                p++;
            }
        }if ( p == size-1){
            isSorted = true;
        }
    }

    @Override
    //Sets the list to be a list of the common element between the list and another in sorted order
    public void intersect(List<T> otherList) {
        ArrayList<T> other = (ArrayList<T>)  otherList;
        sort();
        T[] dupe = (T[]) new Comparable[size+other.size];
        int x = 0;
        for (T e : data){
            if (e != null && isIn(e, other.data)){
                dupe[x] = e;
                x++;
            }
        }for (T e : other.data){
            if (e != null && isIn(e, data)){
                dupe[x] = e;
                x++;
            }
        }
         data = dupe;
        removeDuplicates();
    }

    @Override
    //Merges two lists
    public void merge(List<T> list) {
        if (list == null){
            return;
        }
        ArrayList<T> other = (ArrayList<T>) list;
        sort();
        other.sort();
        T[] dupe = (T[]) new Comparable[size+other.size];
        int index = 0;
        int index2 = 0;
        int n = 0;
        while (index != size && index2 != other.size){
            if (data[index].compareTo(other.data[index2]) <= 0){
                dupe[n] = data[index];
                index++;
                n++;
            }
            else if (other.data[index2].compareTo(data[index]) <= 0){
                dupe[n] = other.data[index2];
                index2++;
                n++;
            }
        }if (index2 == other.size && index != size){
            while (index != size){
                dupe[n] = data[index];
                n++;
                index++;
            }
        }
        else if (index == size && index2 != other.size){
            while (index2 != other.size){
                dupe[n] = other.data[index2];
                n++;
                index2++;
            }
        }
        data = dupe;
        int m = 0;
        for (int i = 0; i < data.length; i++){
            if (data[i] != null){
                m++;
            }
        } size = m;
        }


    @Override
    //Returns the minimum value
    public T getMin() {
        if (size == 0){
            return null;
        }
        if (isSorted){
            return data[0];
        }
        T lowVal = data[0];
        int pos = 0;
        for(int i = 0; i < size; i++){
            if (data[i].compareTo(lowVal) < 0){
                lowVal = data[i];
                pos = i;
            }
        }
        return data[pos];
    }

    @Override
    //Returns the maximum value
    public T getMax() {
        if (size == 0){
            return null;
        }
        if (isSorted){
            return data[size-1];
        }
        T maxVal = data[0];
        int pos = 0;
        for(int i = 0; i < size; i++){
            if (data[i].compareTo(maxVal) > 0){
                maxVal = data[i];
                pos = i;
            }
        }
        return data[pos];
    }

    @Override
    //Simply returns the attribute isSorted
    public boolean isSorted() {
        return isSorted;
    }


    public String toString(){
        String result = "";
        for (int j = 0; j < data.length; j++){
           result = result + data[j]+ "\n";
        }return  result;
    }

    public static void main(String[] args){
        ArrayList <Integer> al = new ArrayList<>();
        al.add(3);
        al.add(2);
        al.add(3);
        al.add(2);
        ArrayList <Integer> al1 = new ArrayList<>();
        al1.add(3);
        al1.add(2);
        al1.add(3);
        al1.add(2);
        System.out.println(al.size);



    }
}
