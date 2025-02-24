/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package structural_patterns.decorator;

import java.util.HashMap;

public class Breads
        implements Meal {

    @Override
    public double getPrice() {
        return 1.0d;
    }

    @Override
    public HashMap<Class<?>, Integer> getItems() {
        var items = new HashMap<Class<?>, Integer>();
        items.put(Breads.class, 1);
        return items;
    }

}
