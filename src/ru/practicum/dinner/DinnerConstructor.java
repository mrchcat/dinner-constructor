package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DinnerConstructor {
    private HashMap<String, String> menu; //блюдо-тип


    public DinnerConstructor() {
        this.menu = new HashMap<String, String>();
    }

    boolean addNewDish(String dishType, String dishName) {
        if (menu.containsKey(dishName)) return false;
        else {
            menu.put(dishName,dishType);
        }
        return true;
    }

    boolean isDishTypeExist(String dishType){
        return menu.containsValue(dishType);
    }

    void printMenu() {
        System.out.println("Меню:");
        for (var dish: menu.entrySet()) {
            System.out.println(dish.getValue()+": "+dish.getKey());
        }
    }


    void generateDishCombo(int numberOfCombos, ArrayList<String> mealTypes) {
        System.out.println("numberOfCombos = " + numberOfCombos);
        System.out.println("mealTypes = " + mealTypes);
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
