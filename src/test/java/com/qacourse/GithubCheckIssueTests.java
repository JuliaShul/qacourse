package com.qacourse;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.qacourse.steps.GithubSteps;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

class GithubCheckIssueTests {

    private static final String GITHUB_URL = "https://github.com/";
    private static final String REPOSITORY_NAME = "allure-framework/allure2";
    private static final String ISSUE_NUMBER_ID = "#issue_1264";
    private static final int ISSUE_NUMBER = 1264;
    private GithubSteps steps = new GithubSteps();

    @Test
    void verifyIssueIsVisible() {
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(false));
        open(GITHUB_URL);

        $(".header-search-input")
                .setValue(REPOSITORY_NAME)
                .pressEnter();

        $(By.linkText(REPOSITORY_NAME)).click();
        $(withText("Issues")).click();
        $(ISSUE_NUMBER_ID).should(Condition.visible);
    }

    @Test
    void verifyIssueIsVisibleAddingLambdaSteps() {
        step("Go to Github", (s) -> {
            s.parameter("url", GITHUB_URL);
            open(GITHUB_URL);
        });
        step("Search for the repository: " + REPOSITORY_NAME, (s) -> {
            s.parameter("Repository name", REPOSITORY_NAME);
            $(".header-search-input")
                    .setValue(REPOSITORY_NAME)
                    .pressEnter();
        });
        step("Go to the repository: " + REPOSITORY_NAME, (s) -> {
            s.parameter("Repositoty name", REPOSITORY_NAME);
            $(By.linkText(REPOSITORY_NAME)).click();
        });
        step("Go to the Issues section", () ->
                $(withText("Issues")).click()
        );
        step("Verify the issue with " + ISSUE_NUMBER_ID + " is visible",
                (s) -> {
                    s.parameter("issue number", ISSUE_NUMBER_ID);
                    $(ISSUE_NUMBER_ID).should(Condition.visible);
                });
    }

    @Test
    void verifyIssueIsVisibleAddingMethodSteps() {
        steps.openUrl(GITHUB_URL);
        steps.searchForRepository(REPOSITORY_NAME);
        steps.clickOnRepository(REPOSITORY_NAME);
        steps.goToIssuesSection();
        steps.findIssueByNumber(ISSUE_NUMBER)
                .should(Condition.visible);
    }
}

