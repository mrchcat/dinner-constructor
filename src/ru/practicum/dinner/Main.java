package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static DinnerConstructor dc = new DinnerConstructor();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isContinueMenu = true;//условие на выход из меню

        do {
            printMenu();
            switch (getNumberFromUser(scanner)) {
                case 1 -> addNewDish();
                case 2 -> generateDishCombo();
                case 3 -> dc.printMenu();
                case 4 -> isContinueMenu = false;
                default -> System.out.println("Введите корректный номер меню");
            }
        } while (isContinueMenu);
        System.out.println("Работа завершена");
    }

    private static void printMenu() {
        System.out.println("\nВыберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Распечатать меню");
        System.out.println("4 - Выход");
    }

    private static int getNumberFromUser(Scanner scanner) {
        int choice = -1;
        if (scanner.hasNextInt()) choice = scanner.nextInt();
        if (scanner.hasNextLine()) scanner.nextLine(); // удаляем лишний текст, если пользователь его напечатал
        return choice;
    }

    private static String getStringFromUser(Scanner scanner) {
        String choice = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        //if (scanner.hasNextLine()) scanner.nextLine(); // удаляем лишний текст, если пользователь его напечатал
        return choice;
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = getStringFromUser(scanner);
        System.out.println("Введите название блюда:");
        String dishName = getStringFromUser(scanner);
        if (dc.isDishExist(dishName)) {
            System.out.println("Ошибка ! Блюдо \"" + dishName + "\" уже присутствует в меню. Введите другое блюдо");
        } else {
            dc.addNewDish(dishType,dishName);
            System.out.println("блюдо \"" + dishName + "\" успешно добавлено в категорию \"" + dishType + "\"");
        }
//        dc.printMenu();
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");
        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos;
        while ((numberOfCombos = getNumberFromUser(scanner)) <= 0) {
            System.out.println("Введите положительное число наборов");
        }
        Box<String> boxWithTypes = new Box<>();
        String dishType;

        System.out.println("Вводите типы блюд, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        while (!(dishType=getStringFromUser(scanner)).isEmpty()) {
            if (dc.isDishTypeExist(dishType)) {
                boxWithTypes.add(dishType);
            } else {
                System.out.println("Ошибка. Категория \"" + dishType + "\" отсутствует в меню. Введите другой тип");
            }
        }
        if (boxWithTypes.isEmpty()) {
            System.out.println("Ошибка ! Корректные типы блюд не введены. Проверьте, что меню заполнено и повторите ввод");
        }
        else{
            dc.generateDishCombo(numberOfCombos, boxWithTypes);
        }
    }


}
