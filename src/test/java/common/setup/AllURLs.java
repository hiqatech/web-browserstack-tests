package common.setup;

public class AllURLs {

    public static String BankingQA = "https://www.globalsqa.com/angularJs-protractor/BankingProject";
    public static String BankingUAT = "https://www.globalsqa.com/angularJs-protractor/BankingProject";

    public static String getProductURL()
    {
        if (System.getProperty("product").contains("Banking")){
            if (System.getProperty("runEnvironment").equalsIgnoreCase("QA"))
                return BankingQA;
            else if (System.getProperty("runEnvironment").equalsIgnoreCase("UAT"))
                    return BankingUAT;
            else return "Product URL has not been defined on UAT environment";
        }
        else return "Product URL has not been defined";
    }

}
