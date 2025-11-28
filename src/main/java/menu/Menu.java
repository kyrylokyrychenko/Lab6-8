package menu;

import java.util.*;
import commands.*;
import utils.ErrorNotifier;
import vegetables.Salad;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Menu {

    private static final Logger logger = LogManager.getLogger(Menu.class);

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

        if (command != null) {
            logger.info("Виконується команда: {}", choice);
            try {
                command.execute();
            } catch (Exception e) {
                logger.error("Помилка при виконанні команди '{}'", choice, e);
                ErrorNotifier.sendErrorEmail(e);
            }
        } else {
            logger.warn("Невірний вибір: {}", choice);
            System.out.println("Невірний вибір!");
        }
    }

    public void start() {
        while (true) {
            show();
            System.out.print("Виберіть пункт меню: ");
            String choice = sc.nextLine().trim().toLowerCase();

            if (choice.equals("вихід")) {
                logger.info("Користувач завершив роботу програми");
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


