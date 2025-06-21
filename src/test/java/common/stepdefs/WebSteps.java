package common.stepdefs;

import common.selenium.WebActs;
import common.setup.AllProducts;
import io.cucumber.java.en.Given;
import static common.setup.Hooks.*;

public class WebSteps extends WebActs {

    @Given("I start the {string} driver")
    public static void IStartTheWebDriver(String driver) {
        AssertExecutedStep(startWebDriver(driver));
    }

    @Given("I stop the webdriver")
    public static void IStopTheWebDriver() {
        AssertExecutedStep(WebActs.stopWebDriver());
    }

    @Given("I navigate to the {string} url")
    public static void INavigateToTheUrl(String url) {
        AssertExecutedStep(navigateToUrl(url));
    }

    @Given("I navigate to the Home page")
    public static void INavigateToTheHomePage()
    { AssertExecutedStep(startWebDriver("default"));
        AssertExecutedStep(navigateToHomePage(System.getProperty("mainURL")));
        AssertExecutedStep(onThePage("Home"));
    }

    @Given("I navigate to the {string} element link")
    public static void INavigateToTheElementLink(String elementName) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(navigateToTheElementLink(elementName, AllProducts.getElementSelector(elementName)));
    }

    @Given("I am on the {string} page")
    public static void IAmOnThePage(String pageName) {
        AssertExecutedStep(onThePage(pageName));
    }

    @Given("I switch to the {string}")
    public static void ISwitchToTheWindow(int windowNumber) {
        AssertExecutedStep(WebActs.switchToWindow(windowNumber));
    }

    @Given("I switch to the {string} content")
    public static void ISwitchToTheFrameContent(String frameName) {
        AssertExecutedStep(WebActs.switchToFrameContent(frameName, AllProducts.getElementSelector(frameName)));
    }

    @Given("I {string} the {string}")
    public static void IActTheElement(String act, String elementName) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(actTheElement(act, elementName, AllProducts.getElementSelector(elementName)));
    }

    @Given("I click the {string}")
    public static void IActTheBrowser(String act) {
        AssertExecutedStep(browserAction(act));
    }

    @Given("I send enter keys to the popup window")
    public static void ISendEnterToWindow() {
        AssertExecutedStep(sendEnterToWindow());
    }

    @Given("The {string} element status should be {string}")
    public static void TheElementStatusShouldBe(String elementName, String status) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(elementStatusShouldBe(elementName, AllProducts.getElementSelector(elementName), status));
    }

    @Given("I should find the {string} in the downloads")
    public static void ICheckDownloads(String expectedFileName) {
        AssertExecutedStep(checkDownloads(expectedFileName));
    }

    @Given("I select the {string} {string} from the {string} dropdown")
    public static void ISelectFromDropDownBy(String text, String attribute, String elementName) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(WebActs.selectFromDropDownBy(text, attribute, elementName, AllProducts.getElementSelector(elementName)));
    }

    @Given("I click the {string} {string} from the {string} options of the {string} dropdown")
    public static void IClickFromDropDownBy(String text, String attribute, String optionName, String elementName) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(WebActs.clickFromDropDownBy(text, attribute, elementName, AllProducts.getElementSelector(elementName), optionName, AllProducts.getElementSelector(optionName)));
    }

    @Given("I upload the {string} to the {string}")
    public static void IUploadTheFile(String fileName, String elementName) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(WebActs.uploadFile(fileName, elementName, AllProducts.getElementSelector(elementName)));
    }

    @Given("I upload the {string} to the {string} with keys")
    public static void IUploadTheFileWithKeys(String fileName, String elementName) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(WebActs.uploadFileWithKey(fileName, elementName, AllProducts.getElementSelector(elementName)));
    }

    @Given("I rename the {string} file to the {string}")
    public static void IRenameFileTo(String fileName, String textX) {
        AssertExecutedStep(renameFile(fileName, textX));
    }

    @Given("I delete the {string} from the {string}")
    public static void IDeleteFileFromThe(String fileName, String pathExp) {
        AssertExecutedStep(deleteFile(fileName, pathExp));
    }

    @Given("I select the {string} {string} from the {string}")
    public static void ISelectTheElementBy(String text, String attribute, String dropDownName) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(dropDownName, AllProducts.getElementSelector(dropDownName)));
        AssertExecutedStep(WebActs.selectFromDropDownBy(text, attribute, dropDownName, AllProducts.getElementSelector(dropDownName)));
    }

    @Given("I click the {string} {string} from the {string}")
    public static void IClickTheElementBy(String text, String attribute, String selection, String dropDownName) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(dropDownName, AllProducts.getElementSelector(dropDownName)));
        AssertExecutedStep(WebActs.clickFromDropDownBy(text, attribute, dropDownName, AllProducts.getElementSelector(dropDownName), selection, AllProducts.getElementSelector(selection)));
    }

    @Given("I select the {string} date in the {string} datepicker")
    public static void ISelectTheDateInTheDatePicker(String date, String datePickerName) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(datePickerName, AllProducts.getElementSelector(datePickerName)));
        AssertExecutedStep(selectDateInDatePicker(date, datePickerName, AllProducts.getElementSelector(datePickerName), AllProducts.getElementSelector("day_selector"), AllProducts.getElementSelector("done_button")));
    }

    @Given("I {string} {string} into the {string}")
    public static void IIntoTheElement(String act, String entry, String elementName) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(intoTheElement(act, entry, elementName, AllProducts.getElementSelector(elementName)));
    }

    @Given("I should see the {string}")
    public static void IShouldSeeTheElement(String elementName) {
        AssertExecutedStep(shouldSeeTheElement(elementName, AllProducts.getElementSelector(elementName)));
    }

    @Given("I should not see the {string}")
    public static void IShouldNotSeeTheElement(String elementName) {
        AssertExecutedStep(shouldNotSeeTheElement(elementName, AllProducts.getElementSelector(elementName)));
    }

    @Given("The {string} element {string} should {string} {string}")
    public static void TheElementTextShouldBe(String elementName, String attribute, String condition, String entry) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(elementTextShouldBe(elementName, AllProducts.getElementSelector(elementName), attribute, condition, entry));
    }

    @Given("The {string} element {string} should not {string} {string}")
    public static void TheElementTextShouldNotBe(String elementName, String attribute, String condition, String entry) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(elementTextShouldNotBe(elementName, AllProducts.getElementSelector(elementName), attribute, condition, entry));
    }

    @Given("I take screenshot as {string}")
    public static void ITakeScreenShot(String fileName) {
        AssertExecutedStep(WebActs.takeAScreenShot(System.getProperty("reportPath") + "/" + fileName));
    }

    @Given("I wait {string} secs for {string}")
    public static void IWaitSomeSec(String wait, String waitFor) {
        AssertExecutedStep(waitSomeSec(wait, waitFor));
    }

    @Given("I hit {string} on the keyboard")
    public static void IHitOnTheKeyBoard(String key) {
        AssertExecutedStep(hitOnTheKeyBoard(key));
    }

    @Given("I store the {string} element text as {string}")
    public static void IStoreTheElementTextAsTheTextX(String elementName, String attribute, String textX) {
        VerifyExecutedStep(WebActs.waitForElementToAppear(elementName, AllProducts.getElementSelector(elementName)));
        AssertExecutedStep(storeElementTextAsTextX(elementName, AllProducts.getElementSelector(elementName), attribute, textX));
    }

    public static void IStoreTheTextAsTheTextX(String text, String textX) {
        AssertExecutedStep(storeTextAsTextX(text, textX));
    }
}
