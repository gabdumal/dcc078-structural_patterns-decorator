/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package structural_patterns.decorator;

public class Cheese
        extends MealDecorator {

    public Cheese(Meal meal) {
        super(meal, "Cheese", 1.5d);
        var cheeseSlicesAmount = this.getAmountOfMeal(Cheese.class);
        if (cheeseSlicesAmount > 4) {
            throw new MealIllegalArgumentException("There must be at most 4 cheese slices");
        }
    }

}
