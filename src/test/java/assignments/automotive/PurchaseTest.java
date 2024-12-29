/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package assignments.automotive;

import assignments.automotive.services.PurchaseService;
import assignments.automotive.services.ServiceError;
import assignments.automotive.services.ServiceFactory;
import assignments.automotive.services.Status;
import assignments.automotive.vehicle.Engine.EngineType;
import assignments.automotive.vehicle.Model.ModelType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {

    @Test
    void shouldGetStatusOfNotStartedFromPurchaseService() {
        var purchase = ServiceFactory.getService("Purchase");
        assertSame(Status.NOT_STARTED, purchase.getStatus());
    }

    @Test
    void shouldBuySedanWithGasoline() {
        var purchase = (PurchaseService) ServiceFactory.getService("Purchase");

        purchase.begin();
        purchase.informModelAndEngine(ModelType.SEDAN, EngineType.GASOLINE);
        purchase.run();
        var finalCost = purchase.calculateFinalCost();

        assertEquals(96750.0d, finalCost, 0.0001d);
    }

    @Test
    void shouldBuyHatchbackWithElectricWithThreeDoors() {
        var purchase = (PurchaseService) ServiceFactory.getService("Purchase");

        purchase.begin();
        purchase.informModelAndEngine(ModelType.HATCHBACK, EngineType.ELECTRIC);
        purchase.informHatchbackDoorsAmount(3);
        purchase.run();
        var finalCost = purchase.calculateFinalCost();

        assertEquals(91000.0d, finalCost, 0.0001d);
    }

    @Test
    void shouldNotBuyHatchbackIfHasInformedAboutCover() {
        try {
            var purchase = (PurchaseService) ServiceFactory.getService("Purchase");

            purchase.begin();
            purchase.informModelAndEngine(ModelType.HATCHBACK, EngineType.ELECTRIC);
            purchase.informIfPickupHasCover(true);
            purchase.run();
            var finalCost = purchase.calculateFinalCost();
            fail();
        }
        catch (RuntimeException exception) {
            assertEquals("The Model is not a Pickup", exception.getMessage());
        }
    }

    @Test
    void shouldNotBuyBecauseModelAndEngineHaveNotBeenInformed() {
        try {
            var purchase = (PurchaseService) ServiceFactory.getService("Purchase");

            purchase.begin();
            purchase.run();
            var finalCost = purchase.calculateFinalCost();
            fail();
        }
        catch (ServiceError exception) {
            assertEquals("The service has not finished", exception.getMessage());
        }
    }

    @Test
    void shouldNotBuyBecauseEngineDoesNotExist() {
        try {
            var purchase = (PurchaseService) ServiceFactory.getService("Purchase");

            purchase.begin();
            purchase.informModelAndEngine(ModelType.HATCHBACK, null);
            purchase.run();
            var finalCost = purchase.calculateFinalCost();
            fail();
        }
        catch (RuntimeException exception) {
            assertEquals("This engine does not exist", exception.getMessage());
        }
    }

    @Test
    void shouldNotBuyBecauseModelDoesNotExist() {
        try {
            var purchase = (PurchaseService) ServiceFactory.getService("Purchase");

            purchase.begin();
            purchase.informModelAndEngine(null, EngineType.DIESEL);
            purchase.run();
            var finalCost = purchase.calculateFinalCost();
            fail();
        }
        catch (RuntimeException exception) {
            assertEquals("This model does not exist", exception.getMessage());
        }
    }

}
