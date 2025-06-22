package products.Banking.Pages;

import java.util.HashMap;

public class Transactions {

    static HashMap<String, String> elementLocators = new HashMap<String, String>();

    public static void setElements()
    {

        elementLocators.put("transaction1_amount", "//table//tbody//tr[1]//td[2]");
        elementLocators.put("transaction2_amount", "//table//tbody//tr[2]//td[2]");
        elementLocators.put("transaction3_amount", "//table//tbody//tr[3]//td[2]");
        elementLocators.put("back_button", "//button[text()='Back']");


    }

    public static String getElementSelector(String element_name)
    {
        String elementLocator = elementLocators.get(element_name);
        return elementLocator;
    }

}
