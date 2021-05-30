package com.qacourse;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

class StudentRegistrationFormTests {

    private RegistrationPage registrationPage = new RegistrationPage();
    private Faker faker = new Faker(Locale.GERMANY);

    @BeforeEach
    void openWebsite() {
        Configuration.browser = "chrome";
        registrationPage.openRegisterPage();
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

    @Test
    void registerStudentWithPageObject() {
        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                gender = "Male",
                email = faker.internet().emailAddress(),
                month = "August",
                year = "2000",
                number = faker.phoneNumber().subscriberNumber(10),
                address = faker.address().fullAddress();

        registrationPage.typeFirstname(firstName);
        registrationPage.typeLastname(lastName);
        registrationPage.typeUserEmail(email);
        registrationPage.chooseGender(gender);
        registrationPage.typeUserNumber(number);
        registrationPage.setDate(month, year);
        registrationPage.chooseHobby();
        registrationPage.typeAddress(address);
        registrationPage.clickSubmitButton();

        $(".table").shouldHave(text(firstName),
                text(lastName),
                text(email),
                text(gender),
                text(number),
                text("13 August,2000"), text(address));
    }
}
