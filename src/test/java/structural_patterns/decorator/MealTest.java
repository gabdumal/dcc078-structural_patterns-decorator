/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package structural_patterns.decorator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealTest {

    @Test
    void shouldCreateMeal() {
        var meal = new Breads();
        var mealAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1.0d, meal.getPrice(), 0.0001d);
    }

    @Test
    void shouldCreateMealWithBurger() {
        var meal = new Burger(new Breads());
        var mealAmount = meal.getAmountOfMeal(Burger.class);
        assertEquals(4.0d, meal.getPrice(), 0.0001d);
    }

    @Test
    void shouldCreateMealWithTwoBurgers() {
        var meal = new Burger(new Burger(new Breads()));
        var mealAmount = meal.getAmountOfMeal(Burger.class);
        assertEquals(7.0d, meal.getPrice(), 0.0001d);
    }

}
