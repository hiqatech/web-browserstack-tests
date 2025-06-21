package common.setup;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Hooks {

    public static boolean wantsToQuit = false;
    public static Scenario scenario;
    public static String myScenario;
    public static String stepLog;
    public static String myUserName;
    public static String myPassword;

    //------------------------------------------------------------------------//

    @Before
    public void setup(Scenario scenario) throws Exception
    {
        this.scenario = scenario;

        LocalDateTime dateTime = LocalDateTime.now();

        myScenario = scenario.getName();
        System.setProperty("scenario",myScenario);
        System.setProperty("product",myScenario.substring(0,myScenario.indexOf("-")).replace(" ",""));

        System.setProperty("projectPath",System.getProperty("user.dir"));
        System.setProperty("systemTime", dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.setProperty("userID",System.getProperty("user.home").replace("C:\\Users\\",""));
        System.setProperty("downloadPath",System.getProperty("user.home")+"\\Downloads\\");
        System.setProperty("uploadPath",System.getProperty("user.home")+"\\Desktop\\");
        System.setProperty("reportPath",System.getProperty("projectPath")+"\\target\\cucumber-reports\\");
        System.setProperty("filePath",System.getProperty("projectPath") + "\\src\\main\\resources\\files\\");
        System.setProperty("driverPath",System.getProperty("projectPath") + "\\src\\main\\resources\\webdrivers\\");
        System.setProperty("reportConfigPath",System.getProperty("projectPath") + "\\src\\main\\resources\\report\\extent-config.xml");

        if(wantsToQuit)
            throw new RuntimeException("Test FAIL : Cucumber wants to quit");

        System.out.println("************************************************************************************");

        System.out.println("SystemTime : " + System.getProperty("systemTime"));
        System.out.println("Product Tests Starts");
        System.out.println("Scenario : " + myScenario);
        System.out.println("ProjectPath : " + System.getProperty("projectPath"));
        System.out.println("ReportPath : " + System.getProperty("reportPath"));
        System.out.println("Environment : " + System.getProperty("runEnvironment"));
        System.out.println("************************************************************************************");

    }

    public static void AssertExecutedStep(String result)
    {
        stepLog = result;
        if (!result.toUpperCase().contains("PASS")) {
            Hooks.scenario.log(getResultFailLog(result));
            System.out.println(result);
            Assert.assertTrue(false);
        }
        else {
            Hooks.scenario.log(result.replace(",,,",""));
            System.out.println(result);
        }
    }

    public static void VerifyExecutedStep(String result)
    {
        stepLog = result;
        if (!result.toUpperCase().contains("PASS")){
            Hooks.scenario.log(getResultFailLog(result));
            //System.out.println(result);
        }
    }

    public static String getResultFailLog(String result){
        String extString = Arrays.asList(result.split(" ,,, " )).get(0);
        String desc = Arrays.asList(result.split(" ,,, " )).get(1);
        result = "FAIL " + desc + " caused by : " + extString;
        return result;
    }

}
