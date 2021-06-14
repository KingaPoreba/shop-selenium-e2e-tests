package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactPage {

    @FindBy(id = "contact-link")
    private WebElement contactButton;

    @FindBy(id = "submitMessage")
    private WebElement contactFormButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    private WebElement errorMessage;

    @FindBy(id = "id_contact")
    private WebElement subjectHeadingSelect;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "message")
    private WebElement messageTextarea;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    private WebElement successMessage;

    public ContactPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void goToContactPage() {
        contactButton.click();
    }

    public void chooseSubjectHeading(String text) {
        Select subjectHeading = new Select(subjectHeadingSelect);
        subjectHeading.selectByVisibleText(text);
    }

    public void submitContactForm() {
        contactFormButton.click();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterMessage(String message) {
        messageTextarea.sendKeys(message);
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }
}