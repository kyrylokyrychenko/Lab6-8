package commands;

import org.junit.jupiter.api.Test;
import vegetables.Vegetable;
import vegetables.Salad;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ShowSaladCommandTest {

    @Test
    void testEmptySalad() {
        // GIVEN
        Salad salad = mock(Salad.class);
        when(salad.getVegetables()).thenReturn(List.of());

        ShowSaladCommand cmd = new ShowSaladCommand(salad);

        // WHEN
        cmd.execute();

        // THEN
        verify(salad, times(1)).getVegetables();
    }

    @Test
    void testNonEmptySalad() {
        // GIVEN
        Salad salad = mock(Salad.class);
        Vegetable veg = mock(Vegetable.class);

        when(veg.toString()).thenReturn("TestVeg");
        when(salad.getVegetables()).thenReturn(List.of(veg));

        ShowSaladCommand cmd = new ShowSaladCommand(salad);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        try {
            // WHEN
            cmd.execute();
        } finally {
            System.setOut(original);
        }

        // THEN
        String printed = out.toString();

        assertTrue(printed.contains("=== Склад салату ==="));
        assertTrue(printed.contains("1: TestVeg"));

        // getVegetables викликається двічі — не важливо скільки, лише що був
        verify(salad, atLeastOnce()).getVegetables();
    }
}
