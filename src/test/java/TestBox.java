import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestBox {
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Yuri");
        $("#lastName").setValue("Chudov");
        $("#userEmail").setValue("yuri@chudov.com");
        $(".custom-control-label").click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("August");
        $(".react-datepicker__year-select").selectOption("1987");
        $(".react-datepicker__day--0"+"04").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $("label[for='hobbies-checkbox-2']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/bg.jpg"));
        $("#currentAddress").setValue("Some address 1");
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        //$("#submit").click();
        executeJavaScript("document.getElementById('submit').click()");

        $(".table-responsive").shouldHave(
                text("Yuri Chudov"),
                text("yuri@chudov.com"),
                text("Male"),
                text("1234567890"),
                text("04 August,1987"),
                text("English"),
                text("Reading"),
                text("bg.jpg"),
                text("Some address 1"),
                text("NCR Delhi"));

    }
}
