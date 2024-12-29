/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package structural_patterns.decorator;

import java.util.HashMap;

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
    public HashMap<Class<?>, Integer> getItems() {
        var items = this.meal.getItems();
        var mealAmount = items.getOrDefault(this.getClass(), 0);
        items.put(this.getClass(), mealAmount + 1);
        return items;
    }

    @Override
    public int getAmountOfMeal(Class<?> mealClass) {
        int amountOfSuchMeal = 0;
        if (mealClass.isInstance(this)) {
            amountOfSuchMeal += 1;
        }
        return amountOfSuchMeal + this.meal.getAmountOfMeal(mealClass);
    }

}
