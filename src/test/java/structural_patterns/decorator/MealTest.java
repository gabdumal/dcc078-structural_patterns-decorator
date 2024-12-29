/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package structural_patterns.decorator;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealTest {

    @Test
    void shouldCreateMeal() {
        var meal = new Breads();

        var breadAmount = meal.getAmountOfMeal(Breads.class);
        assertEquals(1, breadAmount);

        var price = meal.getPrice();
        assertEquals(1.0d, price, 0.0001d);

        var items = meal.getItems();
        var expectedItems = new HashMap<Class<?>, Integer>();
        expectedItems.put(Breads.class, 1);
        assertEquals(expectedItems, items);
    }

}
