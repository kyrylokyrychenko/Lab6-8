package commands;

import vegetables.Salad;

import java.util.Scanner;

public class RemoveVegetableCommand implements Command {

    private final Salad salad;
    private final Scanner sc = new Scanner(System.in);

    public RemoveVegetableCommand(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        int index = requestIndex();

        if (!isValidIndex(index)) {
            System.out.println("Некоректний номер!");
            return;
        }

        salad.remove(index - 1);
        System.out.println("Овоч видалено.");
    }

    private int requestIndex() {
        System.out.print("Введіть номер овоча для видалення: ");
        return Integer.parseInt(sc.nextLine());
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= salad.getVegetables().size();
    }

    @Override
    public String getDesc() {
        return "Видалити овоч за номером у салаті";
    }
}
