package salad;

import org.junit.jupiter.api.Test;
import vegetables.Vegetable;
import vegetables.Salad;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaladTest {

    @Test
    void testAddVegetable() {
        Salad salad = new Salad();
        Vegetable veg = mock(Vegetable.class);

        salad.add(veg);

        assertEquals(1, salad.getVegetables().size());
    }

    @Test
    void testAddNullThrows() {
        Salad salad = new Salad();

        assertThrows(IllegalArgumentException.class, () -> salad.add(null));
    }

    @Test
    void testGetVegetablesIsUnmodifiable() {
        Salad salad = new Salad();
        salad.add(mock(Vegetable.class));

        List<Vegetable> list = salad.getVegetables();

        assertThrows(UnsupportedOperationException.class, () -> list.add(mock(Vegetable.class)));
    }

    @Test
    void testRemoveValidIndex() {
        Salad salad = new Salad();
        Vegetable veg = mock(Vegetable.class);

        salad.add(veg);
        salad.remove(0);

        assertTrue(salad.getVegetables().isEmpty());
    }

    @Test
    void testRemoveInvalidIndex() {
        Salad salad = new Salad();

        assertThrows(IndexOutOfBoundsException.class, () -> salad.remove(1));
    }

    @Test
    void testGetTotalCalories() {
        Salad salad = new Salad();

        Vegetable v1 = mock(Vegetable.class);
        Vegetable v2 = mock(Vegetable.class);

        when(v1.getTotalCalories()).thenReturn(30.0);
        when(v2.getTotalCalories()).thenReturn(100.0);

        salad.add(v1);
        salad.add(v2);

        assertEquals(130.0, salad.getTotalCalories());
    }

    @Test
    void testSortByCalories() {
        Salad salad = new Salad();

        Vegetable low = mock(Vegetable.class);
        Vegetable high = mock(Vegetable.class);

        when(low.getTotalCalories()).thenReturn(20.0);
        when(high.getTotalCalories()).thenReturn(50.0);

        salad.add(high);
        salad.add(low);

        salad.sortByCalories();

        assertEquals(low, salad.getVegetables().get(0));
    }

    @Test
    void testFindByCalories() {
        Salad salad = new Salad();

        Vegetable v1 = mock(Vegetable.class);
        Vegetable v2 = mock(Vegetable.class);

        when(v1.getTotalCalories()).thenReturn(30.0);
        when(v2.getTotalCalories()).thenReturn(80.0);

        salad.add(v1);
        salad.add(v2);

        List<Vegetable> result = salad.findByCalories(20, 60);

        assertEquals(1, result.size());
        assertEquals(v1, result.get(0));
    }
}
