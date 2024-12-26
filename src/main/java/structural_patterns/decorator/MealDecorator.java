/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package structural_patterns.decorator;

public abstract class MealDecorator
        implements Meal {

    private final String name;
    private final double price;
    private final Meal meal;

    public MealDecorator(Meal meal, String name, double price) {
        this.meal = meal;
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price + this.meal.getPrice();
    }

    @Override
    public String getItems() {
        return this.name + "\n" + this.meal.getItems();
    }

}
