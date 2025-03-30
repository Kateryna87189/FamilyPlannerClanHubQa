package family_planner.rest_assured;

import family_planner.dto.AuthRequestDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class LoginTest extends TestBase {
    AuthRequestDto body = AuthRequestDto.builder()
            .email("katranchik21@gmail.com")
            .password("Password@1")
            .build();
    AuthRequestDto invalidPasswordBody = AuthRequestDto.builder()
            .email("katranchik21@gmail.com")
            .password("Password1")  // неправильний пароль
            .build();

    AuthRequestDto unregisteredEmailBody = AuthRequestDto.builder()
            .email("Kateryna2401@outlook.com")  // незареєстрований email
            .password("Password@1")
            .build();

    AuthRequestDto invalidEmailFormatBody = AuthRequestDto.builder()
            .email("katranchik21@.com")  // некоректний формат email
            .password("Password@1")
            .build();

    AuthRequestDto emptyEmailBody = AuthRequestDto.builder()
            .email("")  // порожній email
            .password("Password@1")
            .build();

    AuthRequestDto emptyPasswordBody = AuthRequestDto.builder()
            .email("katranchik21@gmail.com")
            .password("")  // порожній пароль
            .build();


    @Test
    public void loginPositiveTest() {
        String responseToken = given()
                .contentType(ContentType.JSON)
                .body(body).when()
                .post(loginDto)
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("token"))
                .extract().path("token");
        System.out.println("Response token: " + responseToken);
    }
    @Test
    public void loginWithIncorrectPasswordTest() {
        given()
                .contentType(ContentType.JSON)
                .body(invalidPasswordBody)
                .when()
                .post(loginDto)
                .then()
                .assertThat()
                .statusCode(401)
                .body(containsString("Bad credentials"));
    }
    @Test
    public void loginWithUnregisteredEmailTest() {
        given()
                .contentType(ContentType.JSON)
                .body(unregisteredEmailBody)
                .when()
                .post(loginDto)
                .then()
                .assertThat()
                .statusCode(401)
                .body(containsString("Bad credentials"));
    }
    @Test
    public void loginWithInvalidEmailFormatTest() {
        given()
                .contentType(ContentType.JSON)
                .body(invalidEmailFormatBody)
                .when()
                .post(loginDto)
                .then()
                .assertThat()
                .statusCode(400)
                .body(containsString("Invalid email format"));
    }
    @Test
    public void loginWithEmptyEmailTest() {
        given()
                .contentType(ContentType.JSON)
                .body(emptyEmailBody)
                .when()
                .post(loginDto)
                .then()
                .assertThat()
                .statusCode(400)
                .body(containsString("Email cannot be empty"));
    }
    @Test
    public void loginWithEmptyPasswordTest() {
        given()
                .contentType(ContentType.JSON)
                .body(emptyPasswordBody)
                .when()
                .post(loginDto)
                .then()
                .assertThat()
                .statusCode(400)
                .body(containsString("Password cannot be empty"));
    }
}