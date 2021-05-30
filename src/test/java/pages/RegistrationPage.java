package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    private static final String USER_REGISTRATION_FORM_URL = "https://demoqa.com/automation-practice-form";

    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
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
        String selectedGender = String.format("//input[@value='%s']", gender);
        $x(selectedGender).parent().click();
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

    public void openRegisterPage() {
        open(USER_REGISTRATION_FORM_URL);
    }
}
