package menu;

import commands.Command;
import org.junit.jupiter.api.*;
import vegetables.Salad;

import java.io.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    /** Перевіряємо, що init() створює всі команди */
    @Test
    void testInitCreatesAllCommands() {
        Menu menu = new Menu();
        Map<String, Command> map = menu.getCommands();

        assertTrue(map.containsKey("додати"));
        assertTrue(map.containsKey("показати"));
        assertTrue(map.containsKey("підрахувати"));
        assertTrue(map.containsKey("сортувати"));
        assertTrue(map.containsKey("знайти"));
        assertTrue(map.containsKey("видалити"));
        assertTrue(map.containsKey("оновити"));
        assertTrue(map.containsKey("сабменю"));
    }

    /** Перевіряємо виконання існуючої та неіснуючої команди */
    @Test
    void testExecuteValidAndInvalidCommand() {
        Menu menu = new Menu();

        // Мокаємо одну команду
        Command mockCommand = mock(Command.class);
        menu.commands.put("тест", mockCommand);

        // valid
        menu.execute("тест");
        verify(mockCommand, times(1)).execute();

        // invalid
        out.reset();
        menu.execute("немаєтакої");
        assertTrue(out.toString().contains("Невірний вибір!"));
    }

    /** Покриття show() */
    @Test
    void testShowDisplaysMenu() {
        Menu menu = new Menu();
        menu.show();
        String printed = out.toString();

        assertTrue(printed.contains("===== МЕНЮ САЛАТУ ====="));
        assertTrue(printed.contains("вихід"));
    }

    /** Повне тестування start(): одна команда → вихід */
    @Test
    void testStartExecutesCommandAndExits() {
        // Створюємо вхід:
        // 1) команда "показати"
        // 2) "вихід"
        String input = "показати\nвихід\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu();

        // Мокаємо конкретну команду всередині Menu
        Command mockShow = mock(Command.class);
        menu.commands.put("показати", mockShow);

        menu.start();

        verify(mockShow, times(1)).execute();
        assertTrue(out.toString().contains("Вихід з програми"));
    }
}
