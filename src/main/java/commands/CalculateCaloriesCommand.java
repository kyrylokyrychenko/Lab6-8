package commands;

import vegetables.Salad;

public class CalculateCaloriesCommand implements Command {

    private Salad salad;

    public CalculateCaloriesCommand(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        double total = salad.getTotalCalories();
        System.out.println("\nЗагальна калорійність салату: " + total + " ккал");
    }

    @Override
    public String getDesc() {
        return "Підрахувати калорійність салату";
    }
}
