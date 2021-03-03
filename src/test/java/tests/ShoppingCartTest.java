package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ShoppingCartPage;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ShoppingCartTest extends BaseSeleniumTest {

    @Test
    public void shoppingTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        shoppingCartPage.searchProduct("dress");
        shoppingCartPage.goToProductDetails();
        shoppingCartPage.chooseQuantity("4");
        shoppingCartPage.chooseSize(1);
        shoppingCartPage.chooseColor("Blue");
        shoppingCartPage.addToCart();
        shoppingCartPage.continueShopping();
        shoppingCartPage.searchProduct("t-shirt");
        shoppingCartPage.goToProductDetails();
        shoppingCartPage.addToCart();
        shoppingCartPage.continueShopping();
        shoppingCartPage.searchProduct("blouse");
        shoppingCartPage.goToProductDetails();
        shoppingCartPage.chooseColor("White");
        shoppingCartPage.addToCart();
        shoppingCartPage.proceedToCheckout();

        double priceAfterDiscount = shoppingCartPage.getPriceAfterDiscount();
        double price = shoppingCartPage.getPrice();
        double discount = shoppingCartPage.getDiscount();
        double expectedPrice = shoppingCartPage.calculatePriceAfterDiscount(price, discount);

        Assert.assertEquals(priceAfterDiscount, expectedPrice);

        double totalPrice = shoppingCartPage.getTotalPrice();
        double totalPriceOfProducts = shoppingCartPage.getTotalPriceOfProducts();
        double totalShipping = shoppingCartPage.getTotalShipping();
        double tax = shoppingCartPage.getTax();
        double expectedTotalPrice = shoppingCartPage.calculateTotalPrice(totalPriceOfProducts, totalShipping, tax);

        Assert.assertEquals(totalPrice, expectedTotalPrice);

        int quantity = shoppingCartPage.getQuantity();
        double productTotalPrice = shoppingCartPage.getProductTotalPrice(0);
        double expectedProductTotalPrice = shoppingCartPage.calculateProductTotalPrice(priceAfterDiscount, quantity);

        Assert.assertEquals(productTotalPrice, expectedProductTotalPrice);

        totalPriceOfProducts = shoppingCartPage.getTotalPriceOfProducts();
        double expectedTotalPriceOfProducts = shoppingCartPage.calculateTotalPriceOfProducts();

        Assert.assertEquals(totalPriceOfProducts, expectedTotalPriceOfProducts);

        shoppingCartPage.deleteProduct();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("product_5_24_0_0")));
        totalPriceOfProducts = shoppingCartPage.getTotalPriceOfProducts();
        expectedTotalPriceOfProducts = shoppingCartPage.calculateTotalPriceOfProducts();

        Assert.assertEquals(totalPriceOfProducts, expectedTotalPriceOfProducts);
    }
}