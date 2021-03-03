package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.openqa.selenium.Keys.*;

public class ShoppingCartPage {

    @FindBy(id = "search_query_top")
    private WebElement searchFormInput;

    @FindBy(xpath = "//a[@class='product_img_link']")
    private WebElement productImage;

    @FindBy(id = "quantity_wanted")
    private WebElement quantityInput;

    @FindBy(id = "group_1")
    private WebElement sizeSelect;

    @FindBy(xpath = "//a[@class='color_pick']")
    private List<WebElement> colorList;

    @FindBy(xpath = "//*[@id='add_to_cart']/button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@class='btn btn-default button button-medium']//span")
    private WebElement checkoutButton;

    @FindBy(xpath = "//*[@id='product_price_5_24_0']//span[@class='price special-price']")
    private WebElement priceAfterDiscount;

    @FindBy(xpath = "//span[@class='price-percent-reduction small']")
    private WebElement discount;

    @FindBy(xpath = "//span[@class='old-price']")
    private WebElement price;

    @FindBy(id = "total_product")
    private WebElement totalPriceOfProducts;

    @FindBy(id = "total_shipping")
    private WebElement totalShipping;

    @FindBy(id = "total_tax")
    private WebElement tax;

    @FindBy(id = "total_price")
    private WebElement totalPrice;

    @FindBy(name = "quantity_5_24_0_0")
    private WebElement quantity;

    @FindBy(id = "5_24_0_0")
    private WebElement deleteButton;

    @FindBy(xpath = "//td[@class='cart_total']//span[@class='price']")
    private List<WebElement> totalPriceList;

    public ShoppingCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String text) {
        searchFormInput.clear();
        searchFormInput.sendKeys(text);
        searchFormInput.sendKeys(ENTER);
    }

    public void goToProductDetails() {
        productImage.click();
    }

    public void chooseQuantity(String quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }

    public void chooseSize(int index) {
        Select dressSize = new Select(sizeSelect);
        dressSize.selectByIndex(index);
    }

    public void chooseColor(String name) {
        String colorName;

        for (int i = 0; i < colorList.size(); i++) {
            colorName = colorList.get(i).getAttribute("name");

            if (colorName.equals(name)) {
                colorList.get(i).click();
                break;
            }
        }
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void continueShopping() {
        continueShoppingButton.click();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public double getPriceAfterDiscount() {
        String priceAfterDiscountText = priceAfterDiscount.getText();

        priceAfterDiscountText = priceAfterDiscountText.replace("$", "");

        return Double.parseDouble(priceAfterDiscountText);
    }

    public double getPrice() {
        String priceText = price.getText();

        priceText = priceText.replace("$", "");

        return Double.parseDouble(priceText);
    }

    public double getDiscount() {
        double discount;
        String discountText = this.discount.getText();

        discountText = discountText.replace("%", "");
        discountText = discountText.replace("-", "");
        discount = Double.parseDouble(discountText) / 100;

        return discount;
    }

    public double calculatePriceAfterDiscount(double price, double discount) {
        double priceAfterDiscount;
        BigDecimal bd;

        priceAfterDiscount = price - (price * discount);
        bd = new BigDecimal(priceAfterDiscount).setScale(2, RoundingMode.HALF_UP);
        priceAfterDiscount = bd.doubleValue();

        return priceAfterDiscount;
    }

    public double getTotalPrice() {
        String totalPriceText = totalPrice.getText();

        totalPriceText = totalPriceText.replace("$", "");

        return Double.parseDouble(totalPriceText);
    }

    public double getTotalPriceOfProducts() {
        String totalPriceText = totalPriceOfProducts.getText();

        totalPriceText = totalPriceText.replace("$", "");

        return Double.parseDouble(totalPriceText);
    }

    public double getTotalShipping() {
        String totalShippingText = totalShipping.getText();

        totalShippingText = totalShippingText.replace("$", "");

        return Double.parseDouble(totalShippingText);
    }

    public double getTax() {
        String taxText = tax.getText();

        taxText = taxText.replace("$", "");

        return Double.parseDouble(taxText);
    }

    public double calculateTotalPrice(double totalPriceOfProducts, double totalShipping, double tax) {
        double totalPrice;
        BigDecimal bd;

        totalPrice = Double.parseDouble(String.valueOf(totalPriceOfProducts + totalShipping + tax));
        bd = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
        totalPrice = bd.doubleValue();

        return totalPrice;
    }

    public int getQuantity() {
        String quantityText = quantity.getAttribute("value");

        return Integer.parseInt(quantityText);
    }

    public double getProductTotalPrice(int index) {
        String productTotalPriceText = totalPriceList.get(index).getText();

        productTotalPriceText = productTotalPriceText.replace("$", "");

        return Double.parseDouble(productTotalPriceText);
    }

    public double calculateProductTotalPrice(double unitPriceOfDress, int quantityProduct) {
        BigDecimal bd;

        double priceForDresses = Double.parseDouble(String.valueOf(unitPriceOfDress * quantityProduct));
        bd = new BigDecimal(priceForDresses).setScale(2, RoundingMode.HALF_UP);
        priceForDresses = bd.doubleValue();

        return priceForDresses;
    }

    public void deleteProduct() {
        deleteButton.click();
    }

    public double calculateTotalPriceOfProducts() {
        double totalPrice = 0;
        BigDecimal bd;

        for (WebElement element : totalPriceList) {
            String elementTotalPriceText = element.getText();
            double elementTotalPrice;

            elementTotalPriceText = elementTotalPriceText.replace("$", "");
            elementTotalPrice = Double.parseDouble(elementTotalPriceText);
            totalPrice += elementTotalPrice;
        }

        bd = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
        totalPrice = bd.doubleValue();

        return totalPrice;
    }
}



