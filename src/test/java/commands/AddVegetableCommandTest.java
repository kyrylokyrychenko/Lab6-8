package commands;

import org.junit.jupiter.api.Test;
import vegetables.Salad;
import vegetables.*;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddVegetableCommandTest {

    @Test
    void testCreateVegetableCarrot() {
        AddVegetableCommand cmd = new AddVegetableCommand(new Salad());
        Vegetable v = cmd.createVegetable("1", 100);

        assertTrue(v instanceof Carrot);
        assertEquals(100, v.getWeight());
    }

    @Test
    void testCreateVegetableTomato() {
        AddVegetableCommand cmd = new AddVegetableCommand(new Salad());
        Vegetable v = cmd.createVegetable("2", 50);

        assertTrue(v instanceof Tomato);
        assertEquals(50, v.getWeight());
    }

    @Test
    void testInvalidChoiceReturnsNull() {
        AddVegetableCommand cmd = new AddVegetableCommand(new Salad());
        Vegetable v = cmd.createVegetable("999", 100);

        assertNull(v);
    }

    @Test
    void testExecuteAddsCarrotToSalad() {
        // Емуляція введення користувача:
        // "1" (морква) + Enter + "200" + Enter
        String input = "1\n200\n";
        Scanner fakeScanner = new Scanner(input);

        Salad salad = new Salad();
        AddVegetableCommand cmd = new AddVegetableCommand(salad, fakeScanner);

        cmd.execute();

        assertEquals(1, salad.getVegetables().size());
        assertTrue(salad.getVegetables().get(0) instanceof Carrot);
        assertEquals(200, salad.getVegetables().get(0).getWeight());
    }

    @Test
    void testExecuteInvalidChoiceDoesNotAdd() {
        String input = "999\n150\n";
        Scanner fakeScanner = new Scanner(input);

        Salad salad = new Salad();
        AddVegetableCommand cmd = new AddVegetableCommand(salad, fakeScanner);

        cmd.execute();

        assertEquals(0, salad.getVegetables().size());
    }

    @Test
    void testGetDesc() {
        AddVegetableCommand cmd = new AddVegetableCommand(new Salad());
        assertEquals("Додати овоч до салату", cmd.getDesc());
    }

    @Test
    void testCreateVegetableCucumber() {
        AddVegetableCommand cmd = new AddVegetableCommand(new Salad());
        Vegetable v = cmd.createVegetable("3", 120);

        assertTrue(v instanceof Cucumber);
        assertEquals(120, v.getWeight());
    }

    @Test
    void testCreateVegetableOnion() {
        AddVegetableCommand cmd = new AddVegetableCommand(new Salad());
        Vegetable v = cmd.createVegetable("4", 80);

        assertTrue(v instanceof Onion);
        assertEquals(80, v.getWeight());
    }

    @Test
    void testCreateVegetableCabbage() {
        AddVegetableCommand cmd = new AddVegetableCommand(new Salad());
        Vegetable v = cmd.createVegetable("5", 250);

        assertTrue(v instanceof Cabbage);
        assertEquals(250, v.getWeight());
    }

    @Test
    void testCreateVegetablePepper() {
        AddVegetableCommand cmd = new AddVegetableCommand(new Salad());
        Vegetable v = cmd.createVegetable("6", 60);

        assertTrue(v instanceof Pepper);
        assertEquals(60, v.getWeight());
    }

}
