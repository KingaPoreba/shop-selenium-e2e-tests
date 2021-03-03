package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactPage;
import java.util.concurrent.TimeUnit;

public class ContactTest extends BaseSeleniumTest {

    @Test
    public void contactTest() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
        ContactPage contactPage = new ContactPage(driver);
        String errorMessageText;
        String successMessageText;

        contactPage.goToContactPage();
        contactPage.submitContactForm();
        errorMessageText = contactPage.getErrorMessageText();

        Assert.assertEquals("Invalid email address.", errorMessageText);

        contactPage.chooseSubjectHeading(1);
        contactPage.enterEmail("test");
        contactPage.submitContactForm();
        errorMessageText = contactPage.getErrorMessageText();

        Assert.assertEquals("Invalid email address.", errorMessageText);

        contactPage.enterEmail("testowanie@testowanie.com");
        contactPage.submitContactForm();
        errorMessageText = contactPage.getErrorMessageText();

        Assert.assertEquals("The message cannot be blank.", errorMessageText);

        contactPage.enterMessage("test");
        contactPage.submitContactForm();
        successMessageText = contactPage.getSuccessMessageText();

        Assert.assertEquals("Your message has been successfully sent to our team.", successMessageText);
    }
}

