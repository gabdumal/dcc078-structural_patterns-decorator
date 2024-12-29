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

public class BarbecueSauceTest {

    @Test
    void shouldCreateMealWithBurgerAndBarbecueSauceDollop() {
        var meal = new BarbecueSauce(new Burger(new Breads()));

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var burgersAmount = meal.getAmountOfMeal(Burger.class);
        assertEquals(1, burgersAmount);

        var barbecueSauceDollopsAmount = meal.getAmountOfMeal(BarbecueSauce.class);
        assertEquals(1, barbecueSauceDollopsAmount);

        var price = meal.getPrice();
        assertEquals(4.25d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Burger.class, 1);
        expectedItems.put(BarbecueSauce.class, 1);
        assertEquals(expectedItems, items);
    }

    @Test
    void shouldCreateMealWithTwoBurgersAndBarbecueSauceDollop() {
        var meal = new Burger(new BarbecueSauce(new Burger(new Breads())));

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var burgersAmount = meal.getAmountOfMeal(Burger.class);
        assertEquals(2, burgersAmount);

        var barbecueSauceDollopsAmount = meal.getAmountOfMeal(BarbecueSauce.class);
        assertEquals(1, barbecueSauceDollopsAmount);

        var price = meal.getPrice();
        assertEquals(7.25d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        expectedItems.put(Burger.class, 2);
        expectedItems.put(BarbecueSauce.class, 1);
        assertEquals(expectedItems, items);
    }

    @Test
    void shouldNotCreateMealWithBurgerAndTwoBarbecueSauceDollops() {
        try {
            var meal = new BarbecueSauce(new Burger(new Breads()));

            var breadAmount = meal.getAmountOfMeal(Breads.class);
            assertEquals(1, breadAmount);

            var burgersAmount = meal.getAmountOfMeal(Burger.class);
            assertEquals(1, burgersAmount);

            var barbecueSauceDollopsAmount = meal.getAmountOfMeal(BarbecueSauce.class);
            assertEquals(1, barbecueSauceDollopsAmount);

            var price = meal.getPrice();
            assertEquals(4.25d, price, 0.0001d);

            var items = meal.getItems();
            var expectedItems = new HashMap<Class<?>, Integer>();
            expectedItems.put(Breads.class, 1);
            expectedItems.put(Burger.class, 1);
            expectedItems.put(BarbecueSauce.class, 1);
            assertEquals(expectedItems, items);

            new BarbecueSauce(meal);
            fail();
        }
        catch (MealIllegalArgumentException exception) {
            assertEquals("There must be at most 1 barbecue sauce dollop", exception.getMessage());
        }
    }

    @Test
    void shouldNotCreateMealWithBarbecueSauceDollopWithoutBurger() {
        try {
            new BarbecueSauce(new Breads());
            fail();
        }
        catch (MealIllegalArgumentException exception) {
            assertEquals("There must be at least 1 burger to add barbecue sauce", exception.getMessage());
        }
    }

}
