package com.qacourse;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

class GitHubSofAssertionsTest {

    @Test
    void checkSoftAssertionSectionsOnSelenideWikiPage() {
        open("https://github.com/");

        $("[name=q]").setValue("selenide").pressEnter();

        $(By.linkText("selenide/selenide")).click();
        $(By.linkText("Wiki")).click();

        $(By.linkText("Soft assertions")).shouldBe(Condition.visible).click();
        $x("//ol[@start='3']").shouldBe(Condition.text("Using JUnit5 extend test class:"));
    }

    @Test
    void dragAndDropTask() {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        $("#column-a").dragAndDropTo("#column-b");

        $("#column-a").shouldHave(Condition.text("B"));
        $("#column-b").shouldHave(Condition.text("A"));
    }
}
