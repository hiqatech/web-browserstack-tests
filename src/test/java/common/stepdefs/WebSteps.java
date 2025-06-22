package common.stepdefs;

import common.setup.AllPages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import common.data.UtilHelp;
import common.data.FileHelp;
import common.selenium.WebHelp;
import common.setup.AllProducts;
import common.setup.AllURLs;

import static common.setup.Hooks.AssertExecutedStep;
import static common.setup.Hooks.VerifyExecutedStep;

public class WebSteps {

    @Given("I setup the {string} product and {string} environment")
    public static void ISetProductEnv(String product, String environment){
        System.setProperty("runEnvironment",product);
        System.setProperty("product",environment);
        System.setProperty("mainURL", AllURLs.getProductURL());
        AllPages.setAllProductsPageElements();
    }

    @Given("I wait {string} secs for {string}")
    public void IWaitSecSFor(int time, String what) {
        AssertExecutedStep( WebHelp.sleep(time) + " : " + "I wait " + time + " for " + what);
    }

    @Given("I start the webdriver")
    public static void IStartTheWebDriver() {
        AssertExecutedStep( WebHelp.startMyWebDriver() + " : " + "I start the Webdriver");
    }

    @Given("I stop the webdriver")
    public static void IStopTheWebDriver() {
      	VerifyExecutedStep( WebHelp.stopMyWebDriver() + " : " + "I stop the Webdriver");
    }

    @Given("I navigate to the {string} url")
    public static void INavigateToTheUrl(String url) {
        if(url.equalsIgnoreCase("app_home")) {
			url = AllURLs.getProductURL();
		}
        AssertExecutedStep( WebHelp.navigateTo(url) + " : " + "I navigate to the " + url + " URL");
    }

    @Given("I navigate to the Home page")
    public static void INavigateToTheHomePage(){
    	IStartTheWebDriver();
        AssertExecutedStep( WebHelp.navigateTo(System.getProperty("mainURL")) + " : " + "I navigate to the " + System.getProperty("mainURL") + " URL");  
        IAmOnThePage("Home");
    }

    @Given("I navigate to the {string} element link")
    public static void INavigateToTheElementLink(String elementName) {
    	VerifyExecutedStep(waitForElementToAppear(elementName));
        AssertExecutedStep( WebHelp.navigateTo(WebHelp.readAttributeOfWebElement(AllProducts.getElementSelector(elementName), "HREF")) + " : " + "I navigate to the " + elementName + " link with selector " + AllProducts.getElementSelector(elementName));
    }

    @Given("I am on the {string} page")
    public static void IAmOnThePage(String pageName) {
        System.setProperty("activePage", pageName);
        AssertExecutedStep( "PASS : I am on the " + pageName + " page ");
    }

    @Given("I switch to the {string}")
    public static void ISwitchToTheWindow(int windowNumber) {
        AssertExecutedStep( WebHelp.switchToWindow(windowNumber) + " : " + "I switch to the " + windowNumber + " window");
    }

    @Given("I switch to the {string} content")
    public static void ISwitchToTheContent(String frameName) {
        AssertExecutedStep( WebHelp.switchToFrame(AllProducts.getElementSelector(frameName)) + " : " + "I switch to the "  + frameName + " content");
       }

    @Given("I {string} the {string}")
    public static void IActTheElement(String act, String elementName) {
    	VerifyExecutedStep(waitForElementToAppear(elementName));
        if (elementName.contains("alert")) {
			AssertExecutedStep( WebHelp.handleAlert(act) + " : I " + act + " the alert");
		} else if (elementName.contains("CheckBox")) {
			AssertExecutedStep( WebHelp.checkBox(act, AllProducts.getElementSelector(elementName)) + " : I " + act + " the " + elementName);
		} else {
			AssertExecutedStep( WebHelp.safeAct(act, AllProducts.getElementSelector(elementName)) + " : I " + act + " the " + elementName);
		}
        if(elementName.contains("Tab") || elementName.contains("Search") ) {
			IWaitSomeSecs("7",elementName);
		}
    }

    @Given("I click the {string}")
    public static void IActTheBrowser(String act) {
        AssertExecutedStep( WebHelp.browserAct(act) + " : " + "I click the" + act);
    }

    @Given("I send enter keys to the popup window")
    public static void ISendEnterToWindow() {
        AssertExecutedStep( WebHelp.sendEnterKey() + " : " + "I send enter key to the window");
    }

    @Given("The {string} element status should be {string}")
    public static void TheElementStatusShouldBe(String elementName, String status) {
    	VerifyExecutedStep(waitForElementToAppear(elementName));
        if (status.equalsIgnoreCase("SELECTED")) {
			AssertExecutedStep( WebHelp.isSelected(AllProducts.getElementSelector(elementName), status) + " : " + "The " + elementName + " status should be " + status);
		} else if (status.equalsIgnoreCase("CHECKED")) {
			AssertExecutedStep( WebHelp.isChecked(AllProducts.getElementSelector(elementName), status) + " : " + "The " + elementName + " status should be " + status);
		} else {
			AssertExecutedStep( " status is not a implemented to assert");
		}
    }

    @Given("I should find the {string} in the downloads")
    public static void ICheckDownloads(String expectedFileName) {
        if (expectedFileName.contains("Text")) {
			expectedFileName = UtilHelp.prepText(expectedFileName);
		}
        AssertExecutedStep( FileHelp.checkDownLoad(expectedFileName) + " : " + "I should find the " + expectedFileName + " file ind the " + System.getProperty("downloadPath") + " folder");
    }

    @Given("I upload the {string} to the {string}")
    public static void IUploadTheFile(String fileName, String elementName) {
    	VerifyExecutedStep(waitForElementToAppear(elementName));
        if (fileName.contains("Text")) {
			fileName = UtilHelp.storedTexts.get(Integer.parseInt(fileName.replaceAll("\\D+", "")));
		}
        AssertExecutedStep( WebHelp.uploadFile(AllProducts.getElementSelector(elementName), fileName) + " : " + "I upload the " + fileName + " file to the " + elementName);
    }

    @Given("I upload the {string} to the {string} with keys")
    public static void IUploadTheFileWithKeys(String fileName, String elementName) {
    	VerifyExecutedStep(waitForElementToAppear(elementName));
        IActTheElement("select", elementName);
        if (fileName.contains("Text")) {
			fileName = UtilHelp.getStoredText(fileName);
		}
        AssertExecutedStep( WebHelp.uploadFileWithKey(fileName) + " : " + "I upload the " + fileName + " file to the " + elementName);
    }

    @Given("I delete the {string} from the {string}")
    public static void IDeleteFileFromThe(String fileName, String path) {
        if(path.contains("Downloads")) {
			path = System.getProperty("downloadPath");
		} else if(path.contains("Files")) {
			path = System.getProperty("filePath");
		}

        AssertExecutedStep( FileHelp.deleteFile(fileName, path) + " : " + "I delete the " + fileName);
    }
    
    @Given("I click the {string} {string} from the {string} options of the {string}")
    public static void IClickFromDropDownBy(String text, String attribute, String optionName, String elementName) {
    	VerifyExecutedStep(waitForElementToAppear(elementName));
    	AssertExecutedStep( WebHelp.clickFromDropDownBy(AllProducts.getElementSelector(elementName), AllProducts.getElementSelector(optionName), text, attribute) + " : " + "I select the " + text + " from the " + elementName);
    }

    @Given("I select the {string} {string} from the {string}")
    public static void ISelectTheElementBy(String text, String attribute, String elementName) {
    	if(text.contains("$")) {
			text = UtilHelp.getTestData(text.replace("$", ""));
		}
    	VerifyExecutedStep(waitForElementToAppear(elementName));
    	AssertExecutedStep( WebHelp.selectFromDropDownBy(AllProducts.getElementSelector(elementName), attribute, text) + " : " + "I select the " + text + " " + attribute + " from the " + elementName);
    }

    @Given("I click the {string} {string} from the {string}")
    public static void IClickTheElementBy(String text, String attribute, String selection, String elementName) {
    	VerifyExecutedStep(waitForElementToAppear(elementName));
    	AssertExecutedStep( WebHelp.clickFromDropDownBy(AllProducts.getElementSelector(elementName), AllProducts.getElementSelector(selection), text, attribute) + " : " + "I select the " + text + " from the " + elementName);
    }

    @Given("I select the {string} date in the {string} datepicker")
    public static void ISelectTheDateInTheDatePicker(String date, String elementName) {
    	VerifyExecutedStep(waitForElementToAppear(elementName));
    	String result = WebHelp.safeAct("select", AllProducts.getElementSelector(elementName)) + " : " + "I select the " + elementName;

        String dateToSet = UtilHelp.getDynamicDate(date, "yyyyMMdd");
        int nth = Integer.parseInt(dateToSet.substring(4, 6));
        String daySelector = AllProducts.getElementSelector("day_selector");
        daySelector.replace("DD", String.valueOf(nth));
        String doneButtonSelector = AllProducts.getElementSelector("doneButtonSelector");
        result = result + WebHelp.selectNthElement(daySelector, String.valueOf(nth - 1)) + " : " + "I select the " + dateToSet + " with selector " + daySelector;
        AssertExecutedStep( result + WebHelp.safeAct("select", doneButtonSelector) + " : " + "I select the " + doneButtonSelector);
    }

    @Given("I {string} {string} into the {string}")
    public static void IIntoTheElement(String act, String entry, String elementName) {
       	if(entry.contains("$")) {
    			entry = UtilHelp.getTestData(entry.replace("$", ""));
    		}
        	if (entry.contains("CurrentDate")) {
    			entry = UtilHelp.getDynamicDate(entry, "yyyy-MM-dd");
    		} else if (entry.contains("TimeStamp")) {
    			entry = UtilHelp.getTimeStamp("yyyy-MM-dd-hh-mm-ss");
    		} else if (entry.contains("Text")) {
    			entry = UtilHelp.getStoredText(entry);
    		} else if (elementName.contains("date")) {
    			WebHelp.safeAct("select", AllProducts.getElementSelector(elementName));
                for (int i = 0; i < 10; i++) {
                	WebHelp.keyActions("ArrowLeft");
    			}
            }
            entry = entry.replace("-", "").replace("/", "");

            VerifyExecutedStep(waitForElementToAppear(elementName));
            AssertExecutedStep( WebHelp.safeInto(act, AllProducts.getElementSelector(elementName), entry) + " : " + "I " + act + " the " + entry + " into the " + elementName);
        }

    @Given("I should see the {string}")
    public static void IShouldSeeTheElement(String elementName) {
        AssertExecutedStep( WebHelp.waitToAppear(AllProducts.getElementSelector(elementName)) + " : The " + elementName + " should not be visible with selector " + AllProducts.getElementSelector(elementName));
    }

    @Given("I should not see the {string}")
    public static void IShouldNotSeeTheElement(String elementName) {
        AssertExecutedStep( WebHelp.waitToDisappear(AllProducts.getElementSelector(elementName)) + " : The " + elementName + " should not be visible with selector " + AllProducts.getElementSelector(elementName));
    }

    @Given("The {string} element {string} should be {string}")
    public static void TheElementTextShouldBe(String elementName, String attribute, String text) {
    	attribute = attribute.toUpperCase();
        if (text.contains("Text")) {
			UtilHelp.getStoredText(text);
		}
        String currentText = "null";


        VerifyExecutedStep(waitForElementToAppear(elementName));
        if (attribute.equalsIgnoreCase("TEXT")) {
			currentText = WebHelp.readTextOfWebElement(AllProducts.getElementSelector(elementName));
		} else if (attribute.equalsIgnoreCase("VALUE") || attribute.equalsIgnoreCase("HREF") || attribute.equalsIgnoreCase("PLACEHOLDER")) {
			currentText = WebHelp.readAttributeOfWebElement(AllProducts.getElementSelector(elementName), attribute.toLowerCase());
		} else {
			AssertExecutedStep( "FAIL" + " : " + "The " + attribute + " attribute test of element has not been implemented");
		}

        if (!text.contains("http")) {
			text = UtilHelp.prepText(text);
		}

        String result = "FAIL";
        if (currentText.equalsIgnoreCase(text))
            result = "PASS";

        AssertExecutedStep( result + " : " + "The " + currentText + " " + attribute + " should be " + text + " in the " + elementName);
    }

    @Given("The {string} element {string} should not {string} {string}")
    public static void TheElementTextShouldNotBe(String elementName, String attribute, String condition, String text) {
    	attribute = attribute.toUpperCase();
        if (text.contains("Text")) {
			UtilHelp.getStoredText(text);
		}
        String currentText = "null";

        VerifyExecutedStep(waitForElementToAppear(elementName));
        if (attribute.equalsIgnoreCase("TEXT")) {
			currentText = WebHelp.readTextOfWebElement(AllProducts.getElementSelector(elementName));
		} else if (attribute.equalsIgnoreCase("VALUE") || attribute.equalsIgnoreCase("HREF") || attribute.equalsIgnoreCase("PLACEHOLDER")) {
			currentText = WebHelp.readAttributeOfWebElement(AllProducts.getElementSelector(elementName), attribute.toLowerCase());
		} else {
			AssertExecutedStep( "FAIL" + " : " + "The " + attribute + " attribute has not been implemented");
		}

        if (!text.contains("http")) {
			text = UtilHelp.prepText(text);
		}

        String result = "PASS";

        if (condition.equalsIgnoreCase("EQUAL")) {
            if (currentText.equalsIgnoreCase(text)) {
				result = "FAIL";
			}
        } else if (condition.equalsIgnoreCase("CONTAIN")) {
            if (currentText.contains(text)) {
				result = "FAIL";
			}
        } else {
			AssertExecutedStep( "FAIL" + " : " + "The " + condition + " condition test of element has not been implemented");
		}

        AssertExecutedStep( result + " : " + "The " + currentText + " " + attribute + " should " + condition + " with text " + text + " in the " + elementName);
    }

    @Given("I wait {string} secs for the {string}")
    public static void IWaitSomeSecs(String wait, String waitFor) {
    	VerifyExecutedStep(WebHelp.sleep(Integer.valueOf(wait) * 1000) + " : " + "Wait " + wait + " sec/s for " + waitFor);
    }

    @Given("I hit {string} on the keyboard")
    public static void IHitOnTheKeyBoard(String key) {
    	AssertExecutedStep(WebHelp.keyActions(key) + " : " + "I hit " + key + " on the keyboard");
    }

    @Given("I store the {string} element text as {string}")
    public static void IStoreTheElementTextAsTheTextX(String elementName, String attribute, String textX) {
    	 String currentText = "null";

         VerifyExecutedStep(waitForElementToAppear(elementName));
         if (attribute.equalsIgnoreCase("TEXT")) {
 			currentText = WebHelp.readTextOfWebElement(AllProducts.getElementSelector(elementName));
 		} else if (attribute.equalsIgnoreCase("VALUE") || attribute.equalsIgnoreCase("HREF") || attribute.equalsIgnoreCase("PLACEHOLDER")) {
 			currentText = WebHelp.readAttributeOfWebElement(AllProducts.getElementSelector(elementName), attribute.toLowerCase());
 		} else {
 			AssertExecutedStep( "FAIL" + " : " + "The " + attribute + " attribute has not been implemented");
 		}
         AssertExecutedStep( UtilHelp.storeText(currentText, textX) + " : " + "Store the " + attribute + " of the " + elementName + " as " + textX);
     }

     public static void storeTextAsTextX(String text, String textX) {
         text = UtilHelp.prepText(text);
         AssertExecutedStep( UtilHelp.storeText(text, textX) + " : " + "Store the " + text + " as " + textX);
     }

    public static void IStoreTheTextAsTheTextX(String text, String textX) {
        storeTextAsTextX(text, textX);
    }

    public static String waitForElementToAppear(String elementName) {
        return WebHelp.waitToAppear(AllProducts.getElementSelector(elementName)) + " : Visibility of element " + elementName;
    }

    public static String waitForElementToDisappear(String elementName) {
        return WebHelp.waitToDisappear(AllProducts.getElementSelector(elementName)) + " : UnVisibility of element " + elementName;
    }

    public static String checkTheElementVisibility(String elementName) {
    	return WebHelp.checkElementVisibility(AllProducts.getElementSelector(elementName)) + " : UnVisibility of element " + elementName;
    }


}
