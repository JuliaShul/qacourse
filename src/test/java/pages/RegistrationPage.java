package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            maleGenderRadioButton = $("#gender-radio-1").parent(),
            femaleGenderRadioButton = $("#gender-radio-2").parent(),
            userNumberInput = $("#userNumber"),
            addressInput = $("#currentAddress");

    public void typeFirstname(String firstName) {
        firstNameInput.setValue(firstName);
    }

    public void typeLastname(String lastName) {
        lastNameInput.setValue(lastName);
    }

    public void typeUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
    }

    public void chooseGender(String gender) {
        if ("Male".equals(gender)) {
            maleGenderRadioButton.click();
        } else if ("Female".equals(gender)) {
            femaleGenderRadioButton.click();
        } else if ("Other".equals(gender)) {
            throw new IllegalArgumentException("");
        }
    }

    public void typeUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
    }

    public void typeAddress(String address) {
        addressInput.setValue(address);
    }

    public void setDate(String month, String year) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--013").click();
    }

    public void chooseHobby() {
        $("#hobbies-checkbox-1").parent().click();
    }

    public void clickSubmitButton() {
        $("#submit").scrollTo().click();
    }
}
