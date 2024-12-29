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

public class LettuceTest {

    @Test
    void shouldCreateMealWithLettuceLeaf() {
        var meal = new Lettuce(new Breads());

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var lettuceLeavesAmount = meal.getAmountOfMeal(Lettuce.class);
        assertEquals(1, lettuceLeavesAmount);

        var price = meal.getPrice();
        assertEquals(1.50d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Lettuce.class, 1);
        assertEquals(expectedItems, items);
    }

    @Test
    void shouldCreateMealWithThreeLettuceLeaves() {
        var meal = new Lettuce(new Lettuce(new Lettuce(new Breads())));

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var lettuceLeavesAmount = meal.getAmountOfMeal(Lettuce.class);
        assertEquals(3, lettuceLeavesAmount);

        var price = meal.getPrice();
        assertEquals(2.50d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Lettuce.class, 3);
        assertEquals(expectedItems, items);
    }

    @Test
    void shouldNotCreateMealWithSixLettuceLeaves() {
        try {
            var meal = new Lettuce(new Lettuce(new Lettuce(new Lettuce(new Lettuce(new Breads())))));

            var breadAmount = meal.getAmountOfMeal(Breads.class);
            assertEquals(1, breadAmount);

            var lettuceLeavesAmount = meal.getAmountOfMeal(Lettuce.class);
            assertEquals(5, lettuceLeavesAmount);

            var price = meal.getPrice();
            assertEquals(3.5d, price, 0.0001d);

            var items = meal.getItems();
            var expectedItems = new HashMap<Class<?>, Integer>();
            expectedItems.put(Breads.class, 1);
            expectedItems.put(Lettuce.class, 5);
            assertEquals(expectedItems, items);

            new Lettuce(meal);
            fail();
        }
        catch (MealIllegalArgumentException exception) {
            assertEquals("There must be at most 5 lettuce leaves", exception.getMessage());
        }
    }

    @Test
    void shouldCreateMealWithBurgerAndTwoLettuceLeaves() {
        var meal = new Lettuce(new Burger(new Lettuce(new Breads())));

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var burgersAmount = meal.getAmountOfMeal(Burger.class);
        assertEquals(1, burgersAmount);

        var lettuceLeavesAmount = meal.getAmountOfMeal(Lettuce.class);
        assertEquals(2, lettuceLeavesAmount);

        var price = meal.getPrice();
        assertEquals(5.0d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Burger.class, 1);
        expectedItems.put(Lettuce.class, 2);
        assertEquals(expectedItems, items);
    }

}
