package products.Banking.Pages;

import io.cucumber.java.en.Given;

import java.util.HashMap;

import static common.stepdefs.WebSteps.*;

public class Login {

    static HashMap<String, String> elementLocators = new HashMap<String, String>();

    public static void setElements()
    {
        elementLocators.put("customer_login", "//button[text()='Customer Login']");
        elementLocators.put("bank_manager_login", "//button[text()='Bank Manager Login']");
        elementLocators.put("user_select", "//select[@id='userSelect']");
        elementLocators.put("login_button", "//button[text()='Login']");
        elementLocators.put("", "");
    }

    public static String getElementSelector(String element_name)
    {
        String elementLocator = elementLocators.get(element_name);
        return elementLocator;
    }



}
