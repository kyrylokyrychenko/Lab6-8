package commands;

import utils.ErrorNotifier;
import vegetables.Salad;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class RemoveVegetableCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RemoveVegetableCommand.class);

    private final Salad salad;
    private final Scanner sc = new Scanner(System.in);

    public RemoveVegetableCommand(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        try {
            int index = requestIndex();

            if (!isValidIndex(index)) {
                logger.warn("Спроба видалення неіснуючого індекса: {}", index);
                System.out.println("Некоректний номер!");
                return;
            }

            salad.remove(index - 1);
            logger.info("Овоч видалено з позиції {}", index);

            System.out.println("Овоч видалено.");
        } catch (Exception e) {
            logger.error("Помилка під час видалення овоча", e);
            ErrorNotifier.sendErrorEmail(e);
        }
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
