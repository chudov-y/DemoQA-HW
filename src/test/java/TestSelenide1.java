import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestSelenide1 {

    String JUnit5Class = "@ExtendWith({SoftAssertsExtension.class})\n" +
                         "class Tests {\n" +
                         "  @Test\n" +
                         "  void test() {\n" +
                         "    Configuration.assertionMode = SOFT;\n" +
                         "    open(\"page.html\");\n" +
                         "\n" +
                         "    $(\"#first\").should(visible).click();\n" +
                         "    $(\"#second\").should(visible).click();\n" +
                         "  }\n" +
                         "}";

    @BeforeAll
    static void BeforeAll() {
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void SelenideWikiJunit5(){
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-box").$(withText("Show")).click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $(".markdown-body").$("#user-content-3-using-junit5-extend-test-class")
                .closest("h4").sibling(0).shouldHave(text(JUnit5Class));

    }
}
