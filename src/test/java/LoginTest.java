import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class LoginTest {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @BeforeAll
    static void setUpAll() {


    }

    @Test
    @DisplayName("Валидные логин и пароль, статус active")
    void dataValid() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id = login] input").setValue(user.getLogin());
        $("[data-test-id = password] input").setValue(user.getPassword());
        $("[data-test-id = action-login]").click();
        $("body div#root h2").shouldHave(text("Личный кабинет"));
    }

    @Test
    @DisplayName("Валидные логин и неверный пароль, статус active")
    void unValidPassword() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id = login] input").setValue(user.getLogin());
        $("[data-test-id = password] input").setValue(user.getPassword() + "password");
        $("[data-test-id = action-login]").click();
        $("[data-test-id = error-notification]").shouldHave(text("Ошибка"));
        $("[data-test-id = error-notification]").shouldHave(text("Неверно указан логин или пароль"));

    }

    @Test
    @DisplayName("Неверные логин и пароль, статус active")
    void dataUnValid() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id = login] input").setValue(user.getLogin() + "login");
        $("[data-test-id = password] input").setValue(user.getPassword() + "password");
        $("[data-test-id = action-login]").click();
        $("[data-test-id = error-notification]").shouldHave(text("Ошибка"));
        $("[data-test-id = error-notification]").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    @DisplayName("Валидные логин и пароль, статус blocked")
    void dataValidBlocked() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id = login] input").setValue(user.getLogin());
        $("[data-test-id = password] input").setValue(user.getPassword());
        $("[data-test-id = action-login]").click();
        $("[data-test-id = error-notification]").shouldHave(text("Ошибка"));
        $("[data-test-id = error-notification]").shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    @DisplayName("Валидной логин и неверный пароль, статус blocked")
    void dataUnValidPasswordBlocked() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id = login] input").setValue(user.getLogin());
        $("[data-test-id = password] input").setValue(user.getPassword() + "password");
        $("[data-test-id = action-login]").click();
        $("[data-test-id = error-notification]").shouldHave(text("Ошибка"));
        $("[data-test-id = error-notification]").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    @DisplayName("Неверные логин и пароль, статус blocked")
    void dataUnValidBlocked() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id = login] input").setValue(user.getLogin() + "login");
        $("[data-test-id = password] input").setValue(user.getPassword() + "password");
        $("[data-test-id = action-login]").click();
        $("[data-test-id = error-notification]").shouldHave(text("Ошибка"));
        $("[data-test-id = error-notification]").shouldHave(text("Неверно указан логин или пароль"));
    }
}