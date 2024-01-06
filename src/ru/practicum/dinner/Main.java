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
                case 3 -> isContinueMenu = false;
            }
        } while (isContinueMenu);
        System.out.println("Работа завершена");
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
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
        if (dc.addNewDish(dishType, dishName)) {
            System.out.println("блюдо \"" + dishName + "\" успешно добавлено в категорию \"" + dishType + "\"");
        } else {
            System.out.println("Ошибка ! Блюдо \"" + dishName + "\" уже присутствует в меню. Введите другое блюдо");
        }
        dc.printMenu();
    }

    private static void generateDishCombo() {
        boolean isAnyDishesAdded = false;
        System.out.println("Начинаем конструировать обед...");
        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos;
        while ((numberOfCombos = getNumberFromUser(scanner)) <= 0) {
            System.out.println("Введите положительное наборов");
        }
        ArrayList<String> mealTypes = new ArrayList<>();
        String dishType;

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        while (!(dishType=getStringFromUser(scanner)).isEmpty()) {
            if (!dc.isDishTypeExist(dishType)) {
                System.out.println("Ошибка. Категория \"" + dishType + "\" отсуствует в меню. Введите другой тип");
            } else {
                mealTypes.add(dishType);
                isAnyDishesAdded = true;
            }
        }
        if (isAnyDishesAdded) dc.generateDishCombo(numberOfCombos, mealTypes);
        else System.out.println("Ошибка ! Корректные типы блюд не введены. Проверьте, что меню заполнено и повторите ввод");
    }


}
