package ru.practicum.dinner;

import java.util.Locale;
import java.util.Scanner;

public class Generator {
    private final DinnerConstructor dc  ;
    private final Scanner scanner;

    Generator(Scanner scanner){
        dc=new DinnerConstructor();
        this.scanner=scanner;
    }

    private int getNumberFromUser() {
        int choice = -1;
        if (scanner.hasNextInt()) choice = scanner.nextInt();
        if (scanner.hasNextLine()) scanner.nextLine(); // удаляем лишний текст, если пользователь его напечатал
        return choice;
    }

    private String getStringFromUser() {
        return scanner.nextLine().trim().toLowerCase(Locale.ROOT);
    }

    void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = getStringFromUser();
        System.out.println("Введите название блюда:");
        String dishName = getStringFromUser();
        if (dc.isDishExist(dishName)) {
            System.out.println("Ошибка ! Блюдо \"" + dishName + "\" уже присутствует в меню. Введите другое блюдо");
        } else {
            dc.addNewDish(dishType, dishName);
            System.out.println("блюдо \"" + dishName + "\" добавлено в категорию \"" + dishType + "\"");
        }
    }

     void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");
        System.out.println("Введите количество наборов, которые нужно сгенерировать:");

        int numberOfCombos;
        Box<String> boxWithTypes = new Box<>(); //переменная для хранения выбранных категорий вместе с их количеством
        String dishType;

        while ((numberOfCombos = getNumberFromUser()) <= 0) {
            System.out.println("Введите положительное число наборов");
        }
        System.out.println("Введите типы блюд, разделяя символом переноса строки (enter). " +
                "Для завершения ввода введите пустую строку");
        while (!(dishType = getStringFromUser()).isEmpty()) {
            if (dc.isDishTypeExist(dishType)) {
                boxWithTypes.add(dishType);
            } else {
                System.out.println("Ошибка. Категория \"" + dishType + "\" отсутствует в меню. Введите другой тип" +
                        " или пустую строку для завершения");
            }
        }
        if (boxWithTypes.isEmpty()) {
            System.out.println("Ошибка ! Корректные типы блюд не введены. " +
                    "Проверьте, что меню заполнено и повторите ввод");
        } else{
            dc.generateDishComboSet(numberOfCombos, boxWithTypes);
        }
    }

    void printAllDishes(){
        dc.printAllDishes();
    }
}
