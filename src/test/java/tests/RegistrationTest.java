package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;

public class RegistrationTest extends BaseSeleniumTest {

    @Test
    public void registrationWithoutFirstNameTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goToSignInPage();
        registrationPage.enterEmail("testowanie_001@testowanie.com");
        registrationPage.submitSignUpForm();
        registrationPage.submitRegistrationForm();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("firstname is required."));
    }

    @Test
    public void registrationWithoutLastNameTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goToSignInPage();
        registrationPage.enterEmail("testowanie_001@testowanie.com");
        registrationPage.submitSignUpForm();
        registrationPage.enterFirstName("test");
        registrationPage.submitRegistrationForm();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("lastname is required."));
    }

    @Test
    public void registrationWithoutAddress1Test() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goToSignInPage();
        registrationPage.enterEmail("testowanie_001@testowanie.com");
        registrationPage.submitSignUpForm();
        registrationPage.enterFirstName("test");
        registrationPage.enterLastName("test");
        registrationPage.submitRegistrationForm();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("address1 is required."));
    }

    @Test
    public void registrationWithoutCityTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goToSignInPage();
        registrationPage.enterEmail("testowanie_001@testowanie.com");
        registrationPage.submitSignUpForm();
        registrationPage.enterFirstName("test");
        registrationPage.enterLastName("test");
        registrationPage.enterAddress("Black Street");
        registrationPage.submitRegistrationForm();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("city is required."));
    }

    @Test
    public void registrationWithoutPasswordTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goToSignInPage();
        registrationPage.enterEmail("testowanie_001@testowanie.com");
        registrationPage.submitSignUpForm();
        registrationPage.enterFirstName("test");
        registrationPage.enterLastName("test");
        registrationPage.enterAddress("Black Street");
        registrationPage.enterCity("Saint Paul");
        registrationPage.submitRegistrationForm();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("passwd is required."));
    }

    @Test
    public void registrationWithoutState() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goToSignInPage();
        registrationPage.enterEmail("testowanie_001@testowanie.com");
        registrationPage.submitSignUpForm();
        registrationPage.enterFirstName("test");
        registrationPage.enterLastName("test");
        registrationPage.enterAddress("Black Street");
        registrationPage.enterCity("Saint Paul");
        registrationPage.enterPassword("test.test12");
        registrationPage.submitRegistrationForm();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("This country requires you to choose a State."));
    }

    @Test
    public void registrationWithoutPostalCode () {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goToSignInPage();
        registrationPage.enterEmail("testowanie_001@testowanie.com");
        registrationPage.submitSignUpForm();
        registrationPage.enterFirstName("test");
        registrationPage.enterLastName("test");
        registrationPage.enterAddress("Black Street");
        registrationPage.enterCity("Saint Paul");
        registrationPage.enterPassword("test.test12");
        registrationPage.chooseState(24);
        registrationPage.submitRegistrationForm();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));
    }

    @Test
    public void registrationWithoutMobilePhone () {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goToSignInPage();
        registrationPage.enterEmail("testowanie_001@testowanie.com");
        registrationPage.submitSignUpForm();
        registrationPage.enterFirstName("test");
        registrationPage.enterLastName("test");
        registrationPage.enterAddress("Black Street");
        registrationPage.enterCity("Saint Paul");
        registrationPage.enterPassword("test.test12");
        registrationPage.chooseState(24);
        registrationPage.enterPostalCode("00000");
        registrationPage.submitRegistrationForm();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("You must register at least one phone number."));
    }
}


