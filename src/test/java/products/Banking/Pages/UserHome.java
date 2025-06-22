package products.Banking.Pages;

import java.util.HashMap;

public class UserHome {

    public static HashMap<String, String> elementLocators = new HashMap<String, String>();

    public static void setElements()
    {
        elementLocators.put("deposit_button", "//button[contains(text(),'Deposit')]");
        elementLocators.put("transactions_button", "//button[contains(text(),'Transactions')]");
        elementLocators.put("withdrawl_button", "//button[contains(text(),'Withdrawl')]");
        elementLocators.put("", "");
    }

    public static String getElementSelector(String element_name)
    {
        String elementLocator = elementLocators.get(element_name);
        return elementLocator;
    }

}
