package common.selenium;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;


public class WebHelp {

    public static WebDriver webDriver;
    public static int waitTimeMax= 12000;
    public static int waitTime = 500;
    public static int timeOut = 60;

    public static String startMyWebDriver(){
        try
        {
            MutableCapabilities capabilities = new MutableCapabilities();
            HashMap<String, String> bstackOptions = new HashMap<>();
            bstackOptions.putIfAbsent("source", "cucumber-java:sample-master:v1.2");
            capabilities.setCapability("bstack:options", bstackOptions);
            webDriver = new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), capabilities);

            webDriver.manage().timeouts().pageLoadTimeout(timeOut,TimeUnit.SECONDS);
            webDriver.manage().timeouts().setScriptTimeout(timeOut,TimeUnit.SECONDS);

            webDriver.manage().window().maximize();

            return  "PASS";

        }
        catch(Exception ex)
        {return  ex.toString();}

    }

    public static String stopMyWebDriver(){
        try
        {
            webDriver.close();
            webDriver.quit();
            return  "PASS";
        }
        catch(Exception ex)
        {return "NOTE : " + ex.toString();}
    }

    public static String navigateTo(String URL){
        try
        {
            if(!URL.isEmpty() && !URL.equalsIgnoreCase("") && !(URL == null)) {
				webDriver.navigate().to(URL);
			} sleep(5000);
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String getCurrentURL(){
        try
        {
            return webDriver.getCurrentUrl();
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static Boolean verifyNotNull(String text){
        if(!text.isEmpty() && !text.equalsIgnoreCase("") && !(text == null)) {
			return true;
		} else {
			return false;
		}
    }

    public static String isDisplayed(String elementSelector){
        try
        {
            WebElement webElement  = getWebElement(elementSelector);
            if(webElement.isDisplayed() || webElement.isEnabled()) {
				return "PASS";
			} else if(webElement.isEnabled()) {
				return "Element not displayed or hidden";
			} else {
				return "ERROR";
			}
        }
        catch (Exception ex){
            return ex.toString();
        }
    }

    public static WebElement getWebElement(String elementSelector){

        return webDriver.findElement(By.xpath(elementSelector));
    }

    public static String sleep(int sleep){
        try
        {
            Thread.sleep(sleep);
            return "PASS";
        }
        catch (Exception ex){return ex.toString();}
    }

    public static String waitToAppear(String elementSelector){
        double startTime = 0;
        sleep(500);
        while (startTime < waitTimeMax)
        {
            sleep(waitTime);
            if(isDisplayed(elementSelector).equalsIgnoreCase("PASS")) {
				return  "PASS";
			} else
                {
                    sleep(waitTime);
                    startTime = startTime + waitTime;
                }
        }
        return isDisplayed(elementSelector);
    }

    public static String waitToDisappear(String elementSelector){
        double startTime = 0;
        while (startTime < waitTimeMax)
        {
            if(!isDisplayed(elementSelector).equalsIgnoreCase("PASS")) {
				return  "PASS";
			} else
            {
                sleep(waitTime);
                startTime = startTime + waitTime;
            }
        }
        return "ERROR";
    }

    public static Boolean checkElementVisibility(String elementSelector){
        double startTime = 0;
        while (startTime < 5000)
        {
            if(!isDisplayed(elementSelector).equalsIgnoreCase("PASS")) {
				return true;
			} else
            {
                sleep(waitTime);
                startTime = startTime + waitTime;
            }
        }
        return false;
    }

    public static String switchToFrame(String frameSelector){
        try
        {
            webDriver.switchTo().defaultContent();
            if(frameSelector.equalsIgnoreCase("default")) {
				return "PASS";
			}
            if(verifyNotNull(frameSelector)) {
                WebElement frame = webDriver.findElement(By.xpath(frameSelector));
                webDriver.switchTo().frame(frame);
                sleep(3000);
                return "PASS";
            } else {
				return "ERROR";
			}
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String switchToFrameInFrame(String frameSelector1, String frameSelector2){
        try
        {
            webDriver.switchTo().defaultContent();
            if(verifyNotNull(frameSelector1)) {
                waitToAppear(frameSelector1);
                WebElement frame = webDriver.findElement(By.xpath(frameSelector1));
                webDriver.switchTo().frame(frame);
                sleep(2000);
            } else {
				return "ERROR";
			}
            if(verifyNotNull(frameSelector2)) {
                waitToAppear(frameSelector2);
                WebElement frame = webDriver.findElement(By.xpath(frameSelector2));
                webDriver.switchTo().frame(frame);
                sleep(2000);
                return "PASS";
            } else {
				return "ERROR";
			}
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String switchToDefaultContent(){
        try
        {
            webDriver.switchTo().defaultContent();
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String switchToWindow(int windowNumber){
        try
        {
            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(windowNumber-1));
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String keyActions(String actionKey){
        try
        {
            actionKey = actionKey.toUpperCase();
            Actions action = new Actions(webDriver);

            switch(actionKey)
            {
                case "PAGEDOWN": action.sendKeys(Keys.PAGE_DOWN); break;
                case "PAGEUP": action.sendKeys(Keys.PAGE_UP); break;
                case "ARROWUP": action.sendKeys(Keys.ARROW_UP); break;
                case "ARROWDOWN": action.sendKeys(Keys.ARROW_DOWN); break;
                case "ALT": action.sendKeys(Keys.ALT); break;
                case "ESC": action.sendKeys(Keys.ESCAPE); break;
                case "F1": action.sendKeys(Keys.F1); break;
                case "F2": action.sendKeys(Keys.F2); break;
                case "F3": action.sendKeys(Keys.F3); break;
                case "F4": action.sendKeys(Keys.F4); break;
                case "F5": action.sendKeys(Keys.F5); break;
                case "F6": action.sendKeys(Keys.F6); break;
                case "F7": action.sendKeys(Keys.F7); break;
                case "F8": action.sendKeys(Keys.F8); break;
                case "F9": action.sendKeys(Keys.F9); break;
                case "ENTER": action.sendKeys(Keys.ENTER); break;
                default: {System.out.println(actionKey + " actionKey has net been defined"); return "ERROR";}
            }
            action.perform();
            sleep(500);
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String sendKeys(String elementSelector, String actionKey){
        try
        {
            actionKey = actionKey.toUpperCase();
            WebElement webELement = getWebElement(elementSelector);
            switch(actionKey)
            {
                case "PAGEDOWN": webELement.sendKeys(Keys.PAGE_DOWN); break;
                case "PAGEUP": webELement.sendKeys(Keys.PAGE_UP); break;
                case "ARROWUP": webELement.sendKeys(Keys.ARROW_UP); break;
                case "ARROWDOWN": webELement.sendKeys(Keys.ARROW_DOWN); break;
                case "ARROWRIGHT": webELement.sendKeys(Keys.ARROW_RIGHT); break;
                case "ARROWLEFT": webELement.sendKeys(Keys.ARROW_LEFT); break;
                case "ALT": webELement.sendKeys(Keys.ALT); break;
                case "ESC": webELement.sendKeys(Keys.ESCAPE); break;
                case "F1": webELement.sendKeys(Keys.F1); break;
                case "F2": webELement.sendKeys(Keys.F2); break;
                case "F3": webELement.sendKeys(Keys.F3); break;
                case "F4": webELement.sendKeys(Keys.F4); break;
                case "F5": webELement.sendKeys(Keys.F5); break;
                case "F6": webELement.sendKeys(Keys.F6); break;
                case "F7": webELement.sendKeys(Keys.F7); break;
                case "F8": webELement.sendKeys(Keys.F8); break;
                case "F9": webELement.sendKeys(Keys.F9); break;
                case "ENTER": webELement.sendKeys(Keys.ENTER); break;
                default: {System.out.println(actionKey + " sendKey has net been defined"); return "ERROR";}
            }
            sleep(500);
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }


    public static String navigateToWithSendEnterKey(String URL, int thread1, int thread2){
        try
        {
            navigateTo(URL);
            sleep(3000);
            keyActions("ENTER");
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String handleAlert(String action){
        try
        {
            action = action.toUpperCase();
            if(action.equalsIgnoreCase("ACCEPT")) {
				webDriver.switchTo().alert().accept();
			} else if(action.equalsIgnoreCase("DISMISS")) {
				webDriver.switchTo().alert().dismiss();
			}
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }


    public static String browserAct(String elementName){
        try
        {
            elementName = elementName.toUpperCase();
            if(elementName.contains("BACK")) {
				webDriver.navigate().back();
			} else if(elementName.equalsIgnoreCase("FORWARD")) {
				webDriver.navigate().forward();
			}
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String readTextOfWebElement(String elementSelector){
        try
        {
            WebElement webElement = getWebElement(elementSelector);
            return webElement.getText();
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String readAttributeOfWebElement(String elementSelector, String attribute){
        try
        {
            WebElement webElement = getWebElement(elementSelector);
            return webElement.getAttribute(attribute);
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String isChecked(String elementSelector, String status){
        try
        {
            status = status.toUpperCase();
            WebElement webElement = getWebElement(elementSelector);
            if(webElement.isSelected() && status.equalsIgnoreCase("CHECKED")) {
				return "PASS";
			} else if(!webElement.isSelected() && status.equalsIgnoreCase("UNCHECKED")) {
				return  "PASS";
			} else {
				return "ERROR";
			}
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String tryToSelect(WebElement webElement){
        try
        {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).click().perform();
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String tryToClick(WebElement webElement){
        try
        {
            webElement.click();
            return "PASS";
        }
        catch(Exception ex)
        {
            if(ex.toString().contains("StaleElement")){
                sleep(3000);
                try {
                    webElement.click();
                    return "PASS";
                }
                catch (Exception exc)
                {return exc.toString();}
                }
            return ex.toString();}
    }

    public static String tryToHover(WebElement webElement){
        try
        {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).click().build().perform();
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String scrollAnd(String act, String elementSelector){
        try
        {
            WebElement webElement = getWebElement(elementSelector);
            for(int i =0;i<3;i++)
            {keyActions("PAGEUP");}
            String result = "";
            int trying = 0;
            if(act.equalsIgnoreCase("CLICK")) {
				result = tryToClick(webElement);
			} else if(act.equalsIgnoreCase("SELECT")) {
				result = tryToSelect(webElement);
			}
            result = tryToClick(webElement);
            while(trying < 70)
            {
                if(result.equalsIgnoreCase("PASS")) {
					break;
				} else
                {
                    keyActions("ARROWDOWN");
                    if(act.equalsIgnoreCase("CLICK")) {
						result = tryToClick(webElement);
					} else if(act.equalsIgnoreCase("SELECT")) {
						result = tryToSelect(webElement);
					}
                    trying = trying + 1;
                }
            }
            trying = 0;
            return result;
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String safeAct(String act, String elementSelector){
        try
        {
            WebElement webElement = getWebElement(elementSelector);

            if(act.contains("hover") || act.equals("hover")) {
				tryToHover(webElement);
			}
            if(act.contains("double")) {
            	tryToClick(webElement); sleep(2000); }
            if(act.contains("click") || act.equals("click")) {
				return tryToClick(webElement);
			} else if(act.contains("select")|| act.equals("select")) {
				return tryToSelect(webElement);
			} else if(act.contains("hit")|| act.equals("hit"))
                {webElement.sendKeys(Keys.ENTER); return "PASS";}
            else if(act.contains("clear")|| act.equals("clear"))
            {webElement.clear(); return "PASS";}
            else {System.out.println(act + " action has not been defined"); return  "FAIL";}
        }
        catch(Exception ex)
        {return ex.toString();}
    }

    public static String checkBox(String act, String elementSelector){
        try
        {
            act = act.toUpperCase();
            WebElement webElement = getWebElement(elementSelector);
            return tryTheBox(webElement,act);
         }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String tryTheBox(WebElement webElement, String act){
    	try
        {
    		if(act.equalsIgnoreCase("CHECK"))
    			{if (!webElement.isSelected()) {
					webElement.click();
				} return "PASS"; }
            else if(act.equalsIgnoreCase("UNCHECK"))
            {if (webElement.isSelected()) {
				webElement.click();
			} return "PASS"; }
			else {System.out.println(act + " action has not been defined"); return  "FAIL";}
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String sendEnterKey(){
        try
        { sleep(1000);
        Robot robot = new Robot();
        //robot.keyPress(KeyEvent.VK_TAB);
        //robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String safeInto(String act, String elementSelector, String text){
        try
        {   if(act.equalsIgnoreCase("TYPE"))
            {
                safeAct("click", elementSelector);
                WebElement webElement = getWebElement(elementSelector);
                webElement.sendKeys(text);
                return "PASS";
            }
            else if(act.equalsIgnoreCase("ENTER"))
            {
                safeAct("select",elementSelector);
                WebElement webElement = getWebElement(elementSelector);
                Actions action = new Actions(webDriver);
                action.sendKeys(text).perform();
                return "PASS";
            }
            else { System.out.println(act + " action has not been defined");return "ERROR";}
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String selectFromDropDownBy(String elementSelector,String what, String text){
        try
        {
            WebElement webElement = getWebElement(elementSelector);
            Select select = new Select(webElement);
            if(what.equalsIgnoreCase("text")) {
				select.selectByVisibleText(text);
			} else if(what.equalsIgnoreCase("index")) {
				select.selectByIndex(Integer.parseInt(text));
			} else if(what.equalsIgnoreCase("value")) {
				select.selectByValue(text);
			}
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String clickFromDropDownBy(String dropdownSelector, String optionsSelector, String text, String attribute){
        try
        {
            safeAct("select", dropdownSelector);
            sleep(500);
            waitToAppear(dropdownSelector);
            List<WebElement> options = webDriver.findElements(By.xpath(optionsSelector));
            for(WebElement option : options)
            {
                String currenText = "null";
                if(attribute.equalsIgnoreCase("text")) {
					currenText = option.getText();
				}
                if(attribute.equalsIgnoreCase("value")) {
					currenText = option.getAttribute("value");
				}
                if(attribute.equalsIgnoreCase("placeholder")) {
					currenText = option.getAttribute("placeholder");
				}

                if(currenText.equalsIgnoreCase(text))
                {
                    tryToSelect(option);
                    break;
                }
            }
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String isSelected(String elementSelector, String status){
        try
        {
            status = status.toUpperCase();
            WebElement webElement = getWebElement(elementSelector);
            if(status.equalsIgnoreCase("SELECTED")) {
				if(webElement.isSelected()) {
					return "PASS";
				} else {
					return "FALSE";
				}
			} else if(status.equalsIgnoreCase("UNSELECTED")) {
				if(!webElement.isSelected()) {
					return "PASS";
				} else {
					return "FALSE";
				}
			} else { System.out.println(status + " has not been defined");return "ERROR";}
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String uploadFileWithKey(String fileName){
        try
        {
            //String path = System.getProperty("uploadPath") + fileName;
            sleep(1000);
            StringSelection string = new StringSelection(fileName);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(string,null);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            sleep(2000);
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String uploadFile(String browserButtonSelector, String fileName){
        try
        {
            String path = System.getProperty("uploadPath") + fileName;
            webDriver.findElement(By.xpath(browserButtonSelector)).sendKeys(path);
            return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String selectNthElement(String elementSelector , String index){
        try
        {
          List<WebElement> list = webDriver.findElements(By.xpath(elementSelector));
          list.get(Integer.parseInt(index)).click();
           return "PASS";
        }
        catch(Exception ex)
        {return  ex.toString();}
    }


}
