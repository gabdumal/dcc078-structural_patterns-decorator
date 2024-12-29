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

public class BurgerTest {

    @Test
    void shouldCreateMealWithBurger() {
        var meal = new Burger(new Breads());

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var burgerAmount = meal.getAmountOfMeal(Burger.class);
        assertEquals(1, burgerAmount);

        var price = meal.getPrice();
        assertEquals(4.0d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Burger.class, 1);
        assertEquals(expectedItems, items);
    }

    @Test
    void shouldCreateMealWithTwoBurgers() {
        var meal = new Burger(new Burger(new Breads()));

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var burgerAmount = meal.getAmountOfMeal(Burger.class);
        assertEquals(2, burgerAmount);

        var price = meal.getPrice();
        assertEquals(7.0d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Burger.class, 2);
        assertEquals(expectedItems, items);
    }

    @Test
    void shouldCreateMealWithTreeBurgers() {
        var meal = new Burger(new Burger(new Burger(new Breads())));

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var burgerAmount = meal.getAmountOfMeal(Burger.class);
        assertEquals(3, burgerAmount);

        var price = meal.getPrice();
        assertEquals(10.0d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Burger.class, 3);
        assertEquals(expectedItems, items);
    }

    @Test
    void shouldNotCreateMealWithFourBurgers() {
        try {
            var meal = new Burger(new Burger(new Burger(new Breads())));

            var breadAmount = meal.getAmountOfMeal(Breads.class);
            assertEquals(1, breadAmount);

            var burgerAmount = meal.getAmountOfMeal(Burger.class);
            assertEquals(3, burgerAmount);

            var price = meal.getPrice();
            assertEquals(10.0d, price, 0.0001d);

            var items = meal.getItems();
            var expectedItems = new HashMap<Class<?>, Integer>();
            expectedItems.put(Breads.class, 1);
            expectedItems.put(Burger.class, 3);
            assertEquals(expectedItems, items);

            new Burger(meal);
            fail();
        }
        catch (MealIllegalArgumentException exception) {
            assertEquals("There must be at most 3 burgers", exception.getMessage());
        }
    }

}
