package com.qacourse;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class StudentRegistrationFormTests {

    private static final String USER_REGISTRATION_FORM_URL = "https://demoqa.com/automation-practice-form";

    @BeforeAll
    static void openWebsite() {
        Configuration.browser = "chrome";
        open(USER_REGISTRATION_FORM_URL);
    }

    @Test
    void registerStudent() {
        String firstName = "Igor",
                lastName = "Petrov",
                gender = "Male",
                email = "IgorPetrov@mail.ru",
                month = "August",
                year = "2000",
                number = "7364612634",
                address = "Wall Street, 12";

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#gender-radio-1").parent().click();
        $("#userNumber").setValue(number);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--013").click();
        $("#hobbies-checkbox-1").parent().click();
        $("#currentAddress").setValue(address);
        $("#submit").scrollTo().click();

        $(".table").shouldHave(text(firstName), text(lastName), text(email), text(gender), text(number),
                text("13 August,2000"), text(address));
    }
}
