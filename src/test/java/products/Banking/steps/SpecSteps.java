package products.Banking.steps;

import io.cucumber.java.en.Given;
import static common.stepdefs.WebSteps.*;
import static common.stepdefs.WebSteps.IActTheElement;

public class SpecSteps {

    @Given("I login with the {string} user")
    public static void login_with_user(String user){
        IAmOnThePage("Login");
        IActTheElement("select", "customer_login");
        ISelectTheElementBy(user,"text","user_select");
        IActTheElement("select", "login_button");
        IAmOnThePage("UserHome");
    }

    @Given("I navigate to the {string} page")
    public static void navigate_to_page(String page){
        IAmOnThePage("UserHome");
        IActTheElement("select", page.toLowerCase() + "_button");
        IAmOnThePage(page);
    }
}
