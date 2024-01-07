package ru.practicum.dinner;

import java.util.Scanner;

/*
И.К.:На мой взгляд, лучше методы addNewDish и generateDishCombo перенести в класс генерации,
а в классе main оставить работу с меню и вводом выводом

В.Т.: Не могу сказать, что до конца понял предложение. Вынес методы в класс Generator, откуда они в свою очередь
вызывают DinnerConstructor и Box. По иному сделать не получается, т.к. по условию логика расчета и общение с
пользователем должны быть разделены. Учитывая, что общение предполагает многочисленные проверки, то либо мы часть
методов оставляем в Main (первоначальный вариант), либо выносим в отдельный класс, откуда в своб очередь вызываем
класс с логикой расчета (текущий вариант).

*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Generator generator=new Generator(scanner);
        boolean isContinueMenu = true;//условие на выход из меню

        do {
            printMenu();
            switch (getNumberFromUser(scanner)) {
                case 1 -> generator.addNewDish();
                case 2 -> generator.generateDishCombo();
                case 3 -> generator.printAllDishes();
                case 4 -> isContinueMenu = false;
                default -> System.out.println("Введите корректный номер меню");
            }
        } while (isContinueMenu);
        scanner.close();
        System.out.println("Работа завершена");
    }

    private static void printMenu() {
        System.out.println("*".repeat(34));
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Распечатать список блюд");
        System.out.println("4 - Выход");
        System.out.println("*".repeat(34));
    }

    private static int getNumberFromUser(Scanner scanner) {
        int choice = -1;
        if (scanner.hasNextInt()) choice = scanner.nextInt();
        if (scanner.hasNextLine()) scanner.nextLine(); // удаляем лишний текст, если пользователь его напечатал
        return choice;
    }


}
