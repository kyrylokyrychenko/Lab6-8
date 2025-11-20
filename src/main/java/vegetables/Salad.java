package vegetables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Salad {

    private final List<Vegetable> vegetables = new ArrayList<>();

    public void add(Vegetable v) {
        if (v == null) {
            throw new IllegalArgumentException("Овоч не може бути null.");
        }
        vegetables.add(v);
    }

    public List<Vegetable> getVegetables() {
        return Collections.unmodifiableList(vegetables);
    }

    public void remove(int index) {
        validateIndex(index);
        vegetables.remove(index);
    }

    public double getTotalCalories() {
        return vegetables.stream()
                .mapToDouble(Vegetable::getTotalCalories)
                .sum();
    }

    public void sortByCalories() {
        vegetables.sort(Comparator.comparing(Vegetable::getTotalCalories));
    }

    public List<Vegetable> findByCalories(double min, double max) {
        return vegetables.stream()
                .filter(v -> {
                    double c = v.getTotalCalories();
                    return c >= min && c <= max;
                })
                .toList();
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= vegetables.size()) {
            throw new IndexOutOfBoundsException("Некоректний індекс овоча.");
        }
    }
}
