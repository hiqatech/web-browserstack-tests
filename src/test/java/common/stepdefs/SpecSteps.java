package common.stepdefs;

import io.cucumber.java.en.Given;
import static common.stepdefs.WebSteps.*;
import static common.stepdefs.WebSteps.IActTheElement;

public class SpecSteps {

    @Given("I login with the {string} user")
    public static void login_with_user(String user){
        IAmOnThePage("Login");
        IActTheElement("select", "customer_login");
        ISelectTheElementBy("Ron Weasly","value","user_select");
        IActTheElement("select", "login_button");
    }
}
