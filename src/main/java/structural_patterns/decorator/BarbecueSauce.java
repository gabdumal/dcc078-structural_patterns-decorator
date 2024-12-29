/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package structural_patterns.decorator;

public class BarbecueSauce
        extends MealDecorator {

    public BarbecueSauce(Meal meal) {
        super(meal, "Barbecue sauce", 0.25d);
        var burgersAmount = this.getAmountOfMeal(Burger.class);
        if (burgersAmount < 1) {
            throw new MealIllegalArgumentException("There must be at least 1 burger to add barbecue sauce");
        }

        var barbecueSauceDollopsAmount = this.getAmountOfMeal(BarbecueSauce.class);
        if (barbecueSauceDollopsAmount > 1) {
            throw new MealIllegalArgumentException("There must be at most 1 barbecue sauce dollop");
        }
    }

}
