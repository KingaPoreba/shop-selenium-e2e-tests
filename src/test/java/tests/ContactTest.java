package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactPage;

public class ContactTest extends BaseSeleniumTest {

    @Test
    public void contactFormTest() {
        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactPage();
        contactPage.chooseSubjectHeading("Customer service");
        contactPage.enterEmail("testowanie@testowanie.com");
        contactPage.enterMessage("test");
        contactPage.submitContactForm();
        String successMessageText = contactPage.getSuccessMessageText();
        Assert.assertEquals("Your message has been successfully sent to our team.", successMessageText);
    }

    @Test
    public void contactFormWithInvalidEmailTest() {
        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactPage();
        contactPage.submitContactForm();
        String errorMessageText = contactPage.getErrorMessageText();
        Assert.assertEquals("Invalid email address.", errorMessageText);
    }

    @Test
    public void contactFormWithBlankMessageTest() {
        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactPage();
        contactPage.chooseSubjectHeading("Customer service");
        contactPage.enterEmail("testowanie@testowanie.com");
        contactPage.submitContactForm();
        String errorMessageText = contactPage.getErrorMessageText();
        Assert.assertEquals("The message cannot be blank.", errorMessageText);
    }
}

