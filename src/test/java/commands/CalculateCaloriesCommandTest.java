package commands;

import org.junit.jupiter.api.*;
import vegetables.Salad;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class CalculateCaloriesCommandTest {

    private Salad salad;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setup() {
        salad = mock(Salad.class);
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    void testExecute() {
        when(salad.getTotalCalories()).thenReturn(123.5);

        CalculateCaloriesCommand cmd = new CalculateCaloriesCommand(salad);
        cmd.execute();

        verify(salad, times(1)).getTotalCalories();

        String output = out.toString();
        Assertions.assertTrue(output.contains("123.5"));
        Assertions.assertTrue(output.contains("Загальна калорійність салату"));
    }

    @Test
    void testGetDesc() {
        CalculateCaloriesCommand cmd = new CalculateCaloriesCommand(salad);

        Assertions.assertEquals(
                "Підрахувати калорійність салату",
                cmd.getDesc()
        );
    }
}
