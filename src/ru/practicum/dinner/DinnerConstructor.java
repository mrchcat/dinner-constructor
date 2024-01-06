package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DinnerConstructor {
    private HashMap<String, ArrayList<String>> menu; //тип-блюда
    private HashSet<String> dishes; //блюда


    public DinnerConstructor() {
        this.menu = new HashMap<>();
        this.dishes = new HashSet<>();
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
        System.out.println("numberOfCombos = " + numberOfCombos);
        boxWithTypes.printBox();
        /*      int totalCombinations = 1;
        for (String dishType : mealTypes) {
            totalCombinations *= menu.get(dishType).size();
        }
        if (numberOfCombos > totalCombinations) {
            System.out.println("Требуемое число комбинаций превышает возможности меню. Добавьте больше блюд");
            generateAll(mealTypes);
        } else if (numberOfCombos == totalCombinations) generateAll(mealTypes);
        else generateRandom(numberOfCombos, mealTypes);
    }

    private void generateAll(HashSet<String> mealTypes) {
        for (String dishType : mealTypes) {
            for(String dish: menu.get(dishType)){

            }
        }*/
    }

}
