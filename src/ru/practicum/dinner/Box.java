package ru.practicum.dinner;

import java.util.HashMap;
import java.util.Set;

//класс, условно представляющий собой табличку с 2 столбцами, где слева тип, а справа - его количество
//в программе используется для хранения выбранных категорий блюд
public class Box<T> {
    private final HashMap<T, Integer> box;

    public Box() {
        this.box = new HashMap<>();
    }

    void add(T dishType) {
        int num = 1;
        if (box.containsKey(dishType)) {
            num = box.get(dishType) + 1;
        }
        box.put(dishType, num);
    }

    Integer get(T dishType) {
        return box.get(dishType);
    }

    boolean isEmpty() {
        return box.isEmpty();
    }

    public Set<T> getKeySet() {
        return box.keySet();
    }

}


