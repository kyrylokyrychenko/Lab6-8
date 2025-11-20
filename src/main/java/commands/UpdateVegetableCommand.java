package commands;

import vegetables.Salad;
import vegetables.Vegetable;

import java.util.Scanner;

public class UpdateVegetableCommand implements Command {

    private final Salad salad;
    private final Scanner sc = new Scanner(System.in);

    public UpdateVegetableCommand(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        int index = requestIndex();

        if (!isValidIndex(index)) {
            System.out.println("Некоректний номер!");
            return;
        }

        double newWeight = requestWeight();
        updateWeight(index - 1, newWeight);

        System.out.println("Овоч оновлено.");
    }

    private int requestIndex() {
        System.out.print("Введіть номер овоча для оновлення: ");
        return Integer.parseInt(sc.nextLine());
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= salad.getVegetables().size();
    }

    private double requestWeight() {
        System.out.print("Нова вага (г): ");
        return Double.parseDouble(sc.nextLine());
    }

    private void updateWeight(int index, double weight) {
        Vegetable v = salad.getVegetables().get(index);
        v.setWeight(weight);
    }

    @Override
    public String getDesc() {
        return "Оновити вагу овоча за номером у салаті";
    }
}
