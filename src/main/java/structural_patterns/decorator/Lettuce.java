/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package structural_patterns.decorator;

public class Lettuce
        extends MealDecorator {

    public Lettuce(Meal meal) {
        super(meal, "Lettuce", 0.50d);
        var lettuceLeavesAmount = this.getAmountOfMeal(Lettuce.class);
        if (lettuceLeavesAmount > 5) {
            throw new MealIllegalArgumentException("There must be at most 5 lettuce leaves");
        }
    }

}
