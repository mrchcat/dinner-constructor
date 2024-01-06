package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DinnerConstructor {
    private HashMap<String, String> menu;

    public DinnerConstructor() {
        this.menu = new HashMap<String, HashSet<String>>();
    }

    boolean addNewDish(String dishType, String dishName) {
        HashSet<String> dishes;
        if (menu.containsKey(dishType)) {
            dishes = menu.get(dishType);
            if (dishes.contains(dishName)) return false;
            else dishes.add(dishName);
        } else {
            dishes = new HashSet<>();
            dishes.add(dishName);
            menu.put(dishType, dishes);
        }
        return true;
    }

    boolean isContainDishType(String dishType) {
        return menu.containsKey(dishType);
    }

    void printMenu() {
        System.out.println("Меню:");
        for (var entry : menu.entrySet()) {
            System.out.println(entry.getKey());
            for (String s : entry.getValue()) {
                System.out.println("    " + s);
            }
        }
    }

    void generateDishCombo(int numberOfCombos, ArrayList<String> mealTypes) {
        int totalCombinations = 1;
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
        }
    }

}
