package commands;

import menu.Menu;
import vegetables.Salad;

import java.util.LinkedHashMap;
import java.util.Map;

public class Submenu extends Menu implements Command{

    @Override
    public void execute() {
       start();
    }

    @Override
    public String getDesc() {
        return "Сабменю";
    }

    @Override
    protected void init() {
        commands.put("додати", new AddVegetableCommand(salad));
        commands.put("показати", new ShowSaladCommand(salad));
        commands.put("підрахувати", new CalculateCaloriesCommand(salad));
        commands.put("сортувати", new SortVegetablesCommand(salad));
        commands.put("знайти", new FindVegetablesByCaloriesCommand(salad));
        commands.put("видалити", new RemoveVegetableCommand(salad));
        commands.put("оновити", new UpdateVegetableCommand(salad));
    }
}

