package products.GitHub.Pages;

import java.util.HashMap;

public class HomePage {

  public static HashMap<String, String> elementLocators = new HashMap<String, String>();

  public static void setElements()
  {
    elementLocators.put("sing_in_button", "//*[@href=\"/login\"]");
    elementLocators.put("email_field", "//input[@id=\"login_field\"]");
    elementLocators.put("password_field", "//input[@id=\"password\"]");
    elementLocators.put("login_button", "//input[@type=\"submit\"]");
    elementLocators.put("otp_number_field", "//input[@id=\"otp\"]");
    elementLocators.put("otp_number_submit_button", "//button[@type=\"submit\"]");
    elementLocators.put("", "");

  }

  public static String getElementSelector(String element_name)
  {
    setElements();
    String elementLocator = elementLocators.get(element_name);
    return elementLocator;
  }
}
