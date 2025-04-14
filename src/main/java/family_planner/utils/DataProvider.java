package family_planner.utils;

import family_planner.dto.AuthRequestDto;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "validLoginData")
    public static Object[][] provideValidLoginData() {
        return new Object[][]{
                {"katranchik21@gmail.com", "Password@1"}
        };
    }

    @org.testng.annotations.DataProvider(name = "invalidLoginData")
    public static Object[][] provideInvalidLoginData() {
        return new Object[][]{
                {"katranchik21@.com", "Password@1", "Please enter a valid email"},
                {"katranchik21@gmail.com", "Password1@23", "Incorrect password. Please try again."},
                {"", "Password@1", "Email is required"},
                {"katranchik21@gmail.com", "", "Password required"},
                {"usw999+2j804gyg7o78k@spam4.me", "SomePassword123@", "User not found"},
        };

    }

    @org.testng.annotations.DataProvider(name = "loginTestData")
    public Object[][] loginTestData() {
        return new Object[][]{
                {AuthRequestDto.builder().email("katranchik21@gmail.com").password("Password@1").build(), 200, "token"},
                {AuthRequestDto.builder().email("katranchik21@gmail.com").password("Password1").build(), 400, "Invalid email or password"},
                {AuthRequestDto.builder().email("Kateryna2401@outlook.com").password("Password@1").build(), 401, "Email not found"},
                {AuthRequestDto.builder().email("katranchik21@.com").password("Password@1").build(), 400, "Email must be a valid email address with a proper domain"},
                {AuthRequestDto.builder().password("Password@1").build(), 400, "Email cannot be empty"},
                {AuthRequestDto.builder().email("katranchik21@gmail.com").password("").build(), 400, "Password cannot be empty"}
        };
    }
}
