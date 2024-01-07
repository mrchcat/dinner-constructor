package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class DinnerConstructor {
    private final HashMap<String, ArrayList<String>> dishTypeAndDishes; //хранит категорию и список блюд
    private final HashSet<String> dishes; //хранит только блюда (для ускорения проверки на повторный ввод блюд)
    private final HashSet<HashSet<String>> comboSet; //хранит наборы блюд согласно выбору пользователя

    public DinnerConstructor() {
        this.dishTypeAndDishes = new HashMap<>();
        this.dishes = new HashSet<>();
        this.comboSet = new HashSet<>();
    }

    boolean isDishTypeExist(String dishType) {
        return dishTypeAndDishes.containsKey(dishType);
    }

    boolean isDishExist(String dishName) {
        return dishes.contains(dishName);
    }

    void addNewDish(String dishType, String dishName) {
        //добавляем блюдо в таблицу, где хранятся только блюда (для ускорения проверки на повторы)
        dishes.add(dishName);
        //добавляем блюдо в таблицу с категориями и блюдами
        if (dishTypeAndDishes.containsKey(dishType)) {
            dishTypeAndDishes.get(dishType).add(dishName);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(dishName); //
            dishTypeAndDishes.put(dishType, list);
        }
    }

    void generateDishComboSet(int numberOfCombos, Box<String> boxWithTypes) {
        //проверка на то, хватит ли блюд в базе для генерации требуемого количества комбинаций
        if (!isEnoughPower(numberOfCombos, boxWithTypes)) return;

        //создаем комбинаций и заполняем comboSet
        int generatedCombos = 0;//количество созданных комбинаций
        HashSet<String> combo; //переменная для хранения конкретной комбинации блюд
        comboSet.clear();
        while (generatedCombos < numberOfCombos) {
            combo = getCombo(boxWithTypes);
            if (!comboSet.contains(combo)) {
                comboSet.add(combo);
                generatedCombos++;
            }
        }
        printComboSet();
    }

    //функция для создания конкретной комбинации
    private HashSet<String> getCombo(Box<String> boxWithTypes) {
        HashSet<String> combo = new HashSet<>();
        String dishName;
        int dishTypeAskedFreq;  //количество блюд конкретной категорий, указанной пользователем в запросе
        int numOfDishes;   //фактическое количество блюд в конкретной категории
        int generatedDishes;   //количество блюд, добавленных в комбо для конкретной категории
        Random rnd = new Random();

        for (String dishType : boxWithTypes.getKeySet()) {  //перебираем запрошенные пользователем категории

            dishTypeAskedFreq = boxWithTypes.get(dishType);
            numOfDishes = dishTypeAndDishes.get(dishType).size();
            generatedDishes = 0;

            while (generatedDishes < dishTypeAskedFreq) {
                dishName = dishTypeAndDishes.get(dishType).get(rnd.nextInt(numOfDishes));
                if (!combo.contains(dishName)) {
                    combo.add(dishName);
                    generatedDishes++;
                }
            }
        }
        return combo;
    }

    //проверка, достаточно ли блюд для генерации запрошенного количества, включая количества в разрезе категорий
    private boolean isEnoughPower(int numberOfCombos, Box<String> boxWithTypes) {
        boolean isEnoughPower = true;
        int dishAskedFreq; //количество блюд конкретной категорий, указанной пользователем в запросе
        int dishFactFreq; //фактическое количество блюд в конкретной категории
        long totalPower = 1; //максимальное количество блюд, которое возможно сгенерировать для указанных категорий

        for (String dishType : boxWithTypes.getKeySet()) { //перебираем запрошенные пользователем категории
            dishAskedFreq = boxWithTypes.get(dishType);
            dishFactFreq = dishTypeAndDishes.get(dishType).size();

            if (dishAskedFreq > dishFactFreq) {
                System.out.println("Ошибка! Категория \"" + dishType + "\" требует " + dishAskedFreq +
                        " уникальных блюда, но в меню их не более " + dishFactFreq + " шт. Добавьте больше блюд.");
                isEnoughPower = false;
                totalPower = totalPower * dishFactFreq;
            } else {
                totalPower = totalPower * combination(dishAskedFreq, dishFactFreq);
            }
        }

        if (totalPower < numberOfCombos) {
            System.out.println("Ошибка! Возможное число уникальных комбинаций всех блюд не превышает " + totalPower +
                    " ,в то время как вы запрашиваете " + numberOfCombos);
            System.out.println("Увеличьте число блюд");
            isEnoughPower = false;
        }
        return isEnoughPower;
    }

    //расчет количества сочетаний из n элементов по k (k<=n);
    private long combination(int k, int n) {
        long result = 1;
        for (int i = n - k + 1; i <= n; i++) {
            result = result * i;
        }
        for (int i = 1; i <= k; i++) {
            result = result / i;
        }
        return result;
    }

    private void printComboSet() {
        int count = 0;
        for (var combo : comboSet) {
            count++;
            System.out.println("Комбо " + count);
            System.out.println(combo);
        }
    }

    void printAllDishes() {
        if (dishTypeAndDishes.isEmpty()) System.out.println("Список блюд пуст");
        else {
            System.out.println("Список блюд");
            for (var entry : dishTypeAndDishes.entrySet()) {
                System.out.println("Категория: " + entry.getKey());
                for (String dish : entry.getValue()) {
                    System.out.println(" ".repeat(11) + "-" + dish);
                }
            }
        }
    }

}