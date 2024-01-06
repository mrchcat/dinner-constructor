package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class DinnerConstructor {
    private final HashMap<String, ArrayList<String>> menu; //тип-блюда
    private final HashSet<String> dishes; //блюда
    private final HashSet<Combo> comboSet;


    public DinnerConstructor() {
        this.menu = new HashMap<>();
        this.dishes = new HashSet<>();
        this.comboSet = new HashSet<>();
    }

    boolean isDishTypeExist(String dishType) {
        return menu.containsKey(dishType);
    }

    boolean isDishExist(String dishName) {
        return dishes.contains(dishName);
    }


    void addNewDish(String dishType, String dishName) {
        dishes.add(dishName);
        if (menu.containsKey(dishType)) {
            menu.get(dishType).add(dishName);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(dishName);
            menu.put(dishType, list);
        }
    }

    void printMenu() {
        System.out.println("Меню:");
        if (menu.isEmpty()) System.out.println("Меню не заполнено");
        else {
            for (var entry : menu.entrySet()) {
                System.out.println("Категория: " + entry.getKey());
                for (String dish : entry.getValue()) {
                    System.out.println("    -" + dish);
                }
            }
        }
    }

    void generateDishCombo(int numberOfCombos, Box<String> boxWithTypes) {
        comboSet.clear();
        System.out.println("вошли в generateDishCombo");
        System.out.println("numberOfCombos="+numberOfCombos);
        boxWithTypes.printBox();
        if (!isEnoughPower(numberOfCombos, boxWithTypes)) return;
        int count = 0;
        Combo combo;
        System.out.println("generateDishCombo после проверки");
        while (count < numberOfCombos) {
            System.out.println("count="+count);
            combo = getCombo(boxWithTypes);
            System.out.println(combo);
            if (!comboSet.contains(combo)) {
                comboSet.add(combo);
                count++;
                System.out.println("добавили");
            }
            else System.out.println("отказ");
        }
        printCombo();
    }

    private Combo getCombo(Box<String> boxWithTypes) {
        System.out.println("вошли в getCombo");
        HashSet<String> set = new HashSet<>();
        String dishName;
        int dishAskedFreq;
        int numOfDishes;
        int random;
        int count;

        for (String dishType : boxWithTypes.getKeySet()) {
            dishAskedFreq = boxWithTypes.get(dishType);
            numOfDishes = menu.get(dishType).size();
            System.out.println("dishType="+dishType+"dishAskedFreq="+dishAskedFreq+"; numOfDishes="+numOfDishes);
            count=0;
            while (count < dishAskedFreq) {
                double r1=Math.random() * numOfDishes;
                random = (int) r1;
                System.out.println("r1="+r1+" random="+random);
                dishName = menu.get(dishType).get(random);
                System.out.println("random="+random+" dishName="+dishName);
                if (!set.contains(dishName)) {
                    set.add(dishName);
                    count++;
                }
            }
        }
        System.out.println(set);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Combo(set);
    }

    private void printCombo() {
        int count = 0;
        for (var combo : comboSet) {
            count++;
            System.out.println("Комбо " + count);
            System.out.println("[" + combo + "]");
        }
    }

    boolean isEnoughPower(int numberOfCombos, Box<String> boxWithTypes) {
//        System.out.println("isEnoughPower");

        boolean isEnoughPower = true;
        int dishAskedFreq;
        int dishFactFreq;
        long totalPower = 1;

        //проверяем, достаточно ли блюд внутри категорий для запрошенного количества категорий
        for (String dishType : boxWithTypes.getKeySet()) {
            dishAskedFreq = boxWithTypes.get(dishType);
            dishFactFreq = menu.get(dishType).size();
            if (dishAskedFreq > dishFactFreq) {
                System.out.println("Ошибка! Категория \"" + dishType + "\" требует " + dishAskedFreq + " уникальных блюда,"
                        + "но в меню их не более " + dishFactFreq + " шт. Добавьте больше блюд.");
                isEnoughPower = false;
                totalPower = totalPower * dishFactFreq;
            } else totalPower = totalPower * combination(dishAskedFreq, dishFactFreq);
        }
        if (totalPower < numberOfCombos) {
            System.out.println("Ошибка! Возможное число уникальных комбинаций всех блюд не превышает " + totalPower + " ," +
                    "в то время как вы запрашиваете " + numberOfCombos);
            System.out.println("Увеличьте число блюд");
            isEnoughPower = false;
        }
//        System.out.println("вышли из isEnoughPower");
        return isEnoughPower;
    }

    private long combination(int k, int n) {
        long result = 1;
        for (int i = n - k + 1; i <= n; i++) {
            result = result * i;
        }
        for (int i = 1; i <= k; i++) {
            result = result / i;
        }
        System.out.println("combination: k="+k+" n="+n+" res="+result );
        return result;
    }
}