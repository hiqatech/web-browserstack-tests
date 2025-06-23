package products.Banking.pages;

import java.util.HashMap;

public class Transactions {

    static HashMap<String, String> elementLocators = new HashMap<String, String>();

    public static void setElements()
    {

        elementLocators.put("transaction1_amount", "//table//tbody//tr[1]//td[2]");
        elementLocators.put("transaction2_amount", "//table//tbody//tr[2]//td[2]");
        elementLocators.put("transaction3_amount", "//table//tbody//tr[3]//td[2]");
        elementLocators.put("back_button", "//button[text()='Back']");
        elementLocators.put("reset_button", "//button[text()='Reset']");

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
