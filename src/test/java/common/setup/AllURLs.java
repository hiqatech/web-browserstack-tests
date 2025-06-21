package common.setup;

public class AllURLs {

    public static String YouTubeQA = "https://www.youtube.com";
    public static String GitHubQA = "https://www.github.com";

    public static String getProductURL()
    {
        if (System.getProperty("runEnvironment").equalsIgnoreCase("QA")){
            if (System.getProperty("product").contains("YouTube"))
                return YouTubeQA;
            else if (System.getProperty("product").contains("GitHub"))
                return GitHubQA;
            else return "Product URL has not been defined on QA environment";
            }
        else return "Product URL has not been defined";
    }

}
