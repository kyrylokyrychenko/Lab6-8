package commands;

import org.junit.jupiter.api.Test;
import vegetables.Salad;

import static org.mockito.Mockito.*;

class SortVegetablesCommandTest {

    @Test
    void testSorting() {
        Salad salad = mock(Salad.class);

        SortVegetablesCommand cmd = new SortVegetablesCommand(salad);
        cmd.execute();

        verify(salad).sortByCalories();
    }

    @Test
    void testDescription() {
        Salad salad = mock(Salad.class);
        SortVegetablesCommand cmd = new SortVegetablesCommand(salad);

        assert cmd.getDesc().contains("Сортувати");
    }
}
