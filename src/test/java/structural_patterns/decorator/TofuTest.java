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

public class TofuTest {

    @Test
    void shouldCreateMealWithTofuBlock() {
        var meal = new Tofu(new Breads());

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var tofuBlocksAmount = meal.getAmountOfMeal(Tofu.class);
        assertEquals(1, tofuBlocksAmount);

        var price = meal.getPrice();
        assertEquals(3.0d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Tofu.class, 1);
        assertEquals(expectedItems, items);
    }

    @Test
    void shouldNotCreateMealWithFourTofuBlocks() {
        try {
            var meal = new Tofu(new Tofu(new Tofu(new Breads())));

            var breadAmount = meal.getAmountOfMeal(Breads.class);
            assertEquals(1, breadAmount);

            var tofuBlocksAmount = meal.getAmountOfMeal(Tofu.class);
            assertEquals(3, tofuBlocksAmount);

            var price = meal.getPrice();
            assertEquals(7.0d, price, 0.0001d);

            var items = meal.getItems();
            var expectedItems = new HashMap<Class<?>, Integer>();
            expectedItems.put(Breads.class, 1);
            expectedItems.put(Tofu.class, 3);
            assertEquals(expectedItems, items);

            new Tofu(meal);
            fail();
        }
        catch (MealIllegalArgumentException exception) {
            assertEquals("There must be at most 3 tofu blocks", exception.getMessage());
        }
    }

    @Test
    void shouldNotCreateMealWithTofuBlockAndBurger() {
        try {
            var meal = new Tofu(new Breads());

            var breadAmount = meal.getAmountOfMeal(Breads.class);
            assertEquals(1, breadAmount);

            var tofuBlocksAmount = meal.getAmountOfMeal(Tofu.class);
            assertEquals(1, tofuBlocksAmount);

            var price = meal.getPrice();
            assertEquals(3.0d, price, 0.0001d);

            var items = meal.getItems();
            var expectedItems = new HashMap<Class<?>, Integer>();
            expectedItems.put(Breads.class, 1);
            expectedItems.put(Tofu.class, 1);
            assertEquals(expectedItems, items);

            new Burger(meal);
            fail();
        }
        catch (MealIllegalArgumentException exception) {
            assertEquals("A single meal cannot have both tofu blocks and burgers", exception.getMessage());
        }
    }

}
