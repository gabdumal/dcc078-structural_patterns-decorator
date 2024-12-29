/*
 * Copyright (c) 2024 Gabriel Malosto.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.gnu.org/licenses/agpl-3.0.txt>.
 */

package assignments.automotive;

import assignments.automotive.services.LoginService;
import assignments.automotive.services.ServiceError;
import assignments.automotive.services.ServiceFactory;
import assignments.automotive.services.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    void shouldGetStatusOfNotStartedFromLoginService() {
        var login = ServiceFactory.getService("Login");
        assertSame(Status.NOT_STARTED, login.getStatus());
    }

    @Test
    void shouldLogin() {
        var login = (LoginService) ServiceFactory.getService("Login");

        login.begin();
        login.informUsernameAndPassword("gabriel", "banana");
        login.run();
        var user = login.getUser();

        assertTrue(user.isLoggedIn() && login.getStatus() == Status.FINISHED);
    }

    @Test
    void shouldNotLoginBecauseUsernameAndPasswordHaveNotBeenInformed() {
        try {
            var login = (LoginService) ServiceFactory.getService("Login");

            login.begin();
            login.run();
            var user = login.getUser();
            fail();
        }
        catch (ServiceError exception) {
            assertEquals("The service has not finished", exception.getMessage());
        }
    }

    @Test
    void shouldNotLoginBecauseUsernameAndPasswordWereWrong() {
        try {
            var login = (LoginService) ServiceFactory.getService("Login");

            login.begin();
            login.informUsernameAndPassword("ana", "abacaxi");
            login.run();
            var user = login.getUser();
            fail();
        }
        catch (ServiceError exception) {
            assertEquals("The service has not finished", exception.getMessage());
        }
    }

}
