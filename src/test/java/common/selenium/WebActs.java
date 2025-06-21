package common.selenium;

import common.setup.AllURLs;
import common.setup.Hooks;
import common.util.DataHelp;
import common.util.FileHelp;

import static common.util.DataHelp.onlyDigits;

public class WebActs extends WebHelp {

    public static String startWebDriver(String driver) {
        System.setProperty("mainURL", AllURLs.getProductURL());
        if(driver.contains("default"))
            driver = System.getProperty("runDriver");
            System.out.println("WebDriver : " + System.getProperty("runDriver"));
            System.out.println("SeleniumGrid : " + System.getProperty("seleniumGrid"));
        return startMyWebDriver(driver) + " ,,, : " + "I start the Webdriver with the " + driver + " browser" + "\n";
    }

    public static String stopWebDriver() {
        return stopMyWebDriver() + " ,,, : " + "I stop the Webdriver with " + System.getProperty("runDriver") + " the browser" + "\n";
    }

    public static String navigateToHomePage(String pageName) {
        return navigateTo(System.getProperty("mainURL")) + " ,,, : " + "I navigate to the " + System.getProperty("mainURL") + " URL" + "\n";
    }

    public static String navigateToUrl(String url) {
        if(url.equalsIgnoreCase("app_home"))
            url = AllURLs.getProductURL();
        return navigateTo(url) + " ,,, : " + "I navigate to the " + url + " URL" + "\n";
    }

    public static String navigateToTheElementLink(String elementName, String elementSelector) {
        return navigateTo(readAttributeOfWebElement(elementSelector, "HREF")) + " ,,, : " + "I navigate to the " + elementName + " link with selector " + elementSelector + "\n";
    }

    public static String onThePage(String pageName) {
        System.setProperty("activePage", pageName);
        return "PASS  : I am on the " + pageName + " page " + "\n";
    }

    public static String switchToWindow(int windowNumber) {
        return switchToWindow(windowNumber) + " ,,, : " + "I switch to the " + windowNumber + " window" + "\n";
    }

    public static String switchToFrameContent(String frameName,String frameSelector) {
        if(frameName.equalsIgnoreCase("default")) frameSelector = frameName;
        return switchToFrame(frameSelector) + " ,,, : " + "I switch to the "  + frameName + " content" + "\n";
    }

    public static String waitForElementToAppear(String elementName, String elementSelector) {
        return waitToAppear(elementSelector) + " ,,, : Visibility of element " + elementName + " with selector " + elementSelector + "\n";
    }

    public static String waitForElementToDisappear(String elementName, String elementSelector) {
        return waitToDisappear(elementSelector) + " ,,, : UnVisibility of element " + elementName + " with selector " + elementSelector + "\n";
    }

    public static String actTheElement(String act, String elementName, String elementSelector) {
        if (elementName.contains("alert"))
            return handleAlert(act) + " ,,, : I " + act + " the alert" + "\n";
        else
            return safeAct(act, elementSelector) + " ,,, : I " + act + " the " + elementName + " with selector " + elementSelector + "\n";
    }

    public static String browserAction(String act) {
        return browserAct(act) + " ,,, : " + "I click the" + act + "\n";
    }

    public static String sendEnterToWindow() {
        return sendEnterKey() + " ,,, : " + "I send enter key to the window" + "\n";
    }

    public static String elementStatusShouldBe(String elementName, String elementSelector, String status) {
        if (status.equalsIgnoreCase("SELECTED"))
            return isSelected(elementSelector, status) + " ,,, : " + "The " + elementName + " status should be " + status + " with selector " + elementSelector + "\n";
        else if (status.equalsIgnoreCase("CHECKED"))
            return isChecked(elementSelector, status) + " ,,, : " + "The " + elementName + " status should be " + status + " with selector " + elementSelector + "\n";
        else return " status is not a implemented to assert";
    }

    public static String checkDownloads(String expectedFileName) {
        if (expectedFileName.contains("Text"))
            expectedFileName = DataHelp.prepText(expectedFileName);
        return FileHelp.checkDownLoad(expectedFileName) + " ,,, : " + "I should find the " + expectedFileName + " file ind the " + System.getProperty("downloadPath") + " folder" + "\n";
    }

    public static String uploadFile(String fileName, String elementName, String elementSelector) {
        if (fileName.contains("Text"))
            fileName = DataHelp.storedTexts.get(Integer.parseInt(fileName.replaceAll("\\D+", "")));
        return uploadFile(elementSelector, fileName) + " ,,, : " + "I upload the " + fileName + " file to the " + elementName + " with selector " + elementSelector + "\n";
    }

    public static String uploadFileWithKey(String fileName, String elementName, String elementSelector) {
        actTheElement("select", elementName, elementSelector);
        if (fileName.contains("Text"))
            fileName = DataHelp.getStoredText(fileName);
        return uploadFileWithKey(fileName) + " ,,, : " + "I upload the " + fileName + " file to the " + elementName + " with selector " + elementSelector + "\n";
    }

    public static String renameFile(String fileName, String text) {
        String newName = DataHelp.getStoredText(text);
        return FileHelp.renameFile(fileName, newName) + " ,,, : " + "I rename the " + fileName + " file to the " + newName + "\n";
    }

    public static String deleteFile(String fileName, String path) {
        if(path.contains("Downloads"))
            path = System.getProperty("downloadPath");
        else if(path.contains("Files"))
            path = System.getProperty("filePath");

        return FileHelp.deleteFile(fileName, path) + " ,,, : " + "I delete the " + fileName + "\n";
    }

    public static String selectFromDropDownBy(String text, String attribute, String elementName, String elementSelector) {
        return selectFromDropDownBy(elementSelector, attribute, text) + " ,,, : " + "I select the " + text + " " + attribute + " from the " + elementName + " with selector " + elementSelector + "\n";
    }

    public static String clickFromDropDownBy(String text, String attribute, String optionName, String optionSelector, String dropDownName, String dropDownSelector) {
        return clickFromDropDownBy(dropDownSelector, optionSelector, text, attribute) + " ,,, : " + "I select the " + text + " from the " + dropDownName + " with selector " + dropDownSelector + "\n";
    }

    public static String selectDateInDatePicker(String date, String datePickerName, String datePickerSelector, String daySelector, String doneButtonSelector) {
        String result = safeAct("select", datePickerSelector) + " ,,, : " + "I select the " + datePickerName + " with selector " + datePickerSelector + "\n";

        String dateToSet = DataHelp.getDynamicDate(date, "yyyyMMdd");
        int nth = Integer.parseInt(dateToSet.substring(4, 6));
        daySelector = daySelector.replace("DD", String.valueOf(nth));

        waitForElementToAppear("day_button", daySelector);
        result = result + selectNthElement(daySelector, String.valueOf(nth - 1)) + " ,,, : " + "I select the " + dateToSet + " with selector " + daySelector + "\n";

        waitForElementToAppear("done_button", doneButtonSelector);
        return result + safeAct("select", doneButtonSelector) + " ,,, : " + "I select the " + doneButtonSelector + " with selector " + datePickerSelector + "\n";
    }

    public static String intoTheElement(String act, String entry, String elementName, String elementSelector) {
        if (entry.contains("CurrentDate"))
            entry = DataHelp.getDynamicDate(entry, "yyyy-MM-dd");
        else if (entry.contains("TimeStamp"))
            entry = DataHelp.getTimeStamp("yyyy-MM-dd-hh-mm-ss");
        else if (entry.contains("Text"))
            entry = DataHelp.getStoredText(entry);
        else if (elementName.contains("date")) {
            safeAct("select", elementSelector);
            for (int i = 0; i < 10; i++)
                keyActions("ArrowLeft");
        }
        else if (entry.equalsIgnoreCase("OTPNumber"))
            entry = Hooks.otpNumber;
        else if (entry.equalsIgnoreCase("MyUserName"))
            entry = Hooks.myUserName;
        else if (entry.equalsIgnoreCase("MyPassword"))
            entry = Hooks.myPassword;

        entry = entry.replace("-", "").replace("/", "");

        return safeInto(act, elementSelector, entry) + " ,,, : " + "I " + act + " the " + entry + " text into the " + elementName + " with selector " + elementSelector + "\n";
    }

    public static String shouldSeeTheElement(String elementName, String elementSelector) {
        return waitToAppear(elementSelector) + " ,,, : The " + elementName + " should be visible with selector " + elementSelector + "\n";
    }

    public static String shouldNotSeeTheElement(String elementName, String elementSelector) {
        return waitToDisappear(elementSelector) + " ,,, : The " + elementName + " should not be visible with selector " + elementSelector + "\n";
    }

    public static String elementTextShouldBe(String elementName, String elementSelector, String attribute, String condition, String text) {
        attribute = attribute.toUpperCase();

        if (text.contains("Text"))
            DataHelp.getStoredText(text);

        String currentText = "null";
        if (attribute.equalsIgnoreCase("TEXT"))
            currentText = readTextOfWebElement(elementSelector);
        else if (attribute.equalsIgnoreCase("VALUE") || attribute.equalsIgnoreCase("HREF") || attribute.equalsIgnoreCase("PLACEHOLDER"))
            currentText = readAttributeOfWebElement(elementSelector, attribute.toLowerCase());
        else return "FAIL" + " : " + "The " + attribute + " attribute test of element has not been implemented" + "\n";

        if (!text.contains("http"))
            text = DataHelp.prepText(text);

        String result = "FAIL";

        if (condition.equalsIgnoreCase("EQUAL")) {
            if (currentText.equalsIgnoreCase(text))
                result = "PASS";
        } else if (condition.equalsIgnoreCase("CONTAIN")) {
            if (currentText.contains(text))
                result = "PASS";
        } else
            return "FAIL" + " ,,, : " + "The " + condition + " condition test of element has not been implemented" + "\n";

        return result + " ,,, : " + "The " + currentText + " " + attribute + " should " + condition + " with text " + text + " in the " + elementName + " with selector " + elementSelector + "\n";
    }

    public static String elementTextShouldNotBe(String elementName, String elementSelector, String attribute, String condition, String text) {
        attribute = attribute.toUpperCase();

        if (text.contains("Text"))
            DataHelp.getStoredText(text);

        String currentText = "null";
        if (attribute.equalsIgnoreCase("TEXT"))
            currentText = readTextOfWebElement(elementSelector);
        else if (attribute.equalsIgnoreCase("VALUE") || attribute.equalsIgnoreCase("HREF") || attribute.equalsIgnoreCase("PLACEHOLDER"))
            currentText = readAttributeOfWebElement(elementSelector, attribute.toLowerCase());
        else return "FAIL" + " : " + "The " + attribute + " attribute has not been implemented" + "\n";

        if (!text.contains("http"))
            text = DataHelp.prepText(text);

        String result = "PASS";

        if (condition.equalsIgnoreCase("EQUAL")) {
            if (currentText.equalsIgnoreCase(text))
                result = "FAIL";
        } else if (condition.equalsIgnoreCase("CONTAIN")) {
            if (currentText.contains(text))
                result = "FAIL";
        } else
            return "FAIL" + " ,,, : " + "The " + condition + " condition test of element has not been implemented" + "\n";

        return result + " ,,, : " + "The " + currentText + " " + attribute + " should " + condition + " with text " + text + " in the " + elementName + " with selector " + elementSelector + "\n";

    }

    public static String takeAScreenShot(String dest) {
        return takeScreenShot(dest) + " ,,, : " + "I take screenshot and save to " + dest + "\n";
    }

    public static String waitSomeSec(String wait, String waitFor) {
        return sleep(Integer.valueOf(wait) * 1000) + " ,,, : " + "Wait " + wait + " sec/s for " + waitFor + "\n";
    }

    public static String hitOnTheKeyBoard(String key) {
        return keyActions(key) + " ,,, : " + "I hit " + key + " on the keyboard" + "\n";
    }

    public static String storeElementTextAsTextX(String elementName, String attribute, String elementSelector, String textX) {
        String currentText = "null";
        if (attribute.equalsIgnoreCase("TEXT"))
            currentText = readTextOfWebElement(elementSelector);
        else if (attribute.equalsIgnoreCase("VALUE") || attribute.equalsIgnoreCase("HREF") || attribute.equalsIgnoreCase("PLACEHOLDER"))
            currentText = readAttributeOfWebElement(elementSelector, attribute.toLowerCase());
        else return "FAIL" + " ,,, : " + "The " + attribute + " attribute has not been implemented" + "\n";

        String text = readTextOfWebElement(elementSelector);
        return DataHelp.storeText(currentText, textX) + " ,,, : " + "Store the " + attribute + " of the " + elementName + " with selector " + elementSelector + " as " + textX + "\n";
    }

    public static String storeTextAsTextX(String text, String textX) {
        text = DataHelp.prepText(text);
        return DataHelp.storeText(text, textX) + " ,,, : " + "Store the " + text + " as " + textX + "\n";
    }

    public static String receiveOTPNumber() {
        String otpNumber = receiveOTPNumber(Hooks.accountSID, Hooks.authToken, Hooks.phoneNumber);
        if(onlyDigits(otpNumber)){
            Hooks.otpNumber = otpNumber;
            return "PASS : I receive the OTPNumber to my phone number" + "\n";
        }
        else return  otpNumber + " ,,, : "+"Receive the OTPNumber " +"\n";
    }



}
