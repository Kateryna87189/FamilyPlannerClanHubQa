package family_planner.rest_assured;

import family_planner.dto.AuthRequestDto;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class LoginTest extends TestBase {

    @DataProvider(name = "loginTestData")
    public Object[][] loginTestData() {
        return new Object[][]{
                {AuthRequestDto.builder().email("katranchik21@gmail.com").password("Password@1").build(), 200, "token"},
                {AuthRequestDto.builder().email("katranchik21@gmail.com").password("Password1").build(), 401, "Bad credentials"},
                {AuthRequestDto.builder().email("Kateryna2401@outlook.com").password("Password@1").build(), 401, "Bad credentials"},
                {AuthRequestDto.builder().email("katranchik21@.com").password("Password@1").build(), 400, "Invalid email format"},
                {AuthRequestDto.builder().email("").password("Password@1").build(), 400, "Email cannot be empty"},
                {AuthRequestDto.builder().email("katranchik21@gmail.com").password("").build(), 400, "Password cannot be empty"}
        };
    }

    @Test(dataProvider = "loginTestData")
    public void loginTest(AuthRequestDto body, int expectedStatusCode, String expectedMessage) {
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(loginDto)
                .then()
                .log().ifValidationFails()
                .assertThat()
                .log().ifValidationFails()  // Лог тільки при фейлі
                .statusCode(expectedStatusCode)
                .body(containsString(expectedMessage));
    }
}