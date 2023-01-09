import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class LoginTest {

    @Test
    @DisplayName("Валидные логин и пароль, статус active")
    void dataValid() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id = login] input").setValue(user.getLogin());
        $("[data-test-id = password] input").setValue(user.getPassword());
        $("[data-test-id = action-login]").click();
        $("h2").shouldBe(exactText("Личный кабинет").visible);
    }

    @Test
    @DisplayName("Валидные логин и неверный пароль, статус active")
    void unValidPassword() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id = login] input").setValue(user.getLogin());
        $("[data-test-id = password] input").setValue(user.getPassword() + "password");
        $("[data-test-id = action-login]").click();
        $("[data-test-id = error-notification]").shouldBe(exactText("Ошибка").visible);
        $("[data-test-id = error-notification]").shouldBe(exactText("Неверно указан логин или пароль").visible);

    }

    @Test
    @DisplayName("Неверные логин и пароль, статус active")
    void dataUnValid() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id = login] input").setValue(user.getLogin() + "login");
        $("[data-test-id = password] input").setValue(user.getPassword() + "password");
        $("[data-test-id = action-login]").click();
        $("[data-test-id = error-notification]").shouldBe(exactText("Ошибка").visible);
        $("[data-test-id = error-notification]").shouldBe(exactText("Неверно указан логин или пароль").visible);
    }

    @Test
    @DisplayName("Валидные логин и пароль, статус blocked")
    void dataValidBlocked() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id = login] input").setValue(user.getLogin());
        $("[data-test-id = password] input").setValue(user.getPassword());
        $("[data-test-id = action-login]").click();
        $("[data-test-id = error-notification]").shouldBe(exactText("Ошибка").visible);
        $("[data-test-id = error-notification]").shouldBe(exactText("Пользователь заблокирован").visible);
    }

    @Test
    @DisplayName("Валидной логин и неверный пароль, статус blocked")
    void dataUnValidPasswordBlocked() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id = login] input").setValue(user.getLogin());
        $("[data-test-id = password] input").setValue(user.getPassword() + "password");
        $("[data-test-id = action-login]").click();
        $("[data-test-id = error-notification]").shouldBe(exactText("Ошибка").visible);;
        $("[data-test-id = error-notification]").shouldBe(exactText("Неверно указан логин или пароль").visible);;
    }

    @Test
    @DisplayName("Неверные логин и пароль, статус blocked")
    void dataUnValidBlocked() {
        open("http://localhost:9999/");
        val user = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id = login] input").setValue(user.getLogin() + "login");
        $("[data-test-id = password] input").setValue(user.getPassword() + "password");
        $("[data-test-id = action-login]").click();
        $("[data-test-id = error-notification]").shouldBe(exactText("Ошибка").visible);;
        $("[data-test-id = error-notification]").shouldBe(exactText("Неверно указан логин или пароль").visible);;
    }
}