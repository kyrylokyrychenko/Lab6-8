package commands;

import vegetables.Salad;

public class SortVegetablesCommand implements Command {

    private final Salad salad;

    public SortVegetablesCommand(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        salad.sortByCalories();
        System.out.println("\nОвочі відсортовано за калорійністю.");
    }

    @Override
    public String getDesc() {
        return "Сортувати овочі за калорійністю";
    }
}
