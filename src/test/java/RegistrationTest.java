/* Импорт  класса Configuration из библиотеки Selenide.
 Он используется для настройки окружения теста, например, указания базового URL и размера браузера. */
import com.codeborne.selenide.Configuration;
//--------------------------------------------------------------

/* Импорт аннотаций из JUnit Jupiter:
BeforeAll - Позволяет выполнить метод один раз перед всеми тестами в классе.
Test - Помечает метод как тестовый.*/
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//--------------------------------------------------------------

/* Импорт статического метода "appear" из класса Condition библиотеки Selenide.
Метод позволяет проверить, что элемент появился на странице.*/
import static com.codeborne.selenide.Condition.appear;
//--------------------------------------------------------------

/* Импорт статического метода "text" из класса Condition.
Метод используется для проверки наличия текста на элементе.*/
import static com.codeborne.selenide.Condition.text;
//--------------------------------------------------------------

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

/* Объявление класса RegistrationTest, который содержит тесты для регистрации.*/
public class RegistrationTest {
//--------------------------------------------------------------
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillRegistrationFormTest() {
        open("/automation-practice-form");

        // Удаление рекламных банеров.
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        // Заполнение регистрационной формы.
        $("#firstName").setValue("TestFirstName");
        $("#lastName").setValue("TestLastName");
        $("#userEmail").setValue("test@gmail.com");

        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").click();
        $("select.react-datepicker__month-select").selectOptionContainingText("May");
        $(".react-datepicker__year-select").selectOptionContainingText("1999");
        $(".react-datepicker__day--013").click();

        $("#subjectsContainer input").setValue("Physics").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("Screenshot.png");

        $("#currentAddress").setValue("75 PARK PLACE 8TH FLOOR NEW YORK NY 10007");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();

        $("#submit").click();


/*Ассерты: После заполнения формы, тест проверяет, что на странице появился модальный элемент,
а затем проверяет, что значения введенных данных соответствуют ожидаемым значениям.
Например, проверяет, что имя и фамилия равны "TestFirstName TestLastName", и т.д.*/
        $(".modal-content").should(appear);
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("TestFirstName TestLastName"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("test@gmail.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("Date of Birth 13 May,1999"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Physics"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Sports"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("Screenshot.png"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("75 PARK PLACE 8TH FLOOR NEW YORK NY 10007"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Noida"));
//--------------------------------------------------------------
    }
}




