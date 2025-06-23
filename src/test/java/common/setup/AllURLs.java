package common.setup;

public class AllURLs {

    public static String BankingQA = "https://www.globalsqa.com/angularJs-protractor/BankingProject";
    public static String BankingUAT = "https://www.globalsqa.com/angularJs-protractor/BankingProject";

    public static String getProductURL()
    {
        if (System.getProperty("product").contains("Banking")){
            //if (System.getProperty("environment").equalsIgnoreCase("QA"))
                return BankingQA;
            //else if (System.getProperty("environment").equalsIgnoreCase("UAT"))
            //        return BankingUAT;
            //else return "Product URL has not been defined on the environment";
        }
        else return "Product URL has not been defined";
    }

}
