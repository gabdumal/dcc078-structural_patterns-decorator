/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package structural_patterns.decorator;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CheeseTest {

    @Test
    void shouldCreateMealWithCheeseSlice() {
        var meal = new Cheese(new Breads());

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var cheeseSlicesAmount = meal.getAmountOfMeal(Cheese.class);
        assertEquals(1, cheeseSlicesAmount);

        var price = meal.getPrice();
        assertEquals(2.5d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Cheese.class, 1);
        assertEquals(expectedItems, items);
    }

    @Test
    void shouldNotCreateMealWithFiveCheeseSlices() {
        try {
            var meal = new Cheese(new Cheese(new Cheese(new Cheese(new Breads()))));

            var breadAmount = meal.getAmountOfMeal(Breads.class);
            assertEquals(1, breadAmount);

            var cheeseSlicesAmount = meal.getAmountOfMeal(Cheese.class);
            assertEquals(4, cheeseSlicesAmount);

            var price = meal.getPrice();
            assertEquals(7.0d, price, 0.0001d);

            var items = meal.getItems();
            var expectedItems = new HashMap<Class<?>, Integer>();
            expectedItems.put(Breads.class, 1);
            expectedItems.put(Cheese.class, 4);
            assertEquals(expectedItems, items);

            new Cheese(meal);
            fail();
        }
        catch (MealIllegalArgumentException exception) {
            assertEquals("There must be at most 4 cheese slices", exception.getMessage());
        }
    }

    @Test
    void shouldCreateMealWithTwoBurgersAndThreeLettuceLeavesAndFourCheeseSlices() {
        var meal = new Lettuce(new Cheese(
                new Cheese(new Burger(new Lettuce(new Cheese(new Cheese(new Burger(new Lettuce(new Breads())))))))));

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var burgersAmount = meal.getAmountOfMeal(Burger.class);
        assertEquals(2, burgersAmount);

        var lettuceLeavesAmount = meal.getAmountOfMeal(Lettuce.class);
        assertEquals(3, lettuceLeavesAmount);

        var cheeseSlicesAmount = meal.getAmountOfMeal(Cheese.class);
        assertEquals(4, cheeseSlicesAmount);

        var price = meal.getPrice();
        assertEquals(14.5d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Burger.class, 2);
        expectedItems.put(Lettuce.class, 3);
        expectedItems.put(Cheese.class, 4);
        assertEquals(expectedItems, items);
    }

}
