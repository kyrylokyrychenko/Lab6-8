package commands;

import java.util.Scanner;
import vegetables.*;
import vegetables.Salad;

public class AddVegetableCommand implements Command {

    private Salad salad;
    private Scanner sc;

    public AddVegetableCommand(Salad salad) {
        this(salad, new Scanner(System.in));
    }

    public AddVegetableCommand(Salad salad, Scanner sc) {
        this.salad = salad;
        this.sc = sc;
    }

    @Override
    public void execute() {
        showMenu();
        String choice = sc.nextLine();

        double weight = requestWeight();

        Vegetable veg = createVegetable(choice, weight);

        if (veg != null)
            salad.add(veg);
        else
            System.out.println("Невірний вибір.");
    }

    private void showMenu() {
        System.out.println("\nОберіть овоч:");
        System.out.println("1 — Морква");
        System.out.println("2 — Помідор");
        System.out.println("3 — Огірок");
        System.out.println("4 — Лук");
        System.out.println("5 — Капуста");
        System.out.println("6 — Перець");
        System.out.print("Ваш вибір: ");
    }

    private double requestWeight() {
        System.out.print("Введіть вагу (в грамах): ");
        return Double.parseDouble(sc.nextLine());
    }

    // Окрема логіка — тепер легко тестувати!
    protected Vegetable createVegetable(String choice, double weight) {
        return switch (choice) {
            case "1" -> new Carrot(weight);
            case "2" -> new Tomato(weight);
            case "3" -> new Cucumber(weight);
            case "4" -> new Onion(weight);
            case "5" -> new Cabbage(weight);
            case "6" -> new Pepper(weight);
            default -> null;
        };
    }

    @Override
    public String getDesc() {
        return "Додати овоч до салату";
    }
}
