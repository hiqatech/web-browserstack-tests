package products.Banking.pages;

import java.util.HashMap;

public class UserHome {

    public static HashMap<String, String> elementLocators = new HashMap<String, String>();

    public static void setElements()
    {
        elementLocators.put("deposit_button", "//button[contains(text(),'Deposit')]");
        elementLocators.put("transactions_button", "//button[contains(text(),'Transactions')]");
        elementLocators.put("withdrawl_button", "//button[contains(text(),'Withdrawl')]");

        elementLocators.put("account_number", "//*[text()='Account Number : ']//following::strong[1]");
        elementLocators.put("balance", "//*[text()='Account Number : ']//following::strong[2]");
        elementLocators.put("currency", "//*[text()='Account Number : ']//following::strong[3]");

        elementLocators.put("home_button", "//button[text()='Home']");
        elementLocators.put("logout_button", "//button[text()='Logout']");
        elementLocators.put("", "");
    }

    public static String getElementSelector(String element_name)
    {
        String elementLocator = elementLocators.get(element_name);
        return elementLocator;
    }

}
