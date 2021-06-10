package com.qacourse.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GithubSteps {

    @Step("Go to the {url}")
    public void openUrl(String url) {
        open(url);
    }

    @Step("Search for the repository {repositoryName}")
    public void searchForRepository(String repositoryName) {
        $(".header-search-input")
                .val(repositoryName)
                .pressEnter();
    }

    @Step("Search for the repository {repositoryName}")
    public void clickOnRepository(String repositoryName) {
        $(By.linkText(repositoryName)).click();
    }

    @Step("Go to the Issues section")
    public void goToIssuesSection() {
        $(withText("Issues")).click();
    }

    @Step("Find the issue by {issueNumber} ")
    public SelenideElement findIssueByNumber(int issueNumber) {
        String issueIdLocator = String.format("#issue_%s", issueNumber);
        return $(issueIdLocator);
    }
}
