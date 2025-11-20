package commands;

import vegetables.Salad;
import vegetables.Vegetable;

public class ShowSaladCommand implements Command {

    private final Salad salad;

    public ShowSaladCommand(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Склад салату ===");

        if (salad.getVegetables().isEmpty()) {
            printEmpty();
        } else {
            printList();
        }
    }

    private void printEmpty() {
        System.out.println("Салат порожній!");
    }

    private void printList() {
        int i = 1;
        for (Vegetable v : salad.getVegetables()) {
            System.out.println(i + ": " + v);
            i++;
        }
    }

    @Override
    public String getDesc() {
        return "Показати всі овочі в салаті";
    }
}
