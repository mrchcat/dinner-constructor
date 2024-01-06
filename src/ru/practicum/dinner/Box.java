package ru.practicum.dinner;

import java.util.HashMap;
import java.util.Set;

public class Box<T> {
    private final HashMap<T, Integer> box;

    public Box() {
        this.box = new HashMap<>();
    }

     void add(T dishType){
        int num=1;
        if(box.containsKey(dishType)){
            num=box.get(dishType);
            num++;
        }
        box.put(dishType, num);
     }

     Integer get(T dishType){
        return box.get(dishType);
     }

    void printBox(){
         for (var entry: box.entrySet()) {
             System.out.println("{"+entry.getKey()+" - "+entry.getValue()+"}");
         }
     }

     boolean isEmpty(){
        return box.isEmpty();
     }

    int size(){
        return box.size();
    }

    public Set<T> getKeySet(){
        return box.keySet();
    }

}


