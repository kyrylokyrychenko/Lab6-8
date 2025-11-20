package menu;

import java.util.*;
import commands.*;
import vegetables.Salad;

public class Menu {
    protected Map<String, Command> commands = new LinkedHashMap<>();
    private Scanner sc = new Scanner(System.in);
    protected Salad salad = new Salad();   // порожній салат на старті

    public Menu() {
        init();
    }

     protected void init() {
        commands.put("додати", new AddVegetableCommand(salad));
        commands.put("показати", new ShowSaladCommand(salad));
        commands.put("підрахувати", new CalculateCaloriesCommand(salad));
        commands.put("сортувати", new SortVegetablesCommand(salad));
        commands.put("знайти", new FindVegetablesByCaloriesCommand(salad));
        commands.put("видалити", new RemoveVegetableCommand(salad));
        commands.put("оновити", new UpdateVegetableCommand(salad));
        commands.put("сабменю", new Submenu());
     }

    public void show() {
        System.out.println("\n===== МЕНЮ САЛАТУ =====");
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getDesc());
        }
        System.out.println("вихід - Вихід з програми");
    }

    public void execute(String choice) {
        Command command = commands.get(choice);
        if (command != null)
            command.execute();
        else
            System.out.println("Невірний вибір!");
    }

    public void start() {
        while (true) {
            show();
            System.out.print("Виберіть пункт меню: ");
            String choice = sc.nextLine().trim().toLowerCase();

            if (choice.equals("вихід")) {
                System.out.println("\nВихід з програми...");
                break;
            }

            execute(choice);
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

}


