package products.Banking.Pages;

import java.util.HashMap;

public class Home {

    static HashMap<String, String> elementLocators = new HashMap<String, String>();

    public static void setElements()
    {
        elementLocators.put("", "");
    }

    public static String getElementSelector(String element_name)
    {
        String elementLocator = elementLocators.get(element_name);
        return elementLocator;
    }

}
