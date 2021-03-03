package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class RegistrationPage {

    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInButton;

    @FindBy(id = "email_create")
    private WebElement emailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement signUpFormButton;

    @FindBy(id = "submitAccount")
    private WebElement registrationFormButton;

    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;

    @FindBy(id = "customer_lastname")
    private WebElement lastNameInput;

    @FindBy(id = "address1")
    private WebElement addressInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "id_state")
    private WebElement stateSelect;

    @FindBy(id = "postcode")
    private WebElement postalCodeInput;

    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneInput;

    @FindBy(xpath = "//div[@class='alert alert-danger']//ol//li")
    private List<WebElement> errorList;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void goToSignInPage() {
        signInButton.click();
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void submitSignUpForm() {
        signUpFormButton.click();
    }

    public void submitRegistrationForm() {
        registrationFormButton.click();
    }

    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void enterAddress(String address) {
        addressInput.sendKeys(address);
    }

    public void enterCity(String city) {
        cityInput.sendKeys(city);
    }

    public void chooseState(int index) {
        Select state = new Select(stateSelect);
        state.selectByIndex(index);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
    }

    public void enterMobilePhone(String mobilePhone) {
        mobilePhoneInput.sendKeys(mobilePhone);
    }

    public String findErrorMessageText(String errorMessage) {
        for (WebElement error : errorList) {
            String errorText = error.getText();

            if (errorText.equals(errorMessage)) {
                return errorText;
            }
        }

        return "";
    }
}
