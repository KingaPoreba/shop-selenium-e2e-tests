package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import java.util.concurrent.TimeUnit;

public class RegistrationTest extends BaseSeleniumTest {

    @Test
    public void registrationTest() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.goToSignInPage();
        registrationPage.enterEmail("testowanie@testowanie.com");
        registrationPage.submitSignUpForm();

        registrationPage.submitRegistrationForm();
        String errorMessageText = registrationPage.findErrorMessageText("firstname is required.");

        Assert.assertEquals(errorMessageText, "firstname is required.");

        registrationPage.enterFirstName("test");
        registrationPage.submitRegistrationForm();
        errorMessageText = registrationPage.findErrorMessageText("lastname is required.");

        Assert.assertEquals(errorMessageText, "lastname is required.");

        registrationPage.enterLastName("test");
        registrationPage.submitRegistrationForm();
        errorMessageText = registrationPage.findErrorMessageText("address1 is required.");

        Assert.assertEquals(errorMessageText, "address1 is required.");

        registrationPage.enterAddress("test 12");
        registrationPage.submitRegistrationForm();
        errorMessageText = registrationPage.findErrorMessageText("city is required.");

        Assert.assertEquals(errorMessageText, "city is required.");

        registrationPage.enterCity("test");
        registrationPage.submitRegistrationForm();
        errorMessageText = registrationPage.findErrorMessageText("This country requires you to choose a State.");

        Assert.assertEquals(errorMessageText, "This country requires you to choose a State.");

        registrationPage.chooseState(9);
        registrationPage.submitRegistrationForm();
        errorMessageText = registrationPage.findErrorMessageText("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");

        Assert.assertEquals(errorMessageText, "The Zip/Postal code you've entered is invalid. It must follow this format: 00000");

        registrationPage.enterPostalCode("12345");
        registrationPage.submitRegistrationForm();
        errorMessageText = registrationPage.findErrorMessageText("You must register at least one phone number.");

        Assert.assertEquals(errorMessageText, "You must register at least one phone number.");

        registrationPage.enterMobilePhone("123456789");
        registrationPage.submitRegistrationForm();
        errorMessageText = registrationPage.findErrorMessageText("passwd is required.");

        Assert.assertEquals(errorMessageText, "passwd is required.");
    }
}


