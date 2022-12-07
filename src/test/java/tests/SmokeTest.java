package tests;
import core.Automations;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static core.Automations.*;


public class SmokeTest {
    @Test
    public void verifySauceLabUserShoppingWorkflow(){
        // --- Test Data --- //
        String site = "https://www.saucedemo.com/";
        By loc_login_button = xp("//input[@id='login-button']");
        By loc_username = css("#user-name");
        By loc_password = css("#password");
        By loc_prod_filter = xp("//select");
        By loc_first_item_add_to_cart = css("#add-to-cart-sauce-labs-backpack");
        By loc_first_item_remove = css("#remove-sauce-labs-backpack");
        By loc_cart_item_number = xp("//span[@class='shopping_cart_badge']");
        By loc_second_item_add_to_cart = css("#add-to-cart-sauce-labs-bike-light");
        By loc_cart = css("a.shopping_cart_link");
        By loc_all_items_in_cart = xp("//a[contains(@id, 'item')]");
        By loc_continue_shopping = css("#continue-shopping");
        By loc_all_item_price = xp("//div[@class='inventory_item_price']");


        // --- Test Step --- //
        openBrowser();
        visit(site);
        String value = findElement(loc_login_button).getAttribute("value");
        Assert.assertTrue(value.contains("Login"));

        // 3. Login with credentials
        type(loc_username, "standard_user");
        type(loc_password, "secret_sauce");
        click(loc_login_button);

        Select dropdwon = new Select( findElement(loc_prod_filter) );
        List<WebElement> selectedOptions = dropdwon.getAllSelectedOptions();

        String defaultOption = selectedOptions.get(0).getText();
        Assert.assertTrue( defaultOption.contains("Name (A to Z)") );

        // step 5
        click(loc_first_item_add_to_cart);

        // step 6
        String itemCount = getText(loc_cart_item_number);
        Assert.assertTrue( itemCount.equals("1") );

        // step 7 & 8
        click(loc_second_item_add_to_cart);
        itemCount = getText(loc_cart_item_number);
        Assert.assertTrue( itemCount.equals("2") );

        // step 9 & 10
        click(loc_first_item_remove);
        itemCount = getText(loc_cart_item_number);
        Assert.assertTrue( itemCount.equals("1") );

        // step 11
        click(loc_cart);

        // step 12
        List<WebElement> items = findElements(loc_all_items_in_cart);
        String itemName = items.get(0).getText();

        Assert.assertTrue(items.size() == 1);
        Assert.assertTrue(itemName.equals("Sauce Labs Bike Light"));


        // step 13
        click(loc_continue_shopping);

        // step 14
        dropdwon = new Select( findElement(loc_prod_filter) );
        dropdwon.selectByValue("lohi");

        // step 15
        List<WebElement> sortedItem = findElements(loc_all_item_price);
        List<Double> sortedPrice = new ArrayList<>();

        //  01234
        // "$7.99" --> "7.99" --> 7.99
        for(WebElement e : sortedItem) {
            String price = e.getText();
            String corrected = price.substring(1);
            sortedPrice.add( Double.valueOf(corrected));
        }

        boolean ret = true;
        for(int i = 1; i < sortedPrice.size(); i++) {
            int prev = i - 1;
            boolean test = sortedPrice.get(i) >= sortedPrice.get(prev);
            if(test == false){
                ret = false;
                break;
            }
        }
        Assert.assertTrue(ret);
        closeBrowser();
    }


    @Test
    public void  verifyUserPurchaseWorkFlow(){

        //--- Test Data---//
        String site= "https://www.saucedemo.com/";
        By loc_login_button = xp("//input[@id='login-button']");
        By loc_username = css("#user-name");
        By loc_password = css("#password");

        By loc_first_product_title =xp("//div[@class='inventory_details_name]");
        By loc_first_product_price =xp("//div[@class='inventory_item_price']");

        By loc_second_product_title =xp("//div[@class='inventory_details_name']");
        By loc_second_product_price =xp("//div[@class='inventory_item_price']");

        By loc_third_product_title =xp("//div[@class='inventory_details_name']");
        By loc_third_product_price =xp("//div[@class='inventory_item_price']");

        By loc_fourth_product_title =xp("//div[(@class='inventory_details_name')]");
        By loc_fourth_product_price =xp("/div[@class='inventory_item_price']");

        By loc_fifth_product_title=xp("//div[@class='inventory_details_name')");
        By loc_fifth_product_price=xp("/div[@class='inventory_item_price']");

        By loc_sixth_product_title =xp("//div[(@class='inventory_details_name')]");
        By loc_sixth_product_price=xp("/div[@class='inventory_item_price']");

        By loc_click_third_item=xp("//div[@class='inventory_details_desc']");

        By loc_click_add_to_card_button=css("#button aad_to_cart");

        By loc_cart=css("#a.shopping_cart_link");

        By loc_checkout_button=xp("");

        By loc_click_continue_button=css("#continue.submit-button");

        By loc_total_amount=xp("//div[@class='summary.total-label']");

        By loc_text_displayed=xp("'//h2'");



        //--- Test Step---//
        openBrowser();
        visit("site");
        closeBrowser();
        String value = findElement(loc_login_button).getAttribute("value");
        Assert.assertTrue(value.contains("Login"));

        // 3. Login with credentials
        type(loc_username, "standard_user");
        type(loc_password, "secret_sauce");
        click(loc_login_button);

        //4. Click the third item verify item details text content
         click(loc_click_third_item);

      //5.Click add to cart button
        click(loc_click_add_to_card_button);

        //6.Go to your cart page by clicking the cart icon
          click(loc_cart);

      //7.Click checkout button
       click(loc_checkout_button);

     //8. Enter the personal information details and click Continue Button.

        click(loc_click_continue_button);

       //9.From the payment confirmation page,verify the total amount.

        WebElement totalAmount= waits.until(ExpectedConditions.visibilityOfElementLocated(loc_total_amount));
        String extractTotal = totalAmount.getText();
        Assert.assertTrue(extractTotal.contains("17.27"));
        getText(loc_total_amount);

      //11.Click Finish button and confirm the following text is displayed;
    }

}//end::class



