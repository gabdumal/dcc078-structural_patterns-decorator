/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package structural_patterns.decorator;

public class Burger
        extends MealDecorator {

    public Burger(Meal meal) {
        super(meal, "Burger", 3.0d);
        var tofuBlocksAmount = this.getAmountOfMeal(Tofu.class);
        if (tofuBlocksAmount > 0) {
            throw new MealIllegalArgumentException("A single meal cannot have both tofu blocks and burgers");
        }

        var burgersAmount = this.getAmountOfMeal(Burger.class);
        if (burgersAmount > 3) {
            throw new MealIllegalArgumentException("There must be at most 3 burgers");
        }
    }

}
