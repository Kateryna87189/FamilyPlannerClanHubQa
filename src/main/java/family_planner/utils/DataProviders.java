package family_planner.utils;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "validLoginData")
    public static Object[][] provideValidLoginData() {
        return new Object[][]{
                {"katranchik21@gmail.com", "Password@1"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public static Object[][] provideInvalidLoginData() {
        return new Object[][]{
                {"katranchik21@.com", "Password@1", "Please enter a valid email"},
                {"katranchik21@gmail.com", "Password1@23", "Incorrect password. Please try again."},
                {"", "Password@1", "Email is required"},
                {"katranchik21@gmail.com", "", "Password required"},
                {"usw999+2j804gyg7o78k@spam4.me", "SomePassword123@", "User not found"},
        };

    }
}
