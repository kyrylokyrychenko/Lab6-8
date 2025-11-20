package commands;

import menu.Menu;
import org.junit.jupiter.api.*;
import vegetables.Salad;

import java.util.Map;

import static org.mockito.Mockito.*;

public class SubmenuTest {

    private Submenu submenu;
    private Salad salad;

    @BeforeEach
    void setup() {
        salad = mock(Salad.class);

        submenu = new Submenu();

        // ⚠ встановлюємо salad у базовому класі Menu
        try {
            var field = Menu.class.getDeclaredField("salad");
            field.setAccessible(true);
            field.set(submenu, salad);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    void testExecuteCallsStart() {
//        Submenu spyMenu = spy(submenu);
//
//        spyMenu.execute();
//
//        verify(spyMenu, times(1)).start();
//    }

    @Test
    void testInitCreatesAllCommands() {
        submenu.init();

        Map<String, Command> commands;
        try {
            var field = Menu.class.getDeclaredField("commands");
            field.setAccessible(true);
            commands = (Map<String, Command>) field.get(submenu);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(7, commands.size());

        Assertions.assertTrue(commands.get("додати") instanceof AddVegetableCommand);
        Assertions.assertTrue(commands.get("показати") instanceof ShowSaladCommand);
        Assertions.assertTrue(commands.get("підрахувати") instanceof CalculateCaloriesCommand);
        Assertions.assertTrue(commands.get("сортувати") instanceof SortVegetablesCommand);
        Assertions.assertTrue(commands.get("знайти") instanceof FindVegetablesByCaloriesCommand);
        Assertions.assertTrue(commands.get("видалити") instanceof RemoveVegetableCommand);
        Assertions.assertTrue(commands.get("оновити") instanceof UpdateVegetableCommand);
    }

    @Test
    void testGetDesc() {
        Assertions.assertEquals("Сабменю", submenu.getDesc());
    }
}
