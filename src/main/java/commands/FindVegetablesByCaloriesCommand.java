package commands;

import vegetables.Salad;
import vegetables.Vegetable;

import java.util.List;
import java.util.Scanner;

public class FindVegetablesByCaloriesCommand implements Command {

    private final Salad salad;
    private final Scanner sc = new Scanner(System.in);

    public FindVegetablesByCaloriesCommand(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        double min = requestDouble("Мінімум калорій: ");
        double max = requestDouble("Максимум калорій: ");

        List<Vegetable> result = salad.findByCalories(min, max);
        printResult(result);
    }

    private double requestDouble(String message) {
        System.out.print(message);
        return Double.parseDouble(sc.nextLine());
    }

    private void printResult(List<Vegetable> vegetables) {
        System.out.println("\nОвочі в заданому діапазоні:");

        if (vegetables.isEmpty()) {
            System.out.println("Нічого не знайдено.");
            return;
        }

        vegetables.forEach(System.out::println);
    }

    @Override
    public String getDesc() {
        return "Знайти овочі за діапазоном калорій";
    }
}
